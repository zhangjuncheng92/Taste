package com.zjc.taste.eventbus;

/**
 * Created by Administrator on 15.11.10.
 */
public class ReferralCancelEvent {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ReferralCancelEvent(String id) {
        this.id = id;
    }
}
