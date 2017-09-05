package com.zjc.taste.db.model.wedding;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by asus1 on 2017/8/23.
 */
public class Wedding implements Serializable {
    private ArrayList<Resource> resList;
    private String detail;//活动详情
    private String name;//活动名称
    private int createTime;//创建时间
    private int id;//活动id
    private int isShow;//是否展示(0:否,1:是)
    private int total;//点赞数
    private int type;//活动类型(1:促销,2:广告,3:婚宴)

    public ArrayList<Resource> getResList() {
        return resList;
    }

    public void setResList(ArrayList<Resource> resList) {
        this.resList = resList;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCreateTime() {
        return createTime;
    }

    public void setCreateTime(int createTime) {
        this.createTime = createTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsShow() {
        return isShow;
    }

    public void setIsShow(int isShow) {
        this.isShow = isShow;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
