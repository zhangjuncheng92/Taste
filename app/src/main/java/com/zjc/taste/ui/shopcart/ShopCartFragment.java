package com.zjc.taste.ui.shopcart;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.mobo.mobolibrary.ui.base.ZBaseActivity;
import com.mobo.mobolibrary.ui.base.ZBaseToolBarFragment;
import com.mobo.mobolibrary.ui.divideritem.HorizontalDividerItemDecoration;
import com.mobo.mobolibrary.util.Util;
import com.zjc.taste.R;
import com.zjc.taste.api.ApiHttpClient;
import com.zjc.taste.api.ResultResponseHandler;
import com.zjc.taste.db.model.main.MainGoods;
import com.zjc.taste.db.model.shopcart.ShopCart;
import com.zjc.taste.db.parser.main.MainGoodsParser;
import com.zjc.taste.db.parser.shopcart.ShopCartParser;
import com.zjc.taste.eventbus.shopcart.ShopChangeEvent;
import com.zjc.taste.ui.shopcart.adapter.ShopItemAdapter;
import com.zjc.taste.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;


/**
 * Created by Administrator on 15.9.18.
 */
public class ShopCartFragment extends ZBaseToolBarFragment implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    private TextView tvCommit;
    private TextView tvTotal;
    private TextView tvCheckAll;

    private TextView tvCheckAllEdit;
    private TextView tvDelete;

    private RelativeLayout relativeLayoutPay;
    private RelativeLayout relativeLayoutEdit;

    private MenuItem menuEdit;
    private MenuItem menuFinish;

    private EasyRecyclerView mRecyclerView;
    private ShopItemAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        EventBus.getDefault().register(this);
    }

    @Override
    protected int inflateContentView() {
        return R.layout.shopcart_main_frg;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_shopcart_edit, menu);
        menuEdit = menu.findItem(R.id.menu_shopCart_edit);
        menuFinish = menu.findItem(R.id.menu_shopCart_finish);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private Toolbar.OnMenuItemClickListener OnMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            int i = item.getItemId();
            if (i == R.id.menu_shopCart_edit) {
                //编辑购物车
                showMenuFinish();
            } else if (i == R.id.menu_shopCart_finish) {
                //完成
                showMenuEdit();
            }
            return true;
        }
    };

    /**
     * 显示购物车编辑界面
     */
    private void showMenuEdit() {
        relativeLayoutEdit.setVisibility(View.GONE);
        relativeLayoutPay.setVisibility(View.VISIBLE);
        menuEdit.setVisible(true);
        menuFinish.setVisible(false);
        tvCheckAllEdit.setSelected(false);
        shopCartCheckAll(false);
    }

    /**
     * 显示购物车结算界面
     */
    private void showMenuFinish() {
        relativeLayoutEdit.setVisibility(View.VISIBLE);
        relativeLayoutPay.setVisibility(View.GONE);
        menuEdit.setVisible(false);
        menuFinish.setVisible(true);
        tvCheckAll.setSelected(false);
        shopCartCheckAll(false);
    }

    @Override
    protected void layoutInit(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initEmptyLayout(rootView);
        initView();
        initAdapter();
    }

    private void initView() {
        mRecyclerView = (EasyRecyclerView) rootView.findViewById(R.id.recyclerView);
        tvCommit = (TextView) rootView.findViewById(R.id.apply_commit_frg_tv_commit);
        tvTotal = (TextView) rootView.findViewById(R.id.shopCart_main_frg_tv_total);
        tvCheckAll = (TextView) rootView.findViewById(R.id.shopCart_item_tv_checkAll);

        tvCheckAllEdit = (TextView) rootView.findViewById(R.id.shopCart_item_tv_checkAll_edit);
        tvDelete = (TextView) rootView.findViewById(R.id.shopCart_item_tv_delete);

        relativeLayoutPay = (RelativeLayout) rootView.findViewById(R.id.bottom_pay);
        relativeLayoutEdit = (RelativeLayout) rootView.findViewById(R.id.bottom_edit);

        tvCommit.setOnClickListener(this);
        tvCheckAll.setOnClickListener(this);
        tvCheckAllEdit.setOnClickListener(this);
        tvDelete.setOnClickListener(this);
    }

    private void initAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity())
                .colorResId(R.color.comm_divider)
                .sizeResId(R.dimen.comm_divider_line)
                .build());
        mAdapter = new ShopItemAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * 获取购物车商品
     */
    private void getShopCart() {
        ApiHttpClient.getInstance().getShopCart(5, new ResultResponseHandler<ShopCart>(getActivity(), "加载购物车", new ShopCartParser()) {
            @Override
            public void onResultSuccess(List<ShopCart> result) {
                mAdapter.clear();
                mAdapter.addAll(result);
                shopCartRefreshAmount();
            }
        });
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.apply_commit_frg_tv_commit) {
            //提交订单
            if (shopCartBeCheck().size() == 0) {
                Util.showCustomMsg("请选择商品");
            } else {
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constants.ARGUMENT, shopCartBeCheck());
                startActivity(ShopCartActivity.class, bundle);
            }
        } else if (i == R.id.shopCart_item_tv_checkAll) {
            //购物车-全选或反选
            v.setSelected(!v.isSelected());
            shopCartCheckAll(v.isSelected());
            shopCartRefreshAmount();
        } else if (i == R.id.shopCart_item_tv_checkAll_edit) {
            //编辑-全选或反选
            v.setSelected(!v.isSelected());
            shopCartCheckAll(v.isSelected());
        } else if (i == R.id.shopCart_item_tv_delete) {
            //编辑-删除选中
            deleteByShopCart(checkGoodsIds());
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        getShopCart();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        HmsInpatientArea department = departments.get(position);
//        spDepart.setTag(department);
//        SharePreferencesUtil.getInstance().saveDepartsIndex(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public void onEventMainThread(ShopChangeEvent event) {
        ShopCart shopCart = event.getShopCart();
        if (event.getStatus() == ShopChangeEvent.SHOP_ADD) {
            addGoods(shopCart.getGoodsId(), shopCart.getAmount() + 1);
        } else if (event.getStatus() == ShopChangeEvent.SHOP_MINUS) {
            minusGoods(shopCart.getGoodsId(), shopCart.getAmount() - 1);
        }
        shopCartRefreshAmount();
    }

    /**
     * 增加商品
     */
    private void addGoods(int goodsId, int goodsNum) {
        ApiHttpClient.getInstance().addGoods(goodsNum, goodsId, 5, new ResultResponseHandler<MainGoods>(getActivity(), new MainGoodsParser()) {
            @Override
            public void onResultSuccess(List<MainGoods> result) {
                getShopCart();
            }
        });
    }

    /**
     * 减少商品
     */
    private void minusGoods(int goodsId, int goodsNum) {
        ApiHttpClient.getInstance().minusGoods(goodsNum, goodsId, 5, new ResultResponseHandler<MainGoods>(getActivity(), new MainGoodsParser()) {
            @Override
            public void onResultSuccess(List<MainGoods> result) {
                getShopCart();
            }
        });
    }

    /**
     * 批量删除商品从购物车
     */
    private void deleteByShopCart(JsonArray ids) {
        ApiHttpClient.getInstance().deleteByShopCart(ids, 5, new ResultResponseHandler<MainGoods>(getActivity(), new MainGoodsParser()) {
            @Override
            public void onResultSuccess(List<MainGoods> result) {
                getShopCart();
                showMenuEdit();
            }
        });
    }

    /**
     * 全选或反选
     *
     * @return
     */
    public void shopCartCheckAll(boolean isCheck) {
        ArrayList<ShopCart> shopCarts = (ArrayList<ShopCart>) mAdapter.getAllData();
        for (int i = 0; i < shopCarts.size(); i++) {
            ShopCart shopCart = shopCarts.get(i);
            shopCart.setCheck(isCheck);
        }
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 刷新商品总数和总价格
     *
     * @return
     */
    public void shopCartRefreshAmount() {
        double total = 0;
        ArrayList<ShopCart> shopCarts = (ArrayList<ShopCart>) mAdapter.getAllData();
        for (int i = 0; i < shopCarts.size(); i++) {
            ShopCart shopCart = shopCarts.get(i);
            if (shopCart.isCheck()) {
                total += (shopCart.getPrice() * shopCart.getAmount());
            }
        }
        tvTotal.setText(total + "");
    }

    /**
     * 获取选中商品的id数组
     *
     * @return
     */
    public JsonArray checkGoodsIds() {
        JsonArray array = new JsonArray();
        ArrayList<ShopCart> shopCarts = (ArrayList<ShopCart>) mAdapter.getAllData();
        for (int i = 0; i < shopCarts.size(); i++) {
            ShopCart shopCart = shopCarts.get(i);
            if (shopCart.isCheck()) {
                array.add(shopCart.getId());
            }
        }
        return array;
    }

    /**
     * 获取被选中的商品
     *
     * @return
     */
    public ArrayList<ShopCart> shopCartBeCheck() {
        ArrayList<ShopCart> shopCarts = (ArrayList<ShopCart>) mAdapter.getAllData();
        ArrayList<ShopCart> shopCartsBeCheck = new ArrayList<>();
        for (int i = 0; i < shopCarts.size(); i++) {
            ShopCart shopCart = shopCarts.get(i);
            if (shopCart.isCheck()) {
                shopCartsBeCheck.add(shopCart);
            }
        }
        return shopCartsBeCheck;
    }


    private void commitOrder() {

    }


    @Override
    public void sendRequestData() {
        getShopCart();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void setTitle() {
        ((ZBaseActivity) getActivity()).setSupportActionBar(mToolbar);
        setTitle(mToolbar, R.string.tab_name_notice);
        mToolbar.setNavigationIcon(null);
        mToolbar.setOnMenuItemClickListener(OnMenuItemClick);
    }
}
