package com.zjc.taste.ui.personal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.mobo.mobolibrary.ui.base.ZBaseFragment;
import com.mobo.mobolibrary.ui.widget.empty.EmptyLayout;
import com.mobo.mobolibrary.util.UtilUri;
import com.zjc.taste.R;
import com.zjc.taste.db.SharePreferences.SharePreferencesUtil;
import com.zjc.taste.db.model.main.UserInfo;
import com.zjc.taste.eventbus.ActionCameraEvent;
import com.zjc.taste.eventbus.ActionMultiPhotoEvent;
import com.zjc.taste.eventbus.LoginSuccessEvent;
import com.zjc.taste.ui.personal.address.AddressActivity;
import com.zjc.taste.ui.widget.MultiPhotoDialogFragment;
import com.zjc.taste.utils.ConstantsParams;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;

/**
 * @author Z
 * @Filename PersonalMainFragment.java
 * @Date 2016.05.18
 * @description 个人中心主界面
 */
public class PersonalMainFragment extends ZBaseFragment implements View.OnClickListener {
    private SimpleDraweeView sdUserInfo;

    private TextView tvName;
    private TextView tvOrganization;
    private TextView tvRole;
    private TextView tvCode;

    private TextView tvUpReferral;
    private TextView tvDownReferral;
    private TextView tvAddress;
    private TextView tvSet;

    private RelativeLayout rlDetail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected int inflateContentView() {
        return R.layout.personal_main_frg;
    }

    @Override
    protected void layoutInit(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initEmptyLayout(rootView);
        initView();
        SharePreferencesUtil.getInstance().setLogin(true);
        if (SharePreferencesUtil.getInstance().isLogin()) {
            initFrg();
        }
    }

    private void initView() {
        sdUserInfo = (SimpleDraweeView) rootView.findViewById(R.id.personal_main_frg_sd_icon);
        tvName = (TextView) rootView.findViewById(R.id.personal_main_frg_tv_name);
        tvOrganization = (TextView) rootView.findViewById(R.id.personal_main_frg_tv_organization);
        tvRole = (TextView) rootView.findViewById(R.id.personal_main_frg_tv_role);
        tvCode = (TextView) rootView.findViewById(R.id.personal_main_frg_tv_code);
        tvUpReferral = (TextView) rootView.findViewById(R.id.personal_main_frg_tv_up_referral);
        tvDownReferral = (TextView) rootView.findViewById(R.id.personal_main_frg_tv_down_referral);
        tvAddress = (TextView) rootView.findViewById(R.id.personal_main_frg_tv_address);
        tvSet = (TextView) rootView.findViewById(R.id.personal_main_frg_tv_set);
        rlDetail = (RelativeLayout) rootView.findViewById(R.id.personal_main_frg_rl_detail);

        sdUserInfo.setOnClickListener(this);
        tvCode.setOnClickListener(this);
        tvUpReferral.setOnClickListener(this);
        tvDownReferral.setOnClickListener(this);
        tvAddress.setOnClickListener(this);
        tvSet.setOnClickListener(this);
        rlDetail.setOnClickListener(this);
    }

    private void initFrg() {
        initData();
        getUserById();
    }

    private void initData() {
        UserInfo userInfo = SharePreferencesUtil.getInstance().readUser();
        setContent(userInfo);
    }

    private void setContent(UserInfo userInfo) {
//        ImageLoader.getInstance().displayImage(sdUserInfo, Constants.BASE_URL_DOWNLOAD + userInfo.getBaseInfo().getImg());
//        tvName.setText(userInfo.getBaseInfo().getName());

    }

    //更新用户信息
    private void getUserById() {
        final String userId = SharePreferencesUtil.getInstance().readUser().getId();
//        ApiHttpClient.getInstance().getUserById(userId, new ResultResponseHandler<UserInfo>(getActivity(), new UserInfoParser()) {
//            @Override
//            public void onResultSuccess(List<UserInfo> result) {
//                UserInfo userInfo = result.get(0);
//                userInfo.setToken(SharePreferencesUtil.getInstance().readUser().getToken());
//                SharePreferencesUtil.getInstance().saveUser(userInfo);
//                setContent(userInfo);
//            }
//        });
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.personal_main_frg_sd_icon) {
            MultiPhotoDialogFragment dialogFragment1 = new MultiPhotoDialogFragment();
            dialogFragment1.setMax(1);
            dialogFragment1.setAction(ConstantsParams.PHOTO_TYPE_USER);
            dialogFragment1.show(getFragmentManager(), null);
        } else if (i == R.id.personal_main_frg_tv_code) {
            //二维码
//            PersonalCodeFragment fragment = PersonalCodeFragment.newInstance(SharePreferencesUtil.getInstance().readUser().getId() + "", PersonalCodeFragment.CODE_USER);
//            fragment.show(getFragmentManager(), null);
        } else if (i == R.id.personal_main_frg_rl_detail) {
            //我的信息
            startActivity(PersonalDetailActivity.class);
        } else if (i == R.id.personal_main_frg_tv_address) {
            //地址管理
            Bundle bundle = new Bundle();
            startActivity(AddressActivity.class, bundle);
        } else if (i == R.id.personal_main_frg_tv_set) {
            //设置
//            startActivity(SettingActivity.class);
        }
    }

    @Override
    public void sendRequestData() {
//        startActivity(LoginActivity.class);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!SharePreferencesUtil.getInstance().isLogin()) {
            getEmptyLayout().setErrorType(EmptyLayout.NODATA_ENABLE_CLICK);

            if (SharePreferencesUtil.getInstance().isAuthenticationStatus()) {
                getEmptyLayout().setErrorMessage("正在审核，请耐心等候");
                return;
            }
            getEmptyLayout().setErrorMessage("请点击登录");
        }
    }

    public void onEvent(ActionMultiPhotoEvent event) {
        if (event.getAction() == ConstantsParams.PHOTO_TYPE_USER) {
            sdUserInfo.setTag(UtilUri.getLocalUri(event.getUris().get(0)));
            updateUserBaseInfo(event.getUris().get(0));
        }
    }

    public void onEvent(ActionCameraEvent event) {
        if (event.getAction() == ConstantsParams.PHOTO_TYPE_USER) {
            sdUserInfo.setTag(UtilUri.getLocalUri(event.getUri()));
            updateUserBaseInfo(event.getUri());
        }
    }

    /**
     * 登录成功广播
     *
     * @param event
     */
    public void onEvent(LoginSuccessEvent event) {
        getEmptyLayout().setVisibility(View.GONE);
        initData();
    }

    private void updateUserBaseInfo(String uri) {
        ArrayList<String> uris = new ArrayList<>();
        uris.add(uri);
//        ApiHttpClient.getInstance().addResource(uris, new ResultResponseHandler<AppointmentRegistration>(getActivity(), "请稍等", new AppointmentRegistrationParser()) {
//            @Override
//            public void onResultSuccess(List<AppointmentRegistration> result) {
//            }
//
//            @Override
//            public void onSuccess(int i, Header[] headers, String result) {
//                commitUserInfo(result);
//                this.dismissDialog();
//            }
//        });
    }

    private void commitUserInfo(String result) {
        String id = SharePreferencesUtil.getInstance().readUser().getId();
//        ApiHttpClient.getInstance().upLoadUserImg(id, result, new ResultResponseHandlerOfDialog<UserInfo>(getActivity(), "上传中",
//                new UserInfoParser()) {
//            @Override
//            public void onResultSuccess(List<UserInfo> result) {
//                UserInfo userInfo = result.get(0);
//                ImageLoader.getInstance().displayImage(sdUserInfo, (String) sdUserInfo.getTag());
//                Util.showCustomMsg("头像上传成功");
//
//                //更新用户头像
//                UserInfo beforeUserInfo = SharePreferencesUtil.getInstance().readUser();
//                BaseInfo baseInfo = new BaseInfo();
//                baseInfo.setImg(userInfo.getBaseInfo().getImg());
//                beforeUserInfo.setBaseInfo(baseInfo);
//                SharePreferencesUtil.getInstance().saveUser(beforeUserInfo);
//            }
//        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
