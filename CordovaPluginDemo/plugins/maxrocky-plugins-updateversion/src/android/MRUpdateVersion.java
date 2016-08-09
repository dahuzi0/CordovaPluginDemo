package com.maxrocky.plugins.updateversion;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONException;

/**
 * 作者：earl on 16/06/19 15:59
 * 邮箱：z604458675@gmail.com
 * 描述：android
 */
public class MRUpdateVersion extends CordovaPlugin {
    private final String UPDATEVERSION = "updateVersion";

    private CallbackContext callbackContext;

    @Override
    public boolean execute(String action, String rawArgs, CallbackContext callbackContext) throws JSONException {
        this.callbackContext = callbackContext;
        if (action.equals(UPDATEVERSION)) {
            if (rawArgs!= null) {
                new MyWebViewClient().loadUrl(rawArgs);
            }
            return true;
        }
        return false;
    }
}
