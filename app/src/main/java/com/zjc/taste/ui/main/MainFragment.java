package com.zjc.taste.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.rollviewpager.RollPagerView;
import com.mobo.mobolibrary.ui.base.ZBaseActivity;
import com.mobo.mobolibrary.ui.base.ZBaseFragment;
import com.mobo.mobolibrary.ui.base.adapter.ZBaseRecyclerViewAdapter;
import com.mobo.mobolibrary.ui.divideritem.HorizontalDividerItemDecoration;
import com.zjc.taste.R;
import com.zjc.taste.api.ApiHttpClient;
import com.zjc.taste.api.ResultResponseHandler;
import com.zjc.taste.db.SharePreferences.SharePreferencesUtil;
import com.zjc.taste.db.model.main.Advertisement;
import com.zjc.taste.db.model.main.MainGoods;
import com.zjc.taste.db.parser.main.AdvertisementParser;
import com.zjc.taste.db.parser.main.MainGoodsParser;
import com.zjc.taste.ui.main.adapter.HomeRollPagerAdapter;
import com.zjc.taste.ui.main.adapter.MainListAdapter;
import com.zjc.taste.utils.Constants;

import java.util.List;

/**
 * @author Z
 * @Filename MainFragment.java
 * @Date 2017.08.18
 * @description 首界面
 */
public class MainFragment extends ZBaseFragment implements OnClickListener, ZBaseRecyclerViewAdapter.OnItemClickListener {
    private MainListAdapter mAdapter;
    private EasyRecyclerView mRecyclerView;

    private RollPagerView homeHeaderPagerView;// 用来放滑动图片的容器
    private HomeRollPagerAdapter homeHeaderAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void initTitle() {
        Toolbar mToolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        ((ZBaseActivity) getActivity()).setSupportActionBar(mToolbar);
        setTitle(mToolbar, R.string.app_name);
    }

    @Override
    protected int inflateContentView() {
        return R.layout.main_frg;
    }

    @Override
    protected void layoutInit(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initTitle();
        initView();
        initRollViewPager();// 初始化图片并滚动
        initAdapter();
    }

    private void initView() {
        homeHeaderPagerView = (RollPagerView) rootView.findViewById(R.id.main_advertisement);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView = (EasyRecyclerView) rootView.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity())
                .colorResId(R.color.comm_divider_trans)
                .sizeResId(R.dimen.comm_divider_one)
                .build());
    }

    /**
     * 初始化图片
     */
    private void initRollViewPager() {
        homeHeaderAdapter = new HomeRollPagerAdapter(getActivity(),homeHeaderPagerView);
        homeHeaderPagerView.setAdapter(homeHeaderAdapter);

        if (null == SharePreferencesUtil.getInstance().readMainGoods()) {
            findAppMenu();
        } else {
            List<Advertisement> mainGoods = SharePreferencesUtil.getInstance().readMainGoods();
            homeHeaderAdapter.addAll(mainGoods);
            findAppMenu();
        }
    }

    /**
     * 获取首页广告
     */
    private void findAppMenu() {
        ApiHttpClient.getInstance().getAdvertisement(5,new ResultResponseHandler<Advertisement>(getActivity(), new AdvertisementParser()) {
            @Override
            public void onResultSuccess(List<Advertisement> result) {
                homeHeaderAdapter.addAll(result);
                SharePreferencesUtil.getInstance().saveHomeAdvertisement(result);
            }
        });
    }

    private void initAdapter() {
        mAdapter = new MainListAdapter(getActivity());
        mAdapter.setOnItemClickLitener(this);
        mRecyclerView.setAdapter(mAdapter);

        getMainGoods();
    }

    /**
     * 获取首页商品数据
     */
    private void getMainGoods() {
        ApiHttpClient.getInstance().getMainGoods(new ResultResponseHandler<MainGoods>(getActivity(), new MainGoodsParser()) {
            @Override
            public void onResultSuccess(List<MainGoods> result) {
                mAdapter.addAll(result);
            }
        });
    }


    @Override
    public void onItemClick(View view, int position) {

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.INTENT_REQUESTCODE) {

        }
    }
}
