package com.cordova.cordovaplugindemo;

import android.os.Bundle;

import org.apache.cordova.CordovaActivity;

/**
 * 作者：earl on 16/04/01 11:16
 * 邮箱：z604458675@gmail.com
 * 描述：android
 */
public class MainActivity extends CordovaActivity {
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        loadUrl(launchUrl);
    }
}