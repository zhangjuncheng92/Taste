package com.zjc.taste.eventbus;


/**
 * Created by Administrator on 2015/7/14.
 */
public class RecipeOrderCancelEvent {
    private int orid;

    public int getOrid() {
        return orid;
    }

    public void setOrid(int orid) {
        this.orid = orid;
    }

    public RecipeOrderCancelEvent(int orid) {
        this.orid = orid;
    }
}
