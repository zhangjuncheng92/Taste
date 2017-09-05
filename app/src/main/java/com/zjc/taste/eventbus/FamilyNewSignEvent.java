package com.zjc.taste.eventbus;

/**
 * Created by Administrator on 2016/11/8.
 */
public class FamilyNewSignEvent {
    private String str;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public FamilyNewSignEvent(String str) {
        this.str = str;
    }
}
