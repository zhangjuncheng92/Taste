package com.zjc.taste.db.model.goods;

import java.io.Serializable;
import java.util.List;

/**
 * Created by asus1 on 2017/9/2.
 */
public class GoodsDetail implements Serializable {


    /**
     * id : 67
     * name : 12313
     * brand : 132
     * price : 31.0
     * proPrice : 1231.0
     * unit : 0
     * weight : 23.0
     * amount : 0
     * description :
     * mark : adf
     * createTime : 1503923814
     * createUser : 0
     * updateTime : 1504190209
     * updateUser : 0
     * isDelete : 0
     * flag : 0
     * version : 0
     * imageList : [{"id":97,"goodsId":null,"imageId":null,"type":1,"order":1,"name":"E-支付成功.jpg","uri":"image\\c9769ee0-91a2-467e-b1ce-e489a6ec7764","size":649},{"id":98,"goodsId":null,"imageId":null,"type":2,"order":2,"name":"E-支付成功.jpg","uri":"image\\e8b6e423-7376-460a-b246-a00416418be6","size":649}]
     * specList : []
     * category : {"id":1,"name":"干燕窝","pid":0,"level":null,"isShow":1}
     */

    private int id;
    private String name;
    private String brand;
    private double price;
    private double proPrice;
    private String unit;
    private double weight;
    private int amount;
    private String description;
    private String mark;
    private int createTime;
    private int createUser;
    private int updateTime;
    private int updateUser;
    private int isDelete;
    private int flag;
    private int version;
    /**
     * id : 1
     * name : 干燕窝
     * pid : 0
     * level : null
     * isShow : 1
     */

    private CategoryEntity category;
    /**
     * id : 97
     * goodsId : null
     * imageId : null
     * type : 1
     * order : 1
     * name : E-支付成功.jpg
     * uri : image\c9769ee0-91a2-467e-b1ce-e489a6ec7764
     * size : 649
     */

    private List<ImageListEntity> imageList;
    /**
     * 封面图片
     */
    private List<ImageListEntity> coverImageList;
    /**
     * 详情图片
     */
    private List<ImageListEntity> detailImageList;
    /**
     * 轮播图片
     */
    private List<ImageListEntity> commonImageList;
    private List<?> specList;

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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public int getCreateTime() {
        return createTime;
    }

    public void setCreateTime(int createTime) {
        this.createTime = createTime;
    }

    public int getCreateUser() {
        return createUser;
    }

    public void setCreateUser(int createUser) {
        this.createUser = createUser;
    }

    public int getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(int updateTime) {
        this.updateTime = updateTime;
    }

    public int getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(int updateUser) {
        this.updateUser = updateUser;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public List<ImageListEntity> getImageList() {
        return imageList;
    }

    public void setImageList(List<ImageListEntity> imageList) {
        this.imageList = imageList;
    }

    public List<ImageListEntity> getCoverImageList() {
        return coverImageList;
    }

    public void setCoverImageList(List<ImageListEntity> coverImageList) {
        this.coverImageList = coverImageList;
    }

    public List<ImageListEntity> getDetailImageList() {
        return detailImageList;
    }

    public void setDetailImageList(List<ImageListEntity> detailImageList) {
        this.detailImageList = detailImageList;
    }

    public List<ImageListEntity> getCommonImageList() {
        return commonImageList;
    }

    public void setCommonImageList(List<ImageListEntity> commonImageList) {
        this.commonImageList = commonImageList;
    }

    public List<?> getSpecList() {
        return specList;
    }

    public void setSpecList(List<?> specList) {
        this.specList = specList;
    }

    public static class CategoryEntity {
        private int id;
        private String name;
        private int pid;
        private Object level;
        private int isShow;

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

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public Object getLevel() {
            return level;
        }

        public void setLevel(Object level) {
            this.level = level;
        }

        public int getIsShow() {
            return isShow;
        }

        public void setIsShow(int isShow) {
            this.isShow = isShow;
        }
    }

    public static class ImageListEntity {
        private int id;
        private int goodsId;
        private int imageId;
        private int type;
        private int order;
        private String name;
        private String uri;
        private int size;

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

        public int getImageId() {
            return imageId;
        }

        public void setImageId(int imageId) {
            this.imageId = imageId;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }
    }
}
