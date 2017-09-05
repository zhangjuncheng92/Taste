package com.zjc.taste.ui.main;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentTabHost;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.mobo.mobolibrary.ui.base.ZBaseActivity;
import com.mobo.mobolibrary.util.Util;
import com.qihoo.updatesdk.lib.UpdateHelper;
import com.zjc.taste.R;
import com.zjc.taste.ui.main.adapter.MainTab;


/**
 * @author Z
 * @Filename MainActivity.java
 * @Date 2016.10.20
 * @description 主界面控制器
 */
public class MainActivity extends ZBaseActivity implements TabHost.OnTabChangeListener {
    private FragmentTabHost mTabHost;
    private TextView tvNoticeCount;
    public static int noticeCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_act);
        initView();
        checkIsUpData();
    }

    protected void initView() {
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.real_tab_content);

        mTabHost.getTabWidget().setShowDividers(0);
        mTabHost.setOnTabChangedListener(this);
        initTabs();
//        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
//        trans.addToBackStack(null);
//        MainFragment mainFragment = new MainFragment();
//        trans.add(R.id.root, mainFragment).commit();
    }

    private void checkIsUpData() {
        UpdateHelper.getInstance().autoUpdate("com.mobo.curefuturedoctor");
    }

    @Override
    protected void onResume() {
        initNoticeNumber();
        super.onResume();
    }

    public void initNoticeNumber() {
        //如果没有通知消息，则获取消息
        tvNoticeCount.setVisibility(View.GONE);
//        if (SharePreferencesUtil.getInstance().isLogin()) {
//            getNoticeNumber();
//        } else {
//            tvNoticeCount.setVisibility(View.GONE);
//        }
    }

    private void initTabs() {
        MainTab[] tabs = MainTab.values();
        final int size = tabs.length;
        for (int i = 0; i < size; i++) {
            MainTab mainTab = tabs[i];
            TabHost.TabSpec tab = mTabHost.newTabSpec(getString(mainTab.getResName()));

            View indicator = getLayoutInflater().inflate(R.layout.main_indicator_tab, null);
            ImageView icon = (ImageView) indicator.findViewById(R.id.tab_icon);
            icon.setImageResource(mainTab.getResIcon());
            TextView title = (TextView) indicator.findViewById(R.id.tab_title);
            title.setText(getString(mainTab.getResName()));
            tab.setIndicator(indicator);
            tab.setContent(new TabHost.TabContentFactory() {

                @Override
                public View createTabContent(String tag) {
                    return new View(MainActivity.this);
                }
            });
            mTabHost.addTab(tab, mainTab.getClz(), null);

            if (mainTab.equals(MainTab.NOTICE)) {
                tvNoticeCount = (TextView) indicator.findViewById(R.id.tab_icon_count);
            }
        }
    }

    @Override
    public void onTabChanged(String tabId) {
    }

    boolean isExit;

    @Override
    public void onBackPressed() {
        if (mTabHost.getCurrentTab() == 0) {
            exit();
        } else {
            mTabHost.setCurrentTab(0);
        }
    }

    public void exit() {
        if (!isExit) {
            isExit = true;
            Util.showCustomMsg("再按一次退出程序");
            mHandler.sendEmptyMessageDelayed(0, 3000);
        } else {
            finish();
        }
    }

    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };


    private void getNoticeNumber() {
//        ApiHttpClient.getInstance().getMessageList(SharePreferencesUtil.getInstance().readUser().getId(), new ResultResponseHandler<JPushMessageCount>(this, new JPushMessageCountParser()) {
//            @Override
//            public void onResultSuccess(List<JPushMessageCount> result) {
//                if (result.size() > 0 && result.get(0).getCount() > 0) {
//                    tvNoticeCount.setVisibility(View.VISIBLE);
//                    noticeCount = result.get(0).getCount();
//                } else {
//                    tvNoticeCount.setVisibility(View.GONE);
//                }
//            }
//        });
    }
}
