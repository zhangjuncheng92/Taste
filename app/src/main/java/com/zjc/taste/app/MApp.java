package com.zjc.taste.app;

import android.os.Environment;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.decoder.SimpleProgressiveJpegConfig;
import com.mobo.mobolibrary.app.BaseApplication;
import com.mobo.mobolibrary.logs.Logs;
import com.qihoo.updatesdk.lib.UpdateHelper;
import com.zjc.taste.R;
import com.zjc.taste.api.HttpUtilsAsync;
import com.zjc.taste.jpush.JPushUtil;
import com.zjc.taste.utils.Constants;
import com.zjc.taste.utils.ImageLoadProxy;

import java.io.File;

import cn.jpush.android.api.JPushInterface;


public class MApp extends BaseApplication {
    private static MApp me;

    public static MApp getInstance() {
        return me;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        me = this;
        setIsDebug(true);
        createFile();
        initFresco();
        ImageLoadProxy.initImageLoader(getApplicationContext());
        JPushUtil.initJPush();
        UpdateHelper.getInstance().init(getApplicationContext(), (getResources().getColor(R.color.comm_blue)));
//        SDKInitializer.initialize(this);
//        DemoHelper.getInstance().init(this,true);


//        System.loadLibrary("gnustl_shared");
////        System.loadLibrary("ijkffmpeg");//目前使用微博的ijkffmpeg会出现1K再换wifi不重连的情况
//        System.loadLibrary("qupai-media-thirdparty");
////        System.loadLibrary("alivc-media-jni");
//        System.loadLibrary("qupai-media-jni");
//        ApplicationGlue.initialize(this);
    }

    private void setIsDebug(boolean b) {
        JPushInterface.setDebugMode(b);    // 设置开启日志,发布时请关闭日志'
        Logs.setsIsLogEnabled(b);
        HttpUtilsAsync.getInstance().setLoggingEnabled(b);
        UpdateHelper.getInstance().setDebugMode(b);
    }

    private void initFresco() {
        //默认图片的磁盘配置
        DiskCacheConfig diskCacheConfig = DiskCacheConfig.newBuilder()
                .setBaseDirectoryPath(Environment.getExternalStorageDirectory().getAbsoluteFile())//缓存图片基路径
                .setBaseDirectoryName(Constants.DIR_FRESCO)//文件夹名
//            .setCacheErrorLogger(cacheErrorLogger)//日志记录器用于日志错误的缓存。
//            .setCacheEventListener(cacheEventListener)//缓存事件侦听器。
//            .setDiskTrimmableRegistry(diskTrimmableRegistry)//类将包含一个注册表的缓存减少磁盘空间的环境。
                .setMaxCacheSize(1024 * 1024 * 100)//默认缓存的最大大小。
                .build();
//
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                .setProgressiveJpegConfig(new SimpleProgressiveJpegConfig())
                .setMainDiskCacheConfig(diskCacheConfig)
                .build();
        Fresco.initialize(this, config);
    }

    private void createFile() {
        File temp = new File(Constants.BASE_DIR);// 自已项目 文件夹
        if (!temp.exists()) {
            temp.mkdir();
        }
        temp = new File(Constants.DIR_CACHE);// 自已项目 文件夹
        if (!temp.exists()) {
            temp.mkdir();
        }
        temp = new File(Constants.DIR_IMAGE);// 自已项目 文件夹
        if (!temp.exists()) {
            temp.mkdir();
        }
        temp = new File(Constants.DIR_FRESCO);// 自已项目 文件夹
        if (!temp.exists()) {
            temp.mkdir();
        }
    }
}
