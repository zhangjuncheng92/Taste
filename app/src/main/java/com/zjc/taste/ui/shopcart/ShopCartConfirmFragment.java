package com.zjc.taste.ui.shopcart;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.mobo.mobolibrary.ui.base.ZBaseToolBarFragment;
import com.mobo.mobolibrary.ui.divideritem.HorizontalDividerItemDecoration;
import com.mobo.mobolibrary.util.Util;
import com.zjc.taste.R;
import com.zjc.taste.db.model.shopcart.Address;
import com.zjc.taste.db.model.shopcart.ShopCart;
import com.zjc.taste.eventbus.shopcart.ShopChangeEvent;
import com.zjc.taste.ui.shopcart.adapter.ShopItemAdapter;
import com.zjc.taste.utils.Constants;

import java.util.ArrayList;

import cn.qqtheme.framework.entity.City;
import cn.qqtheme.framework.entity.County;
import cn.qqtheme.framework.entity.Province;
import de.greenrobot.event.EventBus;


/**
 * @author Z
 * @Filename ShopCartConfirmFragment.java
 * @Date 2017.10.28
 * @description 订单确认界面
 */
public class ShopCartConfirmFragment extends ZBaseToolBarFragment implements View.OnClickListener {
    private ArrayList<ShopCart> shopCarts;

    private Address address;
    private TextView tvName;
    private TextView tvPhone;
    private TextView tvAddress;


    private TextView tvPayment;
    private TextView tvDelivery;
    private EditText edtRemark;
    private TextView tvNumber;
    private TextView tvSum;

    private TextView tvTotal;
    private TextView tvCommit;

    private EasyRecyclerView mRecyclerView;
    private ShopItemAdapter mAdapter;

    private Dialog dialogPayment;
    private TextView tvOnline;
    private TextView tvOffline;

    /**
     * 传入需要的参数，设置给arguments
     */
    public static ShopCartConfirmFragment newInstance(ArrayList<ShopCart> shopCarts) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.ARGUMENT, shopCarts);
        ShopCartConfirmFragment fragment = new ShopCartConfirmFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        Bundle bundle = getArguments();
        if (bundle != null) {
            shopCarts = (ArrayList<ShopCart>) bundle.getSerializable(Constants.ARGUMENT);
        }
    }

    @Override
    protected int inflateContentView() {
        return R.layout.shopcart_confirm_frg;
    }

    @Override
    protected void layoutInit(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (shopCarts != null) {
            initView();
            initAdapter();
            setInfo();
            initDialogPayment();
        }
    }

    private void initView() {
        mRecyclerView = (EasyRecyclerView) rootView.findViewById(R.id.recyclerView);

        tvName = (TextView) rootView.findViewById(R.id.shopCart_confirm_frg_tv_name);
        tvPhone = (TextView) rootView.findViewById(R.id.shopCart_confirm_frg_tv_phone);
        tvAddress = (TextView) rootView.findViewById(R.id.shopCart_confirm_frg_tv_address);
        tvPayment = (TextView) rootView.findViewById(R.id.shopCart_confirm_frg_tv_payment);
        tvDelivery = (TextView) rootView.findViewById(R.id.shopCart_confirm_frg_tv_delivery);
        edtRemark = (EditText) rootView.findViewById(R.id.shopCart_confirm_frg_edt_remark);
        tvNumber = (TextView) rootView.findViewById(R.id.shopCart_confirm_frg_tv_number);
        tvSum = (TextView) rootView.findViewById(R.id.shopCart_confirm_frg_tv_sum);

        tvTotal = (TextView) rootView.findViewById(R.id.shopCart_confirm_frg_tv_total);
        tvCommit = (TextView) rootView.findViewById(R.id.shopCart_confirm_frg_tv_commit);

        tvCommit.setOnClickListener(this);
        tvAddress.setOnClickListener(this);
        setPaymentOnClick();
    }

    private void setPaymentOnClick() {
        ((View) tvPayment.getParent()).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogPayment.show();
            }
        });
    }

    private void initAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity())
                .colorResId(R.color.comm_divider)
                .sizeResId(R.dimen.comm_divider_line)
                .build());
        mAdapter = new ShopItemAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);
    }

    private void setInfo() {
        mAdapter.addAll(shopCarts);

//        if (doctor.getDoctorTitle() == null) {
//            mTv_subtitle.setVisibility(View.GONE);
//        } else {
//            mTv_subtitle.setText(doctor.getDoctorTitle());
//        }
//        mTv_depart.setText(doctor.getDepartmentName());
//        mTv_name.setText(doctor.getName());
//        mTv_hospital.setText(doctor.getHospitalName());
//        mTv_fee.setText(doctorSchedule.getRegistrationFeeTotal() + "");
//        mTv_time.setText(UtilRegistration.getRegistrationTime(getActivity(), doctorSchedule));
//        tvTimeShare.setText(UtilRegistration.setShareTime(tvTimeShare, doctorSchedule));
//        mTv_scheduleType.setText(doctorSchedule.getScheduleTypeName());
//
//        ImageLoader.getInstance().displayImage(dvDoctor, Constants.BASE_URL_DOWNLOAD + doctor.getPhoto());
    }

    @Override
    protected void setTitle() {
        setTitle(mToolbar, "确认订单");
    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.shopCart_confirm_payment_tv_online) {
            tvOnline.setSelected(true);
            tvOffline.setSelected(false);
            dialogPayment.cancel();
            tvPayment.setText(R.string.shopCart_confirm_payment_online);
        } else if (i == R.id.shopCart_confirm_payment_tv_offline) {
            tvOnline.setSelected(false);
            tvOffline.setSelected(true);
            dialogPayment.cancel();
            tvPayment.setText(R.string.shopCart_confirm_payment_offline);
        }
    }

    private void initDialogPayment() {
        dialogPayment = new Dialog(getContext(), R.style.dialog);
        View viewSex = LayoutInflater.from(getContext()).inflate(R.layout.shopcart_confirm_payment_dialog, null);
        // 设置dialog没有title
        dialogPayment.setContentView(viewSex, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        Window window = dialogPayment.getWindow();
        // 可以在此设置显示动画
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.y = getActivity().getWindowManager().getDefaultDisplay().getHeight();
        // 以下这两句是为了保证按钮可以水平满屏
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        // 设置显示位置
        dialogPayment.onWindowAttributesChanged(wl);

        tvOnline = (TextView) viewSex.findViewById(R.id.shopCart_confirm_payment_tv_online);//在线支付
        tvOffline = (TextView) viewSex.findViewById(R.id.shopCart_confirm_payment_tv_offline);//货到付款
        tvOnline.setOnClickListener(this);
        tvOffline.setOnClickListener(this);
        tvOnline.setSelected(true);
        tvPayment.setText(R.string.shopCart_confirm_payment_online);
    }

    public void onEventMainThread(ShopChangeEvent event) {
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
