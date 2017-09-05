package com.zjc.taste.ui.shopcart;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.mobo.mobolibrary.ui.base.ZBaseActivity;
import com.zjc.taste.R;
import com.zjc.taste.db.model.shopcart.ShopCart;
import com.zjc.taste.utils.Constants;

import java.util.ArrayList;


/**
 * @author Z
 * @Filename ShopCartActivity.java
 * @Date 2017.08.19
 * @description 购物车界面
 */
public class ShopCartActivity extends ZBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.root_act);
    }

    @Override
    protected void initBaseView() {
        //加载购物车界面
        if (getIntent().getExtras() == null) {
            FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
            ShopCartFragment fragment = new ShopCartFragment();
            trans.addToBackStack(null);
            trans.add(R.id.root, fragment).commit();
        } else if (getIntent().getExtras() != null && getIntent().getExtras().containsKey(Constants.ARGUMENT)) {
            if (getIntent().getExtras().getSerializable(Constants.ARGUMENT) instanceof ArrayList) {
                ArrayList<ShopCart> shopCarts = (ArrayList<ShopCart>) getIntent().getExtras().getSerializable(Constants.ARGUMENT);
                FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
                ShopCartConfirmFragment fragment = ShopCartConfirmFragment.newInstance(shopCarts);
                trans.addToBackStack(null);
                trans.add(R.id.root, fragment).commit();
            }
        }
    }
}
