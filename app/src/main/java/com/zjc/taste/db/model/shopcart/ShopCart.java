package com.zjc.taste.db.model.shopcart;

import java.io.Serializable;

/**
 * Created by asus1 on 2017/8/31.
 */
public class ShopCart implements Serializable {


    /**
     * id : 4
     * goodsId : 4
     * name : 印尼雪燕窝0
     * amount : 1
     * weight : 10.00
     * brand : 云海0
     * price : 300.0
     * proPrice : 100.0
     * description : 长效持久0
     * categoryName : 干燕窝
     */

    private int id;
    private int goodsId;
    private String name;
    private int amount;
    private String weight;
    private String brand;
    private double price;
    private double proPrice;
    private String description;
    private String categoryName;
    private String uri;//商品封面图uri

    private boolean isCheck;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}
