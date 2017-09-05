package com.zjc.taste.jpush;

import com.mobo.mobolibrary.logs.Logs;
import com.zjc.taste.app.MApp;
import com.zjc.taste.db.SharePreferences.SharePreferencesUtil;
import com.zjc.taste.db.model.main.UserInfo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;


/**
 * 推送工具类
 */
public class JPushUtil {

    /**
     * 推送标记
     */
    //极光推送，科室标识
    public static final String JP_DEPT_FLAG = "_D_";
    //极光推送，机构标识
    public static final String JP_ORGANIZATION_FLAG = "O";
    //极光推送，用户标识
    public static final String JP_USER_FLAG = "_U_";

    /**
     * 上线版标记
     */
    //极光推送，科室标识
    public static final String APP_DEBUG = "debug";
    //极光推送，机构标识
    public static final String APP_RELEASE = "release";


    public static void initJPush() {
        //推送
        JPushInterface.init(MApp.getInstance().getApplicationContext());
        setAliasAndTags();
    }

    /**
     * 拼接推送标记
     *
     * @param depart
     * @return
     */
    public static String getJPushTagByDepart(int depart) {
        if (Logs.issIsLogEnabled()) {
            return APP_DEBUG + JP_DEPT_FLAG + depart;
        } else {
            return APP_RELEASE + JP_DEPT_FLAG + depart;
        }
    }

    /**
     * 注册用户id
     *
     * @param userId
     * @return
     */
    public static String getJPushTagByUser(String userId) {
        if (Logs.issIsLogEnabled()) {
            return APP_DEBUG + JP_USER_FLAG + userId;
        } else {
            return APP_RELEASE + JP_USER_FLAG + userId;
        }
    }

    /**
     * 设置别名和标记
     */
    public static void setAliasAndTags() {
        try {
            //调用JPush API设置Alias
            if (SharePreferencesUtil.getInstance().isLogin()) {
                UserInfo userInfo = SharePreferencesUtil.getInstance().readUser();
//                List<HmsDepartment> departments = new ArrayList<>();
//                if (userInfo.getDepts() != null) {
//                    departments.addAll(userInfo.getDepts());
//                }

                //如果登录
                String tag = getJPushTagByUser(userInfo.getId());
                Set<String> tags = new HashSet<>();
//                for (int i = 0; i < departments.size(); i++) {
//                    HmsDepartment department = departments.get(i);
//                    tags.add(JPushUtil.getJPushTagByDepart(department.getId()));
//                    tag = tag + "," + JPushUtil.getJPushTagByDepart(department.getId());
//                }

                if (Logs.issIsLogEnabled()) {
                    tags.add(APP_DEBUG + "_ALL_DOCTOR");
                    tag =tag + "," + APP_DEBUG + "_ALL_DOCTOR";
                } else {
                    tags.add(APP_RELEASE + "_ALL_DOCTOR");
                    tag =tag + "," + APP_RELEASE + "_ALL_DOCTOR";
                }
                JPushInterface.setAliasAndTags(MApp.getInstance().getApplicationContext(), getJPushTagByUser(userInfo.getId()) + "", tags, null);

                SharePreferencesUtil.getInstance().saveAliasAndTags(tag);
            } else {
                //如果未登录，未认证
                JPushInterface.setAliasAndTags(MApp.getInstance().getApplicationContext(), "", new HashSet<String>(), null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
