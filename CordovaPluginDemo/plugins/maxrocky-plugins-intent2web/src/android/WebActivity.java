package com.maxrocky.plugins.intent2Web;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cordova.cordovaplugindemo.R;


public class WebActivity extends Activity implements OnClickListener {

	private WebView web;
	private TextView tv_Title;
	private LinearLayout btn_Back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web);

		initView();
		setWebSettings();
		initData();
	}


	private void initView() {
		tv_Title = (TextView) findViewById(R.id.head_tv_title);
		btn_Back = (LinearLayout) findViewById(R.id.head_bt_back);
		web = (WebView) findViewById(R.id.web);
	}

	private void setWebSettings() {
		WebSettings settings = web.getSettings();

		settings.setJavaScriptEnabled(true);
		settings.setLayoutAlgorithm(LayoutAlgorithm.NARROW_COLUMNS);
		settings.setLoadWithOverviewMode(true);
		settings.setUseWideViewPort(true);
		settings.setDomStorageEnabled(true);
		web.setWebViewClient(new webViewClient());
		web.setWebChromeClient(new webChrome());
	}

	private void initData() {
		String title = getIntent().getStringExtra(Intent2Web.TITLE);
		String url = getIntent().getStringExtra(Intent2Web.URL);

		//tv_Title.setText(title);
		web.loadUrl(url);
	}
	private class webChrome extends WebChromeClient{
		@Override
		public void onReceivedTitle(WebView view, String title) {
			tv_Title.setText(title);
			super.onReceivedTitle(view, title);
		}
	}
	private class webViewClient extends WebViewClient {
		public boolean shouldOverrideUrlLoading(WebView view, String url) {

			if(url.startsWith("tctravel://")){
				//url.contains()
				//Toast.makeText(getApplicationContext(),"无法加载的url："+url,Toast.LENGTH_SHORT).show();
				Log.d("无法加载的url：", url);
				System.out.print("url===" + url);
			}else{
				view.loadUrl(url);
				//Toast.makeText(getApplicationContext(),"正常的url："+url,Toast.LENGTH_SHORT).show();
				System.out.print("url==="+url);
			}
			return true;
		}


	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.head_bt_back:
			/*if (web.canGoBack()) {
				web.goBack();
			} else {
				finish();
			}*/
				finish();
				break;

			default:
				break;
		}

	}
}
