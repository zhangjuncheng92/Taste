package com.zjc.taste.eventbus.local;


import com.zjc.taste.utils.ConstantsParams;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 15.11.10.
 */
public class Sex {
    private int type;
    private String name;

    public Sex() {

    }

    public Sex(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<Sex> getSex() {
        List<Sex> list = new ArrayList<>();
        list.add(new Sex(ConstantsParams.USER_SEX_WOMEN, "女"));
        list.add(new Sex(ConstantsParams.USER_SEX_MEN, "男"));
        return list;
    }

    public static int getTypeByName(String name) {
        if ("女".equals(name)) {
            return ConstantsParams.USER_SEX_WOMEN;
        } else {
            return ConstantsParams.USER_SEX_MEN;
        }
    }
}
