package com.zjc.taste.db.model.main;

import java.io.Serializable;
import java.util.List;

/**
 * Created by asus1 on 2017/8/24.
 */
public class Advertisement implements Serializable {


    /**
     * id : 77
     * type : 4
     * resList : [{"uri":"image/3ed0bb42-d8d2-4b6d-a98d-6bb8b8a25a26","type":1}]
     */

    private int id;
    private int type;
    /**
     * uri : image/3ed0bb42-d8d2-4b6d-a98d-6bb8b8a25a26
     * type : 1
     */

    private List<ResListEntity> resList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<ResListEntity> getResList() {
        return resList;
    }

    public void setResList(List<ResListEntity> resList) {
        this.resList = resList;
    }

    public static class ResListEntity {
        private String uri;
        private int type;

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
