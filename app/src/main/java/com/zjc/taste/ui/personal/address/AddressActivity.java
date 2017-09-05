package com.zjc.taste.ui.personal.address;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.mobo.mobolibrary.ui.base.ZBaseActivity;
import com.zjc.taste.R;


/**
 * @author Z
 * @Filename PersonalDetailActivity.java
 * @Date 2016.05.21
 * @description 个人中心控制器
 */
public class AddressActivity extends ZBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.root_act);
    }

    @Override
    protected void initBaseView() {
        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        AddressListFragment fragment = new AddressListFragment();
        trans.addToBackStack(null);
        trans.add(R.id.root, fragment).commit();
    }
}
