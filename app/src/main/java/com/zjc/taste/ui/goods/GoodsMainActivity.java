package com.zjc.taste.ui.goods;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.mobo.mobolibrary.ui.base.ZBaseActivity;
import com.zjc.taste.R;
import com.zjc.taste.ui.wedding.WeddingListFragment;
import com.zjc.taste.ui.goods.adapter.GoodsMainPagerAdapter;
import com.zjc.taste.utils.ConstantsParams;

import java.util.ArrayList;

/**
 * @author Z
 * @Filename GoodsMainActivity.java
 * @Date 2017.08.25
 * @description 商品列表控制器
 */
public class GoodsMainActivity extends ZBaseActivity {
    public ArrayList<Fragment> mFragmentList = new ArrayList<>();

    private ViewPager mPager;// 页卡内容
    private TabLayout mTabLayout;

    private MenuItem menuSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goods_main_act);
        initTitle();
        InitViewPager();
        initTab();
    }

    private void initTitle() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.drawable.comm_back);
        setSupportActionBar(mToolbar);
        OnBackPressClick onBackPressClick = new OnBackPressClick();
        mToolbar.setNavigationOnClickListener(onBackPressClick);

        setTitle(mToolbar, R.string.title_goods_main);
    }


    @Override
    public boolean onCreatePanelMenu(int featureId, Menu menu) {
        getMenuInflater().inflate(R.menu.menu_referral_search, menu);
        return super.onCreatePanelMenu(featureId, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_goods_search) {
//            bundle.putSerializable(Constants.TYPE, ConstantsParams.REFERRAL_UP_PARAM);
//            startActivity(SearchReferralUpActivity.class, bundle);
        }
        return super.onOptionsItemSelected(item);
    }



    private void InitViewPager() {
        mPager = (ViewPager) findViewById(R.id.myconferenceVPager);
        WeddingListFragment startFragment = WeddingListFragment.newInstance(ConstantsParams.REFERRAL_STATUS_START);
        GoodsListFragment bindingFragment = GoodsListFragment.newInstance(ConstantsParams.REFERRAL_STATUS_BANDING);
        GoodsListFragment endFragment = GoodsListFragment.newInstance(ConstantsParams.REFERRAL_STATUS_END);
        GoodsListFragment refuseFragment = GoodsListFragment.newInstance(ConstantsParams.REFERRAL_STATUS_REFUSE);
        GoodsListFragment cancelFragment = GoodsListFragment.newInstance(ConstantsParams.REFERRAL_STATUS_CANCEL);
        mFragmentList.add(startFragment);
        mFragmentList.add(bindingFragment);
        mFragmentList.add(endFragment);
        mFragmentList.add(refuseFragment);
        mFragmentList.add(cancelFragment);
        mPager.setAdapter(new GoodsMainPagerAdapter(getSupportFragmentManager(), mFragmentList));
    }

    private void initTab() {
        mTabLayout = (TabLayout) findViewById(R.id.personal_act_tab);
        mTabLayout.setupWithViewPager(mPager);
    }

}
