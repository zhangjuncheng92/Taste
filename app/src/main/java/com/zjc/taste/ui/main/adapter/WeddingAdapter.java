package com.zjc.taste.ui.main.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.mobo.mobolibrary.ui.base.adapter.ZBaseRecyclerViewAdapter;
import com.mobo.mobolibrary.util.image.FrescoImageLoader;
import com.zjc.taste.R;
import com.zjc.taste.db.model.wedding.Wedding;


/**
 * @author Z
 * @Filename MainListAdapter.java
 * @Date 2016.05.26
 * @description 我的转诊单统一适配器
 */
public class WeddingAdapter extends ZBaseRecyclerViewAdapter {
    private Context context;

    public WeddingAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        return new WeddingItemViewHolder(context, parent);
    }


    public class WeddingItemViewHolder extends BaseViewHolder<Wedding> {
        private Context context;
        private SimpleDraweeView icon;
        private TextView tvName;
        private TextView tvPrice;
        private TextView tvOriginalPrice;


        public WeddingItemViewHolder(Context context, ViewGroup parent) {
            super(parent, R.layout.main_wedding_item);
            this.context = context;
            icon = $(R.id.main_wedding_item_ic);
            tvName = $(R.id.main_wedding_item_tv_name);
            tvPrice = $(R.id.main_wedding_item_tv_price);
            tvOriginalPrice = $(R.id.main_wedding_item_tv_originalPrice);
        }

        @Override
        public void setData(Wedding bean) {
//            FrescoImageLoader.getInstance().displayImage(icon, bean.getResList().get(0).getUri());
            FrescoImageLoader.getInstance().displayImage(icon, "https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=1732957159,190206299&fm=173&s=D8A43C765912EECC06E070650300E072&w=640&h=582&img.JPEG");
            tvName.setText(bean.getName());
//            tvPrice.setText(bean.getName());
//            tvOriginalPrice.setText(bean.getName());
        }
    }

}
