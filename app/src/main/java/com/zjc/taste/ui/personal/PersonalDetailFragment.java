package com.zjc.taste.ui.personal;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.facebook.drawee.view.SimpleDraweeView;
import com.mobo.mobolibrary.ui.base.ZBaseToolBarFragment;
import com.mobo.mobolibrary.util.Util;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.zjc.taste.R;
import com.zjc.taste.api.ApiHttpClient;
import com.zjc.taste.api.ResultResponseHandler;
import com.zjc.taste.db.SharePreferences.SharePreferencesUtil;
import com.zjc.taste.db.model.main.UserInfo;
import com.zjc.taste.eventbus.ActionCameraEvent;
import com.zjc.taste.eventbus.ActionMultiPhotoEvent;
import com.zjc.taste.ui.widget.MultiPhotoDialogFragment;
import com.zjc.taste.utils.Constants;
import com.zjc.taste.utils.ConstantsParams;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;

/**
 * @author Z
 * @Filename PersonalFragment.java
 * @Date 2016.06.06
 * @description 个人中心详情界面
 */
public class PersonalDetailFragment extends ZBaseToolBarFragment implements View.OnClickListener {
    private EditText tvName;
    private EditText tvAge;
    private EditText tvSchool;
    private TextView tvSex;
    private TextView tvScore;
    private EditText tvPhone;
    private EditText tvIdCard;
    private SimpleDraweeView sdIcon;

//    private TimePopupWindow birthOptions;
//
//    private OptionsPopupWindow SexOptions;
    private ArrayList<String> optionsItems = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void setTitle() {
        setTitle(mToolbar, R.string.personal_detail_title);
        mToolbar.setOnMenuItemClickListener(OnMenuItemClick);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_personal_center, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private Toolbar.OnMenuItemClickListener OnMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            int i = item.getItemId();
            if (i == R.id.action_explain) {
                String name = tvName.getText().toString().trim();
                String age = tvAge.getText().toString().trim();
                String phone = tvPhone.getText().toString().trim();
                String idCard = tvIdCard.getText().toString().trim();

                if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(name)) {
                    Util.showCustomMsg("请输入完整信息");
                    return true;
                }

                if (!Util.isMobileNO(phone)) {
                    Util.showCustomMsg("输入的手机号码不正确");
                    return true;
                }

                final UserInfo userInfo = SharePreferencesUtil.getInstance().readUser();
//                userInfo.setUid(SharePreferencesUtil.getInstance().readUser().getUid());
//                userInfo.setTeachername(name);
//                userInfo.setGender((Boolean) tvSex.getTag());
//                userInfo.setPhone(phone);
//                userInfo.setExperience(Integer.parseInt(age));
//                userInfo.setIdentityno(idCard);

//                ApiHttpClient.getInstance().updateUserBaseInfo(userInfo, new ResultResponseHandler(getActivity(), "正在保存") {
//                    @Override
//                    public void onResultSuccess(String result) {
//                        SharePreferencesUtil.getInstance().saveUser(userInfo);
//                        getActivity().finish();
//                    }
//                });
            }
            return true;
        }
    };

    @Override
    protected int inflateContentView() {
        return R.layout.personal_detail_frg;
    }

    @Override
    protected void layoutInit(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initView();
//        initSexOptions();
        setPersonInfo(SharePreferencesUtil.getInstance().readUser());
    }

    private void initView() {
        tvName = (EditText) rootView.findViewById(R.id.personal_frg_tv_name);
        tvSex = (TextView) rootView.findViewById(R.id.personal_frg_tv_sex);
        tvPhone = (EditText) rootView.findViewById(R.id.personal_frg_tv_phone);
        sdIcon = (SimpleDraweeView) rootView.findViewById(R.id.personal_main_frg_sd_icon);

        ((View) tvSex.getParent()).setOnClickListener(this);
        sdIcon.setOnClickListener(this);

        tvSchool.setEnabled(false);
        tvScore.setEnabled(false);

    }

    private void setPersonInfo(UserInfo userInfo) {
//        tvName.setText(userInfo.getTeachername());
//        if (userInfo.isGender()) {
//            tvSex.setText(R.string.personal_sex_men);
//            tvSex.setTag(true);
//        } else {
//            tvSex.setText(R.string.personal_sex_women);
//            tvSex.setTag(false);
//        }
//        tvScore.setText(userInfo.getStars() + "星教练");
//        tvAge.setText(userInfo.getExperience() + "");
//        tvPhone.setText(userInfo.getPhone());
//        tvIdCard.setText(userInfo.getIdentityno());
//        tvSchool.setText(userInfo.getSname());
//        ImageLoader.getInstance().displayImage(sdIcon, Constants.BASE_IP + userInfo.getPhoto());
    }

//    private void initSexOptions() {
//        SexOptions = new OptionsPopupWindow(getActivity());
//        SexOptions.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
//        optionsItems.add(getResources().getString(R.string.personal_sex_men));
//        optionsItems.add(getResources().getString(R.string.personal_sex_women));
//        SexOptions.setPicker(optionsItems, null, null, true);
////        SexOptions.setSelectOptions(0);
//        SexOptions.setOnoptionsSelectListener(new OptionsPopupWindow.OnOptionsSelectListener() {
//            @Override
//            public void onOptionsSelect(int options1, int option2, int options3) {
//                String tx = optionsItems.get(options1);
//                tvSex.setText(tx);
//                if (options1 == 0) {
//                    tvSex.setTag(true);
//                } else {
//                    tvSex.setTag(false);
//                }
//            }
//        });
//    }

    @Override
    public void onClick(View v) {
        InputMethodManager inputmanger = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        int i = v.getId();
        if (i == R.id.personal_main_frg_sd_icon) {
            MultiPhotoDialogFragment dialogFragment1 = new MultiPhotoDialogFragment();
            dialogFragment1.setMax(1);
            dialogFragment1.setAction(ConstantsParams.PHOTO_TYPE_USER);
            dialogFragment1.show(getFragmentManager(), null);
        }
        switch (i) {
            case R.id.sex:
                //隐藏虚拟键盘
//                inputmanger.hideSoftInputFromWindow(rootView.getWindowToken(), 0);
//                SexOptions.showAtLocation(tvSex, Gravity.BOTTOM, 0, 0);
                break;
        }
    }

    public void onEvent(ActionMultiPhotoEvent event) {
        if (event.getAction() == ConstantsParams.PHOTO_TYPE_USER) {
            sdIcon.setTag(event.getUris().get(0));
            updateUserBaseInfo(event.getUris().get(0));
        }
    }

    public void onEvent(ActionCameraEvent event) {
        if (event.getAction() == ConstantsParams.PHOTO_TYPE_USER) {
            sdIcon.setTag(event.getUri());
            updateUserBaseInfo(event.getUri());
        }
    }

    private void updateUserBaseInfo(String uri) {
//        String id = SharePreferencesUtil.getInstance().readUser().getUid();
//        ApiHttpClient.getInstance().upLoadUserImg(id, uri, new ResultResponseHandler(getActivity(), "上传中") {
//            @Override
//            public void onResultSuccess(String result) {
//                //更新用户头像
//                UserInfo beforeUserInfo = SharePreferencesUtil.getInstance().readUser();
//                UserInfo userInfo = new UserInfoParser().parseResultMessage(result);
//                beforeUserInfo.setPhoto(userInfo.getPhoto());
//                SharePreferencesUtil.getInstance().saveUser(beforeUserInfo);
//
//                ImageLoader.getInstance().displayImage(sdIcon, Constants.BASE_IP + userInfo.getPhoto());
//            }
//        });
    }

    private void checkStudyOrder(final boolean ischeck) {
//        ApiHttpClient.getInstance().swichOrder(SharePreferencesUtil.getInstance().readUser().getUid(), ischeck, new ResultResponseHandler(getContext(), "请稍等") {
//
//            @Override
//            public void onResultSuccess(String result) {
//                UserInfo beforeUserInfo = SharePreferencesUtil.getInstance().readUser();
//                beforeUserInfo.setOrderswitch(ischeck);
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
