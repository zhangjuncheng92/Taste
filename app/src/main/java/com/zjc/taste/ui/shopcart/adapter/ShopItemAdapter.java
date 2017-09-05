package com.zjc.taste.ui.shopcart.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.mobo.mobolibrary.ui.base.adapter.ZBaseRecyclerViewAdapter;
import com.mobo.mobolibrary.util.Util;
import com.mobo.mobolibrary.util.image.FrescoImageLoader;
import com.zjc.taste.R;
import com.zjc.taste.db.model.shopcart.ShopCart;
import com.zjc.taste.eventbus.shopcart.ShopChangeEvent;
import com.zjc.taste.utils.Constants;

import de.greenrobot.event.EventBus;


/**
 * Created by Administrator on 2015/7/22.
 */
public class ShopItemAdapter extends ZBaseRecyclerViewAdapter {

    public ShopItemAdapter(Context context) {
        super(context);
    }

    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        return new MainServiceViewHolder(parent);
    }

    class MainServiceViewHolder extends BaseViewHolder<ShopCart> {
        private SimpleDraweeView icon;
        private TextView tvIsCheck;
        private TextView tvName;
        private TextView tvCount;
        private TextView tvPrice;
        private ImageView imgAdd;
        private ImageView imgMinus;


        public MainServiceViewHolder(ViewGroup parent) {
            super(parent, R.layout.shop_cart_item);
            icon = $(R.id.main_wedding_item_ic);
            tvIsCheck = $(R.id.shopCart_item_tv_checkAll);
            tvName = $(R.id.apply_recipe_item_tv_name);
            tvCount = $(R.id.apply_recipe_item_tv_count);
            tvPrice = $(R.id.apply_recipe_item_tv_price);
            imgAdd = $(R.id.apply_recipe_item_tv_add);
            imgMinus = $(R.id.apply_recipe_item_tv_minus);
        }

        @Override
        public void setData(final ShopCart service) {
            FrescoImageLoader.getInstance().displayImage(icon, Constants.getUrlByResource(service.getUri()));
            tvName.setText(service.getName() + "");
            tvCount.setText(service.getAmount() + "");
            tvPrice.setText("¥" + service.getPrice());
            tvIsCheck.setSelected(service.isCheck());

            tvIsCheck.setTag(service);
            tvIsCheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ShopCart shopCart = (ShopCart) view.getTag();
                    shopCart.setCheck(!view.isSelected());
                    view.setSelected(!view.isSelected());
                    EventBus.getDefault().post(new ShopChangeEvent(shopCart));
                }
            });

            imgAdd.setTag(service);
            imgAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ShopCart shopCart = (ShopCart) v.getTag();
                    EventBus.getDefault().post(new ShopChangeEvent(ShopChangeEvent.SHOP_ADD, shopCart));
                }
            });

            imgMinus.setTag(service);
            imgMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ShopCart shopCart = (ShopCart) v.getTag();
                    if (shopCart.getAmount() == 1) {
                        Util.showCustomMsg("购买数量不能少于1");
                    } else {
                        EventBus.getDefault().post(new ShopChangeEvent(ShopChangeEvent.SHOP_MINUS, shopCart));
                    }
                }
            });
        }
    }

}
