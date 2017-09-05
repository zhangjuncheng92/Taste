package com.zjc.taste.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;

import com.facebook.drawee.view.SimpleDraweeView;
import com.mobo.mobolibrary.util.image.FrescoImageLoader;
import com.zjc.taste.R;
import com.zjc.taste.db.SharePreferences.SharePreferencesUtil;
import com.zjc.taste.utils.Constants;


public class WelcomeActivity extends Activity {
    private SimpleDraweeView sdBg;
    private static final long WAIT_TIME = 2000;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_welcome_act);
        init();
    }

    private void init() {
        try {
//            //初始化城市选择
//            SharePreferencesUtil.getInstance().saveCity(new City("1", "武汉市", new LatLngLocal(30.543622, 114.433890)));

            PackageManager pm = this.getPackageManager();//context为当前Activity上下文
            PackageInfo pi = pm.getPackageInfo(this.getPackageName(), 0);
            int curVersionCode = pi.versionCode;
            int oldVersionCode = SharePreferencesUtil.getInstance().getVersionCode();
            if (curVersionCode != oldVersionCode) {
                //更新了应用
//                SharePreferencesUtil.getInstance().saveConfigChoose(MainFunction.initMainConfig(getApplication()));
                SharePreferencesUtil.getInstance().setVersionCode(curVersionCode);
                SharePreferencesUtil.getInstance().setLogin(false);
            }

            sdBg = (SimpleDraweeView) findViewById(R.id.welcome_bg);
            FrescoImageLoader.getInstance().displayImage(sdBg, Constants.BASE_SYSTEM_IP + "hms/static/images/welcome.png");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } finally {
            toActvity(MainActivity.class);
        }
    }

    private void toActvity(final Class<?> target) {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                final Intent intent = new Intent(WelcomeActivity.this, target);
                startActivity(intent);
                finish();
            }
        }, WAIT_TIME);
    }
}
