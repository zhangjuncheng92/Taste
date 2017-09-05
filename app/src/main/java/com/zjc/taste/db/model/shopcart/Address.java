package com.zjc.taste.db.model.shopcart;

/**
 * Created by asus1 on 2017/9/3.
 */
public class Address {
    private int province;//省id
    private int city;//市id
    private int area;//区id
    private String address;//详细地址

    public int getProvince() {
        return province;
    }

    public void setProvince(int province) {
        this.province = province;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
