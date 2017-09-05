package com.zjc.taste.db.SharePreferences;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.mobo.mobolibrary.util.Util;
import com.zjc.taste.app.MApp;
import com.zjc.taste.db.model.main.Advertisement;
import com.zjc.taste.db.model.main.MainGoods;
import com.zjc.taste.db.model.main.UserInfo;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2015/7/24.
 */
public class SharePreferencesUtil {
    private static SharePreferencesUtil me;

    public static SharePreferencesUtil getInstance() {
        if (me == null) {
            me = new SharePreferencesUtil();
        }
        return me;
    }

    public Application getApplication() {
        return MApp.getInstance();
    }


    public void savePwd(String pwd) {
        Util.writePreferences(getApplication(), "password", pwd);
    }

    public String readPwd() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplication());
        return sp.getString("password", "");
    }

    public void removePwd() {
        Util.removePreferences(getApplication(), "password");
    }

    public void saveUserName(String phone) {
        Util.writePreferences(getApplication(), "userName", phone);
    }

    public String readPhone() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplication());
        return sp.getString("userName", "");
    }

    public void setLogin(boolean isLogin) {
        Util.writePreferences(getApplication(), "login", isLogin);
    }

    public boolean isLogin() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplication());
        return sp.getBoolean("login", false);
    }

    public void setAuthenticationStatus(boolean isLogin) {
        Util.writePreferences(getApplication(), "AuthenticationStatus", isLogin);
    }

    public boolean isAuthenticationStatus() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplication());
        return sp.getBoolean("AuthenticationStatus", false);
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    public UserInfo readUser() {
        UserInfo userInfo = new UserInfo();
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplication());
        String string2obj = sp.getString("userInfo", "");
        if (!TextUtils.isEmpty(string2obj)) {
            try {
                userInfo = (UserInfo) SerializableUtil.str2Obj(string2obj);
            } catch (IOException e) {
                e.printStackTrace();
                return userInfo;
            }
        }
        return userInfo;
    }

    /**
     * 保存用户信息
     *
     * @return
     */
    public void saveUser(UserInfo userInfo) {
        try {
            String obj2Str = SerializableUtil.obj2Str(userInfo);
            Util.writePreferences(getApplication(), "userInfo", obj2Str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    /**
//     * 保存首页医院搜索历史信息
//     *
//     * @return
//     */
//    public void saveSearchHostoryList2(List<SearchHistory> searchHistories) {
//        try {
//            String obj2Str = SerializableUtil.list2String(searchHistories);
//            Util.writePreferences(getApplication(), "searchHistories", obj2Str);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 获取首页医院搜索历史信息
//     *
//     * @return
//     */
//    public List<SearchHistory> getSearchHostoryList2() {
//        List<SearchHistory> list = new ArrayList<>();
//        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplication());
//        String stringList = sp.getString("searchHistories", "");
//        if (stringList != "") {
//            try {
//                list = (List<SearchHistory>) SerializableUtil.string2List(stringList);
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
//        return list;
//    }
//
//    /**
//     * 保存全站搜索历史信息
//     *
//     * @return
//     */
//    public void saveSearchHistoryFull(List<SearchHistory> searchHistories) {
//        try {
//            String obj2Str = SerializableUtil.list2String(searchHistories);
//            Util.writePreferences(getApplication(), "searchHistoryFull", obj2Str);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 获取全站搜索历史信息
//     *
//     * @return
//     */
//    public List<SearchHistory> getSearchHistoryFull() {
//        List<SearchHistory> list = new ArrayList<>();
//        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplication());
//        String stringList = sp.getString("searchHistoryFull", "");
//        if (stringList != "") {
//            try {
//                list = (List<SearchHistory>) SerializableUtil.string2List(stringList);
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
//        return list;
//    }
//
//    /**
//     * 删除全站搜索历史信息
//     *
//     * @return
//     */
//    public void removeSearchHistoryFull() {
//        try {
//            Util.removePreferences(getApplication(), "searchHistoryFull");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    /**
//     * 保存首页医院搜索历史信息
//     *
//     * @return
//     */
//    public void saveSearchTreatment(List<SearchHistory> searchHistories) {
//        try {
//            String obj2Str = SerializableUtil.list2String(searchHistories);
//            Util.writePreferences(getApplication(), "SearchTreatment", obj2Str);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 获取首页医院搜索历史信息
//     *
//     * @return
//     */
//    public List<SearchHistory> getSearchTreatment() {
//        List<SearchHistory> list = new ArrayList<>();
//        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplication());
//        String stringList = sp.getString("SearchTreatment", "");
//        if (stringList != "") {
//            try {
//                list = (List<SearchHistory>) SerializableUtil.string2List(stringList);
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
//        return list;
//    }
//
//
//    /**
//     * 保存ICD10搜索历史信息
//     */
//    public void saveSearchHistoryICD10(List<SearchHistory> searchHistories) {
//        try {
//            String obj2Str = SerializableUtil.list2String(searchHistories);
//            Util.writePreferences(getApplication(), "ICD10", obj2Str);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 获取ICD10搜索历史信息
//     */
//    public List<SearchHistory> getSearchHistoryICD10() {
//        List<SearchHistory> list = new ArrayList<>();
//        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplication());
//        String stringList = sp.getString("ICD10", "");
//        if (stringList != "") {
//            try {
//                list = (List<SearchHistory>) SerializableUtil.string2List(stringList);
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
//        return list;
//    }
//
//    /**
//     * 保存搜索上转转诊单历史信息
//     *
//     * @return
//     */
//    public void saveSearchReferralUp(List<SearchHistory> searchHistories) {
//        try {
//            String obj2Str = SerializableUtil.list2String(searchHistories);
//            Util.writePreferences(getApplication(), "SearchReferralUp", obj2Str);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 获取搜索上转转诊单历史信息
//     *
//     * @return
//     */
//    public List<SearchHistory> getSearchReferralUp() {
//        List<SearchHistory> list = new ArrayList<>();
//        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplication());
//        String stringList = sp.getString("SearchReferralUp", "");
//        if (stringList != "") {
//            try {
//                list = (List<SearchHistory>) SerializableUtil.string2List(stringList);
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
//        return list;
//    }



    /**
     * 删除ICD10搜索历史信息
     */
    public void removeSearchHistoryICD10() {
        try {
            Util.removePreferences(getApplication(), "ICD10");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 得到存储的版本号
     */
    public int getVersionCode() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplication());
        return sp.getInt("versionCode", 0);
    }

    /**
     * 设置存储的版本号
     */
    public void setVersionCode(int versionCode) {
        Util.writePreferences(getApplication(), "versionCode", versionCode);
    }

    //保存首页广告到本地
    public void saveHomeAdvertisement(List<Advertisement> advertisements) {
        try {
            String list2String = SerializableUtil.list2String(advertisements);
            Util.writePreferences(getApplication(), "Advertisement", list2String);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //读取本地的首页广告
    public List<Advertisement> readMainGoods() {
        List<Advertisement> advertisements = null;
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplication());
        String string2List = sp.getString("Advertisement", "");
        if (!TextUtils.isEmpty(string2List)) {
            try {
                advertisements = (List<Advertisement>) SerializableUtil.string2List(string2List);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return advertisements;
    }



    public void saveStatisticsKey(String key) {
        Util.writePreferences(getApplication(), "key", key);
    }

    public String readStatisticsKey() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplication());
        return sp.getString("key", "");
    }

    /**
     * 保存极光注册别名和标签
     */
    public void saveAliasAndTags(String aliasAndTags) {
        Util.writePreferences(getApplication(), "aliasAndTags", aliasAndTags);
    }

    public String readAliasAndTags() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplication());
        return sp.getString("aliasAndTags", "");
    }

}
