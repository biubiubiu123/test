package com.open.ccb.ccbdemo;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.provider.SyncStateContract;

import com.ccbsdk.log.CrashHandler;


/**
 * 作者：xuyt on 2018/10/30 10:33
 * 邮箱：android_shitou@163.com
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler.instance(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

    }
}
