package com.zjc.taste.eventbus;


import com.zjc.taste.eventbus.notice.Notice;

/**
 * Created by Administrator on 2015/7/14.
 */
public class JPushNotificationStateEvent {
    Notice jPushNotification;

    public JPushNotificationStateEvent(Notice jPushNotification) {
        this.jPushNotification = jPushNotification;
    }

    public Notice getJPushNotification() {
        return jPushNotification;
    }
}
