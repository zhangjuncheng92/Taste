package com.zjc.taste.db.model.wedding;

import java.io.Serializable;

/**
 * Created by asus1 on 2017/8/23.
 */
public class Resource implements Serializable {
    private int activityId;//分类id
    private int id;//活动资源id
    private String resName;//资源名称
    private int type;//资源类型(0:图片,1:视频)
    private String uri;//资源地址

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
