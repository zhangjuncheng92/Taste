package com.zjc.taste.eventbus;


import com.zjc.taste.eventbus.local.Sex;

/**
 * Created by Administrator on 15.11.10.
 */
public class SelectSexEvent {
    private Sex sex;

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public SelectSexEvent(Sex sex) {
        this.sex = sex;
    }
}
