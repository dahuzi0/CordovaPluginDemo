package com.maxrocky.plugins.updateversion;

import android.content.Intent;
import android.net.Uri;
import android.webkit.WebViewClient;

import com.cordova.cordovaplugindemo.app.WDApplication;


/**
 * 作者：earl on 16/03/10 21:38
 * 邮箱：z604458675@gmail.com
 * 描述：android  跳转到浏览器
 */
public class MyWebViewClient extends WebViewClient {

    public boolean loadUrl(String url) {
        url = url.substring(url.indexOf("\"") + 1, url.lastIndexOf("\""));
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        WDApplication.instance.startActivity(intent);
        return true;
    }
}
