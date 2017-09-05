package com.zjc.taste.ui.wedding;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.mobo.mobolibrary.ui.base.ZBaseFragment;
import com.mobo.mobolibrary.ui.base.adapter.ZBaseRecyclerViewAdapter;
import com.mobo.mobolibrary.ui.divideritem.HorizontalDividerItemDecoration;
import com.zjc.taste.R;
import com.zjc.taste.api.ApiHttpClient;
import com.zjc.taste.api.ResultResponseHandler;
import com.zjc.taste.db.model.goods.Goods;
import com.zjc.taste.db.parser.goods.GoodsParser;
import com.zjc.taste.ui.goods.adapter.GoodsAdapter;
import com.zjc.taste.utils.Constants;
import com.zjc.taste.utils.ConstantsParams;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * @author Z
 * @Filename WeddingListFragment.java
 * @Date 2017.08.25
 * @description 婚宴列表
 */
public class WeddingListFragment extends ZBaseFragment implements ZBaseRecyclerViewAdapter.OnItemClickListener, ZBaseRecyclerViewAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {
    private GoodsAdapter mAdapter;
    private EasyRecyclerView mRecyclerView;

    private int referral_status;

    /**
     * 传入需要的参数，设置给arguments
     */
    public static WeddingListFragment newInstance(int bean) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.ARGUMENT, bean);
        WeddingListFragment fragment = new WeddingListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            referral_status = bundle.getInt(Constants.ARGUMENT);
        }
    }

    @Override
    protected int inflateContentView() {
        return R.layout.comm_recyclerview_frg;
    }

    @Override
    protected void layoutInit(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initEmptyLayout(rootView);
        initView();
        findDoctorsByDepartmentId();
    }

    private void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView = (EasyRecyclerView) rootView.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setRefreshListener(this);
        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity())
                .colorResId(R.color.comm_divider)
                .sizeResId(R.dimen.comm_divider_line)
                .build());
    }

    private void initAdapter() {
        mAdapter = new GoodsAdapter(getActivity());
        mAdapter.setOnItemClickLitener(this);
        mAdapter.setMore(R.layout.view_more, this);
        mAdapter.setNoMore(R.layout.view_nomore);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void sendRequestData() {
        findDoctorsByDepartmentId();
    }

    @Override
    public void onLoadMore() {
        int start = mAdapter.getCount();
        ApiHttpClient.getInstance().getMainGoods(new ResultResponseHandler<Goods>(getActivity(), new GoodsParser()) {
            @Override
            public void onResultSuccess(List<Goods> result) {
                mAdapter.addAll(result);
                isLoadFinish(result.size());
            }
        });
    }

    private void findDoctorsByDepartmentId() {
        ApiHttpClient.getInstance().getMainGoods(new ResultResponseHandler<Goods>(getActivity(), getEmptyLayout(), new GoodsParser()) {

            @Override
            public void onResultSuccess(List<Goods> result) {
                initAdapter();
                mAdapter.addAll(result);
                isLoadFinish(result.size());
            }
        });
    }

    /**
     * 加载完成
     */
    public boolean isLoadFinish(int size) {
        if (size < ConstantsParams.PAGE_SIZE) {
            mAdapter.stopMore();
            mAdapter.setNoMore(R.layout.view_nomore);
            return true;
        }
        return false;
    }


    @Override
    public void onItemClick(View view, int position) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.ARGUMENT, (Goods) mAdapter.getItem(position));
//        startActivity(ReferralDetailActivity.class, bundle);
    }

    @Override
    public void onRefresh() {
        ApiHttpClient.getInstance().getMainGoods(new ResultResponseHandler<Goods>(getActivity(), mRecyclerView, new GoodsParser()) {

            @Override
            public void onResultSuccess(List<Goods> result) {
                initAdapter();
                mAdapter.addAll(result);
                isLoadFinish(result.size());
            }
        });
    }

//    /**
//     * 更新内容
//     *
//     * @param event
//     */
//    public void onEventMainThread(ReferralDownChangeEvent event) {
//        int count = mAdapter.getCount();
//        mAdapter.notifyDataSetChanged();
//        findDoctorsByDepartmentId();
////        for (int i = 0; i < count; i++) {
////            Refer refer = (Refer) mAdapter.getItem(i);
////            if (refer.getRefId().equals(event.getHmsDownReferral().getRefId())) {
////                refer.getExtendInfo().put("admittingDiagnose", event.getHmsDownReferral().getExtendInfo().get("admittingDiagnose"));
////                refer.getExtendInfo().put("casePresentation", event.getHmsDownReferral().getExtendInfo().get("casePresentation"));
////                refer.getExtendInfo().put("procedures", event.getHmsDownReferral().getExtendInfo().get("procedures"));
////                refer.getExtendInfo().put("allergy", event.getHmsDownReferral().getExtendInfo().get("allergy"));
////                refer.getExtendInfo().put("dischargeDiagnose", event.getHmsDownReferral().getExtendInfo().get("dischargeDiagnose"));
////                refer.getExtendInfo().put("dischargeDiagnose", event.getHmsDownReferral().getExtendInfo().get("dischargeDiagnose"));
////                refer.setReceiveOrganizationAdress(event.getHmsDownReferral().getReceiveOrganizationAdress());
////                refer.setReceiveOrganizationName(event.getHmsDownReferral().getReceiveOrganizationName());
////                refer.setReferralStatus(event.getHmsDownReferral().getReferralStatus());
//////                refer.setCauseRemark(event.getHmsDownReferral().getCauseRemark());
////
////                //时间
//////                hmsDownReferral.setReceiveAdminName(event.getHmsDownReferral().getReceiveAdminName());
////                refer.setReceiveTime(event.getHmsDownReferral().getReceiveTime());
//////                hmsDownReferral.setEndAdminName(event.getHmsDownReferral().getEndAdminName());
////                refer.setEndTime(event.getHmsDownReferral().getEndTime());
//////                hmsDownReferral.setCauseUserName(event.getHmsDownReferral().getCauseUserName());
//////                hmsDownReferral.setCauseTime(event.getHmsDownReferral().getCauseTime());
////                mAdapter.notifyItemChanged(i);
////                break;
////            }
////        }
//    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}