package com.mobo.mobolibrary.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 服务端返回的Json消息封装类
 *
 * @param <T>
 * @author sharoncn
 */
public class ResultMessage<T> {
    private boolean flag = false;
    private List<T> list = null;// 其他信息
    private int code;
    private String msg;

    public boolean isFlag() {
        if (code == 200) {
            return true;
        }
        return flag;
    }

    public List<T> getResult() {
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    public void setResult(List<T> result) {
        this.list = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
