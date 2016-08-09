package com.cordova.cordovaplugindemo;

import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import com.cordova.cordovaplugindemo.app.WDApplication;

import org.apache.cordova.CordovaActivity;

/**
 * 作者：earl on 16/04/01 11:16
 * 邮箱：z604458675@gmail.com
 * 描述：android
 */
public class MainActivity extends CordovaActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WDApplication.instance = this;
        loadUrl(launchUrl);
    }

    /**
     * 拷贝到剪切板
     */
    public void clipboard(String url) {
        ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        cm.setText(url);
        Toast.makeText(this, "已复制，长按输入框可粘贴", Toast.LENGTH_LONG).show();
    }

    public String getDeviceId() {
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String deviceId = tm.getDeviceId();
        return deviceId;
    }
}