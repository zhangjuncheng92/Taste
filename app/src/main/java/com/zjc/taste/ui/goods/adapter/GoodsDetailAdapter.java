package com.zjc.taste.ui.goods.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.mobo.mobolibrary.ui.base.adapter.ZBaseRecyclerViewAdapter;
import com.mobo.mobolibrary.util.image.FrescoImageLoader;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.zjc.taste.R;
import com.zjc.taste.db.model.goods.GoodsDetail;
import com.zjc.taste.utils.Constants;
import com.zjc.taste.utils.ImageLoadProxy;


/**
 * @author Z
 * @Filename GoodsDetailAdapter.java
 * @Date 2017.05.26
 * @description 商品详情适配器
 */
public class GoodsDetailAdapter extends ZBaseRecyclerViewAdapter {
    private Context context;

    public GoodsDetailAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        return new WeddingItemViewHolder(context, parent);
    }


    public class WeddingItemViewHolder extends BaseViewHolder<GoodsDetail.ImageListEntity> {
        private ImageView icon;


        public WeddingItemViewHolder(Context context, ViewGroup parent) {
            super(parent, R.layout.goods_detail_item);
            icon = $(R.id.goods_detail_item_icon);
        }

        @Override
        public void setData(GoodsDetail.ImageListEntity bean) {
            ImageLoadProxy.displayImage4Detail(Constants.getUrlByResource(bean.getUri()), icon,null);
        }
    }

}
