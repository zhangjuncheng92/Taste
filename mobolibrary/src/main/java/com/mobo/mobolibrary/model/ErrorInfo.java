package com.mobo.mobolibrary.model;

/**
 * Created by Administrator on 2015/6/2.
 */
public class ErrorInfo {
    private String code;
    private String msg;

    public ErrorInfo() {
    }

    public ErrorInfo(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
