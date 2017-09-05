package com.zjc.taste.ui.personal.address;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.mobo.mobolibrary.ui.base.ZBaseToolBarFragment;
import com.mobo.mobolibrary.util.Util;
import com.zjc.taste.R;
import com.zjc.taste.ui.shopcart.AddressPickTask;

import cn.qqtheme.framework.entity.City;
import cn.qqtheme.framework.entity.County;
import cn.qqtheme.framework.entity.Province;
import de.greenrobot.event.EventBus;

/**
 * @author Z
 * @Filename AddressAddFragment.java
 * @Date 2017.06.26
 * @description 新建地址
 */
public class AddressAddFragment extends ZBaseToolBarFragment implements View.OnClickListener {
    private EditText edtReceiver;
    private EditText edtPhone;
    private TextView tvArea;
    private EditText edtDetail;
    private ToggleButton tbDefault;
    private TextView tvSave;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setTitle() {
        setTitle(mToolbar, R.string.title_address_add);
    }

    @Override
    protected int inflateContentView() {
        return R.layout.address_add_frg;
    }

    @Override
    protected void layoutInit(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        edtReceiver = (EditText) rootView.findViewById(R.id.address_add_frg_edt_receiver);
        edtPhone = (EditText) rootView.findViewById(R.id.address_add_frg_edt_phone);
        tvArea = (TextView) rootView.findViewById(R.id.address_add_frg_tv_area);
        edtDetail = (EditText) rootView.findViewById(R.id.address_add_frg_edt_detail);
        tbDefault = (ToggleButton) rootView.findViewById(R.id.address_add_frg_tb_default);
        tvSave = (TextView) rootView.findViewById(R.id.address_add_frg_tv_save);

        ((View) tvArea.getParent()).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAddressPicker(view);
            }
        });
        tvSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.address_add_frg_tv_save) {
            saveAddress();
        }
    }

    public void onAddressPicker(View view) {
        AddressPickTask task = new AddressPickTask(getActivity());
        task.setHideProvince(false);
        task.setHideCounty(false);
        task.setCallback(new AddressPickTask.Callback() {
            @Override
            public void onAddressInitFailed() {
                Util.showCustomMsg("数据初始化失败");
            }

            @Override
            public void onAddressPicked(Province province, City city, County county) {
                if (county == null) {
                    Util.showCustomMsg(province.getAreaName() + city.getAreaName());
                } else {
                    Util.showCustomMsg(province.getAreaName() + city.getAreaName() + county.getAreaName());
                }
            }
        });
        task.execute("湖北", "武汉", "东湖高新区");
    }

    private void saveAddress() {
        //必填
        String name = edtReceiver.getEditableText().toString().trim();
        String phone = edtPhone.getEditableText().toString().trim();
        String area = tvArea.getText().toString().trim();
        String detail = edtDetail.getEditableText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            Util.showCustomMsg(getContext().getResources().getString(R.string.address_receiver_hint));
            return;
        }

        if (TextUtils.isEmpty(phone)) {
            Util.showCustomMsg(getContext().getResources().getString(R.string.address_detail_hint));
            return;
        }
        if (TextUtils.isEmpty(area)) {
            Util.showCustomMsg(getContext().getResources().getString(R.string.address_detail_hint));
            return;
        }

        if (TextUtils.isEmpty(detail)) {
            Util.showCustomMsg(getContext().getResources().getString(R.string.address_detail_hint));
            return;
        }

//        ApiHttpClient.getInstance().addHmsPatient(hmsResident, new ResultResponseHandlerOfDialog<HmsResident>(getActivity(), "正在新建档案，请稍等", new HmsResidentParser()) {
//            @Override
//            public void onResultSuccess(List<HmsResident> result) {
//                EventBus.getDefault().post(new CreateResidentEvent(result.get(0)));
//                getActivity().finish();
//            }
//        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
