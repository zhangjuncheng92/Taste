package com.zjc.taste.ui.goods.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.mobo.mobolibrary.ui.base.adapter.ZBaseRecyclerViewAdapter;
import com.mobo.mobolibrary.util.image.FrescoImageLoader;
import com.zjc.taste.R;
import com.zjc.taste.db.model.goods.Goods;
import com.zjc.taste.utils.Constants;


/**
 * @author Z
 * @Filename MainListAdapter.java
 * @Date 2016.05.26
 * @description 我的转诊单统一适配器
 */
public class GoodsAdapter extends ZBaseRecyclerViewAdapter {
    private Context context;

    public GoodsAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        return new WeddingItemViewHolder(context, parent);
    }


    public class WeddingItemViewHolder extends BaseViewHolder<Goods> {
        private Context context;
        private SimpleDraweeView icon;
        private TextView tvName;
        private TextView tvPrice;
        private TextView tvOriginalPrice;


        public WeddingItemViewHolder(Context context, ViewGroup parent) {
            super(parent, R.layout.main_goods_item);
            this.context = context;
            icon = $(R.id.main_wedding_item_ic);
            tvName = $(R.id.main_wedding_item_tv_name);
            tvPrice = $(R.id.main_wedding_item_tv_price);
            tvOriginalPrice = $(R.id.main_wedding_item_tv_originalPrice);
        }

        @Override
        public void setData(Goods bean) {
            FrescoImageLoader.getInstance().displayImage(icon, Constants.getUrlByResource(bean.getUri()));
            tvName.setText(bean.getName());
            tvPrice.setText(bean.getProPrice() + "");
            tvOriginalPrice.setText(bean.getPrice() + "");
        }
    }

}
