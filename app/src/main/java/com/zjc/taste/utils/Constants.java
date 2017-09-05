package com.zjc.taste.utils;

import android.os.Environment;

import com.zjc.taste.api.DevBase;
import com.zjc.taste.app.MApp;


/**
 * 常量类
 *
 * @author Z-2mobo
 */
public class Constants {
    public static final String BASE_SYSTEM_IP = "http://39.108.191.62/e-shop/api/";

    public static final String BASE_IMG_IP = "http://39.108.191.62/e-shop/showImage/";

    public static final String sourceChannel = "android";//来源

    //v=***	第一个1代表是安卓，后面三个代表app版本号
    public static final String BaseVersion = "v=1" + DevBase.getVersionName(MApp.getInstance());
    //v=***	第一个1代表是安卓，后面三个代表app版本号
    public static final String BaseOrgId = "&orgId=T01010103";
    //v=***	第一个1代表是安卓，后面三个代表app版本号
    public static final String BaseProId = "&projId=1";

    /**
     * 路径
     */
    public static final String SDCARD = Environment.getExternalStorageDirectory().getPath() + "/";
    public static final String BASE_DIR = SDCARD + "taste/";
    public static final String DIR_CACHE = BASE_DIR + "cache/";
    public static final String DIR_IMAGE = BASE_DIR + "images/uml";
    public static final String DIR_FRESCO = "taste/fresco";
    public static final String DIR_VIDEO = BASE_DIR + "video/";

    // MD5循环加密次数
    public static final int ENCRYPT_TIMES = 1;

    /**
     * 拍照
     */
    public static final int IMAGE_REQUEST_CODE = 0;
    public static final int CAMERA_REQUEST_CODE = 3;
    public static final int RESULT_REQUEST_CODE = 2;

    /**
     * 二维码扫描后传送到主界面的key
     */
    public static final String SCAN_RESULT = "scan_result";

    public static final String ARGUMENT = "argument";
    public static final String ARGUMENT_TWO = "argument2";
    public static final String PUSH = "jpush";
    public static final String INDEX = "index";
    public static final String TYPE = "type";


    /**
     * 请求码和返回码
     */
    public static final int Statistics_Up = 1;
    public static final int Statistics_Down = 3;
    public static final int Family_Doctor = 2;

    /**
     * 请求码和返回码
     */
    public static final int INTENT_REQUESTCODE = 0;
    public static final int INTENT_RESULTCODE = 1;
    public static final int INTENT_RESULT_CODE_SECOND = 2;

    /**
     * http requestCode
     */
    public static final int REQUEST_CODE_EMPTY = 1;
    public static final int REQUEST_CODE_DIALOG = 2;
    public static final int REQUEST_CODE_SWIPE_REFRESH = 3;


    /**
     * 搜索控制标记
     */
    public static int ACTION_SHOW = 1;
    public static int ACTION_FILTER = 2;
    public static int ACTION_DISMISS = 3;
    public static int ACTION_DELAY_TIME = 100;

    public static String AES_KEY_COMMON = "_8%V&0&^%_?*9y@5";

    public static String AES_KEY_SYSTEM = "_8%V&0&^%_?*9y@r";

    public static String getUrlByResource(String resource) {
        return Constants.BASE_IMG_IP + resource;
    }
}
