package com.zjc.taste.eventbus.notice;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/26.
 * 消息对象
 */
public class Notice implements Serializable {
    private String id;//消息ID
    private String desId;//推送目标
    private int desType;//推送目标类别（0：个体，1：群组）
    private String desTypeDesc;//推送目标类别描述（如上：个体/群组）
    private String phones;//短信推送手机集合,英文逗号隔开（推送类型非短信时，手机号必须输入）
    private String templateId;//短息模板id
    private int pushType;//推送类型（1:极光推送，2：短信推送，3，微信推送，4，web推送）
    private String pushTypeDesc;//推送类型描述（如上：极光推送/短信推送…）
    private String content;//消息内容
    private String title;//推送类型非短信时，标题必须输入
    private String extras;//扩展内容（json格式，自行定义，如：{"id":38,"title":"中医治…","message":""}）
    private String sendTime;//推送时间（格式如：2017-05-12 20:20:56）
    private String modifyTime;//修改时间（格式如：2017-05-12 20:20:56）
    private int state;//消息状态 0未读 1已读
    private int status;//推送状态（0：待推送，1，推送中，1，推送成功，2推送失败）
    private String statusDesc;//推送状态描述（如上：待推送/推送中…）
    private int userId;//创建人ID
    private String serviceId;//业务ID
    private int serviceType;//业务类型
    private String orgId;//组织ID
    private String projId;//项目ID

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesId() {
        return desId;
    }

    public void setDesId(String desId) {
        this.desId = desId;
    }

    public int getDesType() {
        return desType;
    }

    public void setDesType(int desType) {
        this.desType = desType;
    }

    public String getDesTypeDesc() {
        return desTypeDesc;
    }

    public void setDesTypeDesc(String desTypeDesc) {
        this.desTypeDesc = desTypeDesc;
    }

    public String getPhones() {
        return phones;
    }

    public void setPhones(String phones) {
        this.phones = phones;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public int getPushType() {
        return pushType;
    }

    public void setPushType(int pushType) {
        this.pushType = pushType;
    }

    public String getPushTypeDesc() {
        return pushTypeDesc;
    }

    public void setPushTypeDesc(String pushTypeDesc) {
        this.pushTypeDesc = pushTypeDesc;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public int getServiceType() {
        return serviceType;
    }

    public void setServiceType(int serviceType) {
        this.serviceType = serviceType;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }
}
