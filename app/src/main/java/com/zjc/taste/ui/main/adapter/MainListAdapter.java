package com.zjc.taste.ui.main.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.mobo.mobolibrary.ui.base.ZBaseActivity;
import com.mobo.mobolibrary.ui.base.adapter.ZBaseRecyclerViewAdapter;
import com.mobo.mobolibrary.ui.divideritem.SpacesItemDecoration;
import com.zjc.taste.R;
import com.zjc.taste.db.model.goods.Goods;
import com.zjc.taste.db.model.main.MainGoods;
import com.zjc.taste.db.model.wedding.Wedding;
import com.zjc.taste.ui.goods.GoodsDetailActivity;
import com.zjc.taste.ui.goods.GoodsMainActivity;
import com.zjc.taste.ui.goods.adapter.GoodsAdapter;
import com.zjc.taste.ui.wedding.WeddingDetailActivity;
import com.zjc.taste.utils.Constants;
import com.zjc.taste.utils.ConstantsParams;

import java.util.ArrayList;


/**
 * @author Z
 * @Filename MainListAdapter.java
 * @Date 2017.08.24
 * @description 首页列表适配器
 */
public class MainListAdapter extends ZBaseRecyclerViewAdapter {
    private Context context;

    public MainListAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public int getViewType(int position) {
        /**调用父类的getItem，获取searchResult实体类，如调用之类的话，会触发下面的方法，导致返回不同实体*/
        return ((MainGoods) super.getItem(position)).getType();
    }

    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        /**根据viewType得到不同的View*/
        if (viewType == 1) {
            return new MainGoodsViewHolder(context, parent);
        } else if (viewType == 2) {
            return new MainGoodsViewHolder(context, parent);
        } else if (viewType == 3) {
            return new MainWeddingViewHolder(context, parent);
        } else if (viewType == 4) {
            return new MainGoodsViewHolder(context, parent);
        } else if (viewType == 5) {
            return new MainGoodsViewHolder(context, parent);
        }
        return new MainGoodsViewHolder(context, parent);
    }

    /**
     * 婚宴活动布局
     */
    public class MainWeddingViewHolder extends BaseViewHolder<MainGoods> {
        private Context context;
        private TextView tvName;
        private TextView tvMore;
        private EasyRecyclerView mRecyclerView;
        private WeddingAdapter mAdapter;

        public MainWeddingViewHolder(Context context, ViewGroup parent) {
            super(parent, R.layout.main_list_item);
            this.context = context;
            tvName = $(R.id.main_list_item_tv_name);
            tvMore = $(R.id.main_list_item_tv_more);
            mRecyclerView = $(R.id.recyclerView);
        }

        @Override
        public void setData(MainGoods bean) {
            tvName.setText("婚宴活动");
            //点击更多
            ((View) tvMore.getParent()).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putInt(Constants.ARGUMENT, ConstantsParams.SYSTEM_YES);
                    ((ZBaseActivity) getContext()).startActivity(GoodsMainActivity.class, bundle);
                }
            });
            //设置分类商品
            setWeddingList(bean.getWeddingList());
        }

        private void setWeddingList(ArrayList<Wedding> weddingList) {
            GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);

            mRecyclerView.setLayoutManager(layoutManager);
            mRecyclerView.addItemDecoration(new SpacesItemDecoration(10));
            mRecyclerView.setTag(weddingList);
            mAdapter = new WeddingAdapter(getContext());
            mAdapter.setOnItemClickLitener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    ArrayList<Wedding> list = (ArrayList<Wedding>) mRecyclerView.getTag();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Constants.ARGUMENT, list.get(position));
                    ((ZBaseActivity) getContext()).startActivity(WeddingDetailActivity.class, bundle);
                }
            });
            mRecyclerView.setAdapter(mAdapter);
            mAdapter.addAll(weddingList);
        }
    }

    public class MainGoodsViewHolder extends BaseViewHolder<MainGoods> {
        private Context context;
        private TextView tvName;
        private TextView tvMore;
        private EasyRecyclerView mRecyclerView;
        private GoodsAdapter mAdapter;

        public MainGoodsViewHolder(Context context, ViewGroup parent) {
            super(parent, R.layout.main_list_item);
            this.context = context;
            tvName = $(R.id.main_list_item_tv_name);
            tvMore = $(R.id.main_list_item_tv_more);
            mRecyclerView = $(R.id.recyclerView);
        }

        @Override
        public void setData(MainGoods bean) {
            tvName.setText(bean.getCategoryName());
            //点击更多
            ((View) tvMore.getParent()).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putInt(Constants.ARGUMENT, ConstantsParams.SYSTEM_YES);
                    ((ZBaseActivity) getContext()).startActivity(GoodsMainActivity.class, bundle);
                }
            });
            //设置分类商品
            setGoodsList(bean.getGoodsList());
        }

        private void setGoodsList(ArrayList<Goods> goodsList) {
            GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);

            mRecyclerView.setLayoutManager(layoutManager);
            mRecyclerView.addItemDecoration(new SpacesItemDecoration(10));
            mRecyclerView.setTag(goodsList);
            mAdapter = new GoodsAdapter(getContext());
            mAdapter.setOnItemClickLitener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    ArrayList<Goods> list = (ArrayList<Goods>) mRecyclerView.getTag();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Constants.ARGUMENT, list.get(position).getId());
                    ((ZBaseActivity) getContext()).startActivity(GoodsDetailActivity.class, bundle);
                }
            });
            mRecyclerView.setAdapter(mAdapter);
            mAdapter.addAll(goodsList);
        }
    }

}
