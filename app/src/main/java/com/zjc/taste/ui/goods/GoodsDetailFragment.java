package com.zjc.taste.ui.goods;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.rollviewpager.RollPagerView;
import com.mingle.sweetpick.SweetSheet;
import com.mobo.mobolibrary.ui.base.ZBaseToolBarFragment;
import com.mobo.mobolibrary.ui.divideritem.HorizontalDividerItemDecoration;
import com.mobo.mobolibrary.util.Util;
import com.zjc.taste.R;
import com.zjc.taste.api.ApiHttpClient;
import com.zjc.taste.api.ResultResponseHandler;
import com.zjc.taste.db.model.goods.GoodsDetail;
import com.zjc.taste.db.model.main.MainGoods;
import com.zjc.taste.db.parser.goods.GoodsDetailParser;
import com.zjc.taste.db.parser.main.MainGoodsParser;
import com.zjc.taste.ui.goods.adapter.GoodsDetailAdapter;
import com.zjc.taste.ui.goods.adapter.GoodsRollPagerAdapter;
import com.zjc.taste.utils.Constants;

import java.util.List;

/**
 * @author Z
 * @Filename GoodsDetailFragment.java
 * @Date 2017.08.25
 * @description 商品详情
 */
public class GoodsDetailFragment extends ZBaseToolBarFragment implements View.OnClickListener {
    private TextView tvTitle;
    private TextView tvPrice;
    private TextView tvSoure;
    private TextView tvSales;
    private TextView tvRule;
    private WebView wbView;
    private LinearLayout btPhone;
    private LinearLayout btShoppingCar;
    private TextView tvCard;
    public LocationClient mLocationClient;
    public MyLocationListener myListener = new MyLocationListener();
    public LocationClientOption option;
    private String city;

    private Handler mHandler = new Handler();
    private WebSettings mWebSettings;

    private int goodsId;

    private RollPagerView homeHeaderPagerView;// 用来放滑动图片的容器
    private GoodsRollPagerAdapter homeHeaderAdapter;

    private GoodsDetailAdapter mAdapter;
    private EasyRecyclerView mRecyclerView;

    //底部弹窗
    private LinearLayout rl_bottom;
    private Button bt_one;
    private Button bt_two;
    private TextView tvBy;
    private SweetSheet mSweetCard;
    Dialog dialogSex;
    private TextView tvTitleDetil;
    private TextView tvPriceDetail;
    private TextView tvNumDetail;
    private TextView tvCardDetail;
    private TextView tvReduceDetail;
    private TextView tvAddDetail;
    private TextView tvNumberDetail;
    SimpleDraweeView image;
    private double latitude = 0.0;
    private double longitude = 0.0;

    /**
     * 传入需要的参数，设置给arguments
     */
    public static GoodsDetailFragment newInstance(int bean) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.ARGUMENT, bean);
        GoodsDetailFragment fragment = new GoodsDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Bundle bundle = getArguments();
        if (bundle != null) {
            goodsId = (int) bundle.getSerializable(Constants.ARGUMENT);
        }
    }

    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            if (location == null) {
                return;
            }
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            city = location.getCity();
            //百度地图没定到位不返回null，返回的是4.9E-324
//            if (latitude == 4.9E-324 || longitude == 4.9E-324) {
//                SharePreferencesUtil.getInstance().savelatitude(30.543622 + "");
//                SharePreferencesUtil.getInstance().savelongitude(114.433890 + "");
//            } else {
//                SharePreferencesUtil.getInstance().savelatitude(String.valueOf(latitude));
//                SharePreferencesUtil.getInstance().savelongitude(String.valueOf(longitude));
//            }

        }

        @Override
        public void onConnectHotSpotMessage(String s, int i) {

        }
    }

    private void initLocation() {
        mLocationClient = new LocationClient(getActivity());
        mLocationClient.registerLocationListener(myListener);
        option = new LocationClientOption();
//        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setOpenGps(true);// 打开gps
        option.setIsNeedAddress(true);
        option.setCoorType("bd09ll");
        option.setScanSpan(1000 * 1);
//        option.setNeedDeviceDirect(true);
        option.setAddrType("all");
        mLocationClient.setLocOption(option);
        mLocationClient.start();
        if (mLocationClient != null && mLocationClient.isStarted()) {
            mLocationClient.requestLocation();
        } else {

        }
    }

    @Override
    protected void setTitle() {
        setTitle(mToolbar, "商品详情");
    }

    @Override
    protected int inflateContentView() {
        return R.layout.shopping_heath_card_detail_frg;
    }

    @Override
    protected void layoutInit(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initLocation();
        initView();
        initEven();
        initRollViewPager();// 初始化图片并滚动
        initDetailView();
        getGoodsDetail();
    }

    private void initEven() {
        dialogSex = new Dialog(getContext(), R.style.dialog);
        View viewSex = LayoutInflater.from(getContext()).inflate(R.layout.shopping_card_detail_bottom_card, null);
        // 设置dialog没有title
        dialogSex.setContentView(viewSex, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        Window window = dialogSex.getWindow();
        // 可以在此设置显示动画
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.y = getActivity().getWindowManager().getDefaultDisplay().getHeight();
        // 以下这两句是为了保证按钮可以水平满屏
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        // 设置显示位置
        dialogSex.onWindowAttributesChanged(wl);

        image = (SimpleDraweeView) viewSex.findViewById(R.id.shopping_card_detail_bottom_picture);
        rl_bottom = (LinearLayout) viewSex.findViewById(R.id.shopping_card_detail_bottom);
        bt_one = (Button) viewSex.findViewById(R.id.shopping_card_detail_bottom_btOne);
        bt_two = (Button) viewSex.findViewById(R.id.shopping_card_detail_bottom_btTwo);
//        if (goodsId.getCate() == 2) {
//            bt_two.setVisibility(View.GONE);
//        } else if (goodsId.getCate() == 1) {
//            bt_one.setVisibility(View.GONE);
//        }
        tvTitleDetil = (TextView) viewSex.findViewById(R.id.shopping_card_detail_bottom_title);
        tvPriceDetail = (TextView) viewSex.findViewById(R.id.shopping_card_detail_bottom_price);
        tvNumDetail = (TextView) viewSex.findViewById(R.id.shopping_card_detail_bottom_num);
        tvCardDetail = (TextView) viewSex.findViewById(R.id.shopping_card_detail_bottom_card);
        tvReduceDetail = (TextView) viewSex.findViewById(R.id.shopping_card_detail_bottom_reduce);
        tvAddDetail = (TextView) viewSex.findViewById(R.id.shopping_card_detail_bottom_add);
        tvNumberDetail = (TextView) viewSex.findViewById(R.id.shopping_card_detail_bottom_number);
        tvBy = (TextView) viewSex.findViewById(R.id.shopping_card_detail_bottom_by);

//        FrescoImageLoader.getInstance().displayImage(image, Constants.BASE_URL_DOWNLOAD + goodsId.getTilteImgId());
        tvNumberDetail.setText("1");
//        tvNumDetail.setText(tvNumberDetail.getText().toString());
////        SharePreferencesUtil.getInstance().saveNum(tvNumDetail.getText().toString());
//        tvTitleDetil.setText(goodsId.getName());
//        tvPriceDetail.setText("¥" + goodsId.getPrice() + "");

        if (tvCard.getText().toString().equals("实物卡") || tvCard.getText().toString().equals("电子卡") || Integer.parseInt(tvNumberDetail.getText().toString()) > 1) {
            tvReduceDetail.setClickable(true);
        } else {
            tvReduceDetail.setClickable(false);
        }
        if (tvCard.getText().toString().equals("实物卡") || tvCard.getText().toString().equals("电子卡")) {
            tvAddDetail.setClickable(true);
        } else {
            tvAddDetail.setClickable(false);
        }

        tvBy.setOnClickListener(this);
        tvReduceDetail.setOnClickListener(this);
        tvAddDetail.setOnClickListener(this);
        bt_one.setOnClickListener(this);
        bt_two.setOnClickListener(this);
        rl_bottom.setOnClickListener(this);

//        tvTitle.setText(goodsId.getName());
//        tvPrice.setText("¥" + goodsId.getPrice() + "");
//        tvSoure.setText(goodsId.getSoure());
//        tvSales.setText(goodsId.getSales());
//        tvRule.setText(goodsId.getRule());
    }


    private void initView() {
        homeHeaderPagerView = (RollPagerView) rootView.findViewById(R.id.main_advertisement);
        tvTitle = (TextView) rootView.findViewById(R.id.shopping_heath_card_detail_title);
        tvPrice = (TextView) rootView.findViewById(R.id.shopping_heath_card_detail_price);
        tvSoure = (TextView) rootView.findViewById(R.id.shopping_heath_card_detail_soure);
        tvSales = (TextView) rootView.findViewById(R.id.shopping_heath_card_detail_sales);
        tvRule = (TextView) rootView.findViewById(R.id.shopping_heath_card_detail_rule);
        btPhone = (LinearLayout) rootView.findViewById(R.id.shopping_heath_card_detail_phone);
        btShoppingCar = (LinearLayout) rootView.findViewById(R.id.shopping_heath_card_detail_shoppingCar);
        tvCard = (TextView) rootView.findViewById(R.id.shopping_heath_card_detail_card);
//        tvRule.setText(goodsId.getRule());
        tvRule.setOnClickListener(this);
        tvCard.setOnClickListener(this);
        btPhone.setOnClickListener(this);
        btShoppingCar.setOnClickListener(this);

//        tvSoure.setText(goodsId.getDescription());
    }


    /**
     * 初始化图片
     */
    private void initRollViewPager() {
        homeHeaderAdapter = new GoodsRollPagerAdapter(getActivity(), homeHeaderPagerView);
        homeHeaderPagerView.setAdapter(homeHeaderAdapter);
    }

    private void initDetailView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView = (EasyRecyclerView) rootView.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity())
                .colorResId(R.color.comm_divider)
                .sizeResId(R.dimen.comm_divider_line)
                .build());
    }

    private void initAdapter() {
        mAdapter = new GoodsDetailAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * 获取商品详情数据
     */
    private void getGoodsDetail() {
        ApiHttpClient.getInstance().getGoodsDetail(5, goodsId, new ResultResponseHandler<GoodsDetail>(getActivity(), new GoodsDetailParser()) {
            @Override
            public void onResultSuccess(List<GoodsDetail> result) {
                homeHeaderAdapter.addAll(result.get(0).getCommonImageList());

                //商品详情
                initAdapter();
                mAdapter.addAll(result.get(0).getDetailImageList());
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shopping_card_detail_bottom_reduce:
                if (Integer.parseInt(tvNumberDetail.getText().toString()) > 1) {
                    tvNumberDetail.setText(Integer.parseInt(tvNumberDetail.getText().toString()) - 1 + "");
                    tvNumDetail.setText(tvNumberDetail.getText().toString());
                } else {
                    tvNumberDetail.setText("1");
                }
//                SharePreferencesUtil.getInstance().saveNum(tvNumDetail.getText().toString());
                break;
            case R.id.shopping_card_detail_bottom_add:
                tvNumberDetail.setText(Integer.parseInt(tvNumberDetail.getText().toString()) + 1 + "");
                tvNumDetail.setText(tvNumberDetail.getText().toString());
//                SharePreferencesUtil.getInstance().saveNum(tvNumDetail.getText().toString());
                break;
            case R.id.shopping_heath_card_detail_phone://在线咨询
                Util.call(getContext(), "027-87880897");
                break;
            case R.id.shopping_card_detail_bottom_by://立即购买
                if (tvCard.getText().toString().equals("实物卡") || tvCard.getText().toString().equals("电子卡")) {
                    dialogSex.cancel();
                    //订单填写界面
//                    ShoppingHeathCardOrderFragment fragment1 = ShoppingHeathCardOrderFragment.newInstance(goodsId);
//                    replaceFrg(fragment1, null);
                } else {
                    Util.showCustomMsg("请选择卡类别");
                }
                break;
            case R.id.shopping_heath_card_detail_shoppingCar://立即购买
                //加入购物车
                addShopCart();
                break;
            case R.id.shopping_heath_card_detail_card://卡类别
                dialogSex.show();
                break;
            case R.id.shopping_heath_card_detail_rule://服务
//                startToWeb(Constants.BASE_URL + "healthCard/toRule");
                break;
            case R.id.shopping_card_detail_bottom_btOne://实物卡
                tvCard.setText("实物卡");
                tvCardDetail.setText("实物卡");
//                SharePreferencesUtil.getInstance().saveCard(tvCardDetail.getText().toString());
                bt_one.setBackgroundResource(R.color.red);
                bt_two.setBackgroundResource(R.color.white);
                break;
            case R.id.shopping_card_detail_bottom_btTwo://电子卡
                tvCard.setText("电子卡");
                tvCardDetail.setText("电子卡");
//                SharePreferencesUtil.getInstance().saveCard(tvCardDetail.getText().toString());
                bt_two.setBackgroundResource(R.color.red);
                bt_one.setBackgroundResource(R.color.white);
                break;
            case R.id.shopping_heath_card_detail_frg:
                dialogSex.cancel();
                break;
        }
    }

    /**
     * 获取首页商品数据
     */
    private void addShopCart() {
        ApiHttpClient.getInstance().addShopCart(1, goodsId, 5, new ResultResponseHandler<MainGoods>(getActivity(), "正在加入购物车...", new MainGoodsParser()) {
            @Override
            public void onResultSuccess(List<MainGoods> result) {

            }
        });
    }

    private void startToWeb(String url) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.ARGUMENT, url);
//        startActivity(HospitalWebActivity.class, bundle);
    }


}
