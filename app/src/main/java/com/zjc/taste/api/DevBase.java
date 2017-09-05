package com.zjc.taste.api;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;

import com.google.gson.JsonObject;
import com.zjc.taste.app.MApp;
import com.zjc.taste.db.SharePreferences.SharePreferencesUtil;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2016/10/21.
 */
public class DevBase {
    public static String getAll() {
        String userId = "";
        String token = "";
        String doctorUuId = "";
        String curOrgCode = "";


        String imei = ((TelephonyManager) MApp.getInstance().getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId(); //手机序列号。
        String networkInfo = GetNetworkType();//网络状态
        String imsi = ((TelephonyManager) MApp.getInstance().getSystemService(Context.TELEPHONY_SERVICE)).getSubscriberId();//获取运营商sim卡imsi号
        String mac = (((WifiManager) MApp.getInstance().getSystemService(Context.WIFI_SERVICE)).getConnectionInfo()).getMacAddress(); //手机Mac地址。
        String version = "1" + getVersionName(MApp.getInstance());//版本1.6.0
        String phoneMode = android.os.Build.MODEL;//手机型号
        String os = android.os.Build.VERSION.RELEASE;//操作系统IOS:8.4.1   android:5.0.1
        String channelId = readFromAssets();//渠道ID

        JsonObject postRequest = new JsonObject();
        if (SharePreferencesUtil.getInstance().isLogin()) {
            postRequest.addProperty("userId", userId);
            postRequest.addProperty("token", token);
            postRequest.addProperty("uuid", doctorUuId);
            postRequest.addProperty("curOrgCode", curOrgCode);
        }

        postRequest.addProperty("imei", imei);
        postRequest.addProperty("networkInfo", networkInfo);
        postRequest.addProperty("channelId", channelId);
        postRequest.addProperty("imsi", imsi);
        postRequest.addProperty("mac", mac);
        postRequest.addProperty("version", version);
        postRequest.addProperty("phoneMode", phoneMode);
        postRequest.addProperty("os", os);
        postRequest.addProperty("userType", 2);//用户类型1，居民2，医生
        postRequest.addProperty("orgId", "T01010103");
        postRequest.addProperty("projId", "1");
        //是否为测试版（0：debug，1：release）
        postRequest.addProperty("isDebug", HttpUtilsAsync.getInstance().isLoggingEnabled() ? 1 : 0);

        return postRequest.toString();
    }


    public static String readFromAssets() {
        try {
            InputStream is = MApp.getInstance().getAssets().open("multi_channel_packaging");
//            String text = readTextFromSDcard(is);
            return readTextFromSDcard(is);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "-1";
    }

    public static String readTextFromSDcard(InputStream is) throws Exception {
        InputStreamReader reader = new InputStreamReader(is);
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuffer buffer = new StringBuffer("");
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            buffer.append(str);
//            buffer.append("\n");
        }
        return buffer.toString();
    }


    public static String GetNetworkType() {
        String strNetworkType = "";
        NetworkInfo networkInfo = ((ConnectivityManager) MApp.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                strNetworkType = "WIFI";
            } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                String _strSubTypeName = networkInfo.getSubtypeName();
//                Log.e("cocos2d-x", "Network getSubtypeName : " + _strSubTypeName);
                // TD-SCDMA   networkType is 17
                int networkType = networkInfo.getSubtype();
                switch (networkType) {
                    case TelephonyManager.NETWORK_TYPE_GPRS:
                    case TelephonyManager.NETWORK_TYPE_EDGE:
                    case TelephonyManager.NETWORK_TYPE_CDMA:
                    case TelephonyManager.NETWORK_TYPE_1xRTT:
                    case TelephonyManager.NETWORK_TYPE_IDEN: //api<8 : replace by 11
                        strNetworkType = "2G";
                        break;
                    case TelephonyManager.NETWORK_TYPE_UMTS:
                    case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    case TelephonyManager.NETWORK_TYPE_HSDPA:
                    case TelephonyManager.NETWORK_TYPE_HSUPA:
                    case TelephonyManager.NETWORK_TYPE_HSPA:
                    case TelephonyManager.NETWORK_TYPE_EVDO_B: //api<9 : replace by 14
                    case TelephonyManager.NETWORK_TYPE_EHRPD:  //api<11 : replace by 12
                    case TelephonyManager.NETWORK_TYPE_HSPAP:  //api<13 : replace by 15
                        strNetworkType = "3G";
                        break;
                    case TelephonyManager.NETWORK_TYPE_LTE:    //api<11 : replace by 13
                        strNetworkType = "4G";
                        break;
                    default:
                        // http://baike.baidu.com/item/TD-SCDMA 中国移动 联通 电信 三种3G制式
                        if (_strSubTypeName.equalsIgnoreCase("TD-SCDMA") || _strSubTypeName.equalsIgnoreCase("WCDMA") || _strSubTypeName.equalsIgnoreCase("CDMA2000")) {
                            strNetworkType = "3G";
                        } else {
                            strNetworkType = _strSubTypeName;
                        }
                        break;
                }
//                Log.e("cocos2d-x", "Network getSubtype : " + Integer.valueOf(networkType).toString());
            }
        }
//        Log.e("cocos2d-x", "Network Type : " + strNetworkType);
        return strNetworkType;
    }


    //版本号
    public static int getVersionCode(Context context) {
        return getPackageInfo(context).versionCode;
    }

    //版本名
    public static String getVersionName(Context context) {
        return getPackageInfo(context).versionName.replace(".", "");
    }

    private static PackageInfo getPackageInfo(Context context) {
        PackageInfo pi = null;
        try {
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);

            return pi;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pi;
    }

    public static String getToken() {
        return SharePreferencesUtil.getInstance().readUser().getToken();
    }

}
