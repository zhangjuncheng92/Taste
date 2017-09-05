package com.zjc.taste.eventbus.shopcart;

import com.zjc.taste.db.model.shopcart.ShopCart;

/**
 * Created by Administrator on 2015/7/14.
 */
public class ShopChangeEvent {
    private int status;
    private ShopCart shopCart;

    public static int SHOP_ADD = 1;
    public static int SHOP_MINUS = 2;

    public ShopChangeEvent(ShopCart shopCart) {
        this.shopCart = shopCart;
    }

    public ShopChangeEvent(int status, ShopCart shopCart) {
        this.status = status;
        this.shopCart = shopCart;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ShopCart getShopCart() {
        return shopCart;
    }

    public void setShopCart(ShopCart shopCart) {
        this.shopCart = shopCart;
    }
}
