package com.zjc.taste.ui.main.adapter;


import com.zjc.taste.R;
import com.zjc.taste.ui.main.MainFragment;
import com.zjc.taste.ui.personal.PersonalMainFragment;
import com.zjc.taste.ui.shopcart.ShopCartFragment;

public enum MainTab {

    HOME(0, R.string.tab_name_home, R.drawable.tab_icon_main, MainFragment.class),

//    INFO(1, R.string.tab_name_info, R.drawable.tab_icon_notice, HealthPropagandaMainFragment.class),

    NOTICE(2, R.string.tab_name_notice, R.drawable.tab_icon_message, ShopCartFragment.class),

    PERSONAL(3, R.string.tab_name_my, R.drawable.tab_icon_personal, PersonalMainFragment.class);

    private int idx;
    private int resName;
    private int resIcon;
    private Class<?> clz;

    private MainTab(int idx, int resName, int resIcon, Class<?> clz) {
        this.idx = idx;
        this.resName = resName;
        this.resIcon = resIcon;
        this.clz = clz;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public int getResName() {
        return resName;
    }

    public void setResName(int resName) {
        this.resName = resName;
    }

    public int getResIcon() {
        return resIcon;
    }

    public void setResIcon(int resIcon) {
        this.resIcon = resIcon;
    }

    public Class<?> getClz() {
        return clz;
    }

    public void setClz(Class<?> clz) {
        this.clz = clz;
    }
}
