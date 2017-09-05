package com.zjc.taste.eventbus.notice;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/26.
 * 消息计数对象
 */
public class MessageCount implements Serializable {
    //（就医端:1预约挂号提醒,2在线报名提醒 3中医服务提醒4体检报名提醒）
    //行医端：1预约挂号提醒2在线报名提醒3双向转诊提醒4中医服务提醒5体检报告提醒
    private int messageType;//消息类型
    private String messageTitle;//消息标题
    private String messageDesc;//消息描述（预留字段）
    private int readedCount;//已读总数
    private int noReadCount;//未读总数
    private String imgUrl;//图片路径（消息缩略图）

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public String getMessageDesc() {
        return messageDesc;
    }

    public void setMessageDesc(String messageDesc) {
        this.messageDesc = messageDesc;
    }

    public int getReadedCount() {
        return readedCount;
    }

    public void setReadedCount(int readedCount) {
        this.readedCount = readedCount;
    }

    public int getNoReadCount() {
        return noReadCount;
    }

    public void setNoReadCount(int noReadCount) {
        this.noReadCount = noReadCount;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
