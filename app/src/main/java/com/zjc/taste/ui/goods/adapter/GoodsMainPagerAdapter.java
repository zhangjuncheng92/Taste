package com.zjc.taste.ui.goods.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.mobo.mobolibrary.ui.base.adapter.FragmentViewPagerAdapter;
import com.zjc.taste.utils.ConstantsParams;

import java.util.List;

/**
 * Created by Administrator on 2015/7/22.
 */
public class GoodsMainPagerAdapter extends FragmentViewPagerAdapter {
    private String tabTitles[] = new String[]{ConstantsParams.REFERRAL_STATUS_START_TXT, ConstantsParams.REFERRAL_STATUS_BANDING_TXT, ConstantsParams.REFERRAL_STATUS_END_TXT,
            ConstantsParams.REFERRAL_STATUS_CANCEL_TXT,ConstantsParams.REFERRAL_STATUS_CANCEL_TXT,ConstantsParams.REFERRAL_STATUS_CANCEL_TXT};

    public GoodsMainPagerAdapter(FragmentManager fm, List<Fragment> lists) {
        super(fm, lists);
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
