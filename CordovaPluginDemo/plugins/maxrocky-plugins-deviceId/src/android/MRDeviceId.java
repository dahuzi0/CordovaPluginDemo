package com.maxrocky.plugins.deviceId;

import com.cordova.cordovaplugindemo.app.WDApplication;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

public class MRDeviceId extends CordovaPlugin {

    private final String GET_DEVICE_ID = "getDeviceId";

    private CallbackContext callbackContext;

    @Override
    public boolean execute(String action, JSONArray args,
                           CallbackContext callbackContext) throws JSONException {
        this.callbackContext = callbackContext;

        if (action.equals(GET_DEVICE_ID)) {
            callbackContext.success(WDApplication.instance.getDeviceId());
            return true;

        }
        return false;
    }


}
