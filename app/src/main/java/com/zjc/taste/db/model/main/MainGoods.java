package com.zjc.taste.db.model.main;

import com.zjc.taste.db.model.goods.Goods;
import com.zjc.taste.db.model.wedding.Wedding;

import java.util.ArrayList;

/**
 * Created by asus1 on 2017/8/23.
 */
public class MainGoods {
    /**
     * 分类id
     */
    private int categoryId;
    /**
     * 分类名称
     */
    private String categoryName;


    private int type;

    private ArrayList<Goods> goodsList;

    private ArrayList<Wedding> weddingList;

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

    public ArrayList<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(ArrayList<Goods> goodsList) {
        this.goodsList = goodsList;
    }

    public ArrayList<Wedding> getWeddingList() {
        return weddingList;
    }

    public void setWeddingList(ArrayList<Wedding> weddingList) {
        this.weddingList = weddingList;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
