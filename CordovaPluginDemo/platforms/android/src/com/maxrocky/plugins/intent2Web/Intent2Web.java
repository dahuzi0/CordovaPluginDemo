package com.maxrocky.plugins.intent2Web;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import android.content.Intent;

public class Intent2Web extends CordovaPlugin {

	private final String GO_TO_WEB = "go_to_webactivity";
	public static final String TITLE = "title";
	public static final String URL = "url";

	private CallbackContext callbackContext;

	@Override
	public boolean execute(String action, JSONArray args,
						   CallbackContext callbackContext) throws JSONException {
		this.callbackContext = callbackContext;
		if (action.equals(GO_TO_WEB)) {
			intent2Web(args.getString(0), args.getString(1));
			return true;
		}
		return false;
	}

	// 跳转到webActivity
	private void intent2Web(String title, String url) {
		if (title != null ) {
			if (url != null && !url.isEmpty()) {
				Intent intent = new Intent();
				intent.setClass(cordova.getActivity(), WebActivity.class);
				intent.putExtra(TITLE, title);
				intent.putExtra(URL, url);
				cordova.getActivity().startActivity(intent);
			} else {
				callbackContext.error("url地址不合法");
			}
		} else {
			callbackContext.error("title不合法");
		}
//		cordova.getActivity().finish();
	}

}
