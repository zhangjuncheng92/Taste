package com.zjc.taste.db.model.goods;

import java.io.Serializable;

/**
 * Created by asus1 on 2017/8/23.
 */
public class Goods implements Serializable {
    private String brand;//品牌
    private int categoryId;//分类id
    private String categoryName;//分类名称
    private String description;//描述
    private int id;//商品id
    private String name;//商品名称
    private double price;//原价
    private double proPrice;//促销价
    private String uri;//商品封面图uri
    private double weight;//重量(g)
    /**
     * type : null
     * detail : null
     * total : null
     * isShow : null
     * createTime : 1503290466
     */

    private Object type;
    private Object detail;
    private Object total;
    private Object isShow;
    private int createTime;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getProPrice() {
        return proPrice;
    }

    public void setProPrice(double proPrice) {
        this.proPrice = proPrice;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Object getType() {
        return type;
    }

    public void setType(Object type) {
        this.type = type;
    }

    public Object getDetail() {
        return detail;
    }

    public void setDetail(Object detail) {
        this.detail = detail;
    }

    public Object getTotal() {
        return total;
    }

    public void setTotal(Object total) {
        this.total = total;
    }

    public Object getIsShow() {
        return isShow;
    }

    public void setIsShow(Object isShow) {
        this.isShow = isShow;
    }

    public int getCreateTime() {
        return createTime;
    }

    public void setCreateTime(int createTime) {
        this.createTime = createTime;
    }
}
