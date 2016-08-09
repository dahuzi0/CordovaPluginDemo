package com.cordova.cordovaplugindemo.app;

import android.app.Application;
import android.content.Context;

import com.cordova.cordovaplugindemo.MainActivity;


/**
 * 作者：earl on 16/05/14 14:25
 * 邮箱：z604458675@gmail.com
 * 描述：android
 */
public class WDApplication extends Application {
    private static Context context;
    /* 持有私有静态实例，防止被引用，此处赋值为null，目的是实现延迟加载 */
    public static MainActivity instance = null;

    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }

}
