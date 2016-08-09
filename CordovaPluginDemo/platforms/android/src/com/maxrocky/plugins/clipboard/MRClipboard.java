package com.maxrocky.plugins.clipboard;
import com.cordova.cordovaplugindemo.app.WDApplication;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONException;

/**
 * 作者：earl on 16/06/19 15:59
 * 邮箱：z604458675@gmail.com
 * 描述：android
 */
public class MRClipboard extends CordovaPlugin {
    private final String CLIPBOARD = "clipboard";

    private CallbackContext callbackContext;

    @Override
    public boolean execute(String action, String rawArgs, CallbackContext callbackContext) throws JSONException {
        this.callbackContext = callbackContext;
        if (action.equals(CLIPBOARD)) {
            if (rawArgs!= null) {
                String objectString = rawArgs.toString();
                WDApplication.instance.clipboard(objectString);
            }
            return true;
        }
        return false;
    }
}
