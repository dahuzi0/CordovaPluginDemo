package com.maxrocky.plugins.alipay;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;

public class AliPay extends CordovaPlugin {
	// 接口地址
	public final String URL = "http://ssyhwx.maxrocky.com:55298/pay/payForTest/1333";
	
	public final String START_TO_ALIPAY = "start_to_alipay";
	private final int SDK_PAY_FLAG = 1001;
	
	private PayBody payBody;
	public CallbackContext callbackContext;
	public Context context;
	
	@Override
	public boolean execute(String action, JSONArray data,CallbackContext callbackContext) throws JSONException {
		this.callbackContext = callbackContext;
		if (action.equals(START_TO_ALIPAY)) {
			pay();
		}
        return true;
	}

	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SDK_PAY_FLAG:
				PayResult payResult = new PayResult((String) msg.obj);
				/**
				 * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
				 * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
				 * docType=1) 建议商户依赖异步通知
				 */
				String resultInfo = payResult.getResult();// 同步返回需要验证的信息

				String resultStatus = payResult.getResultStatus();
				// 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
				if (TextUtils.equals(resultStatus, "9000")) {
					showToast("支付成功");
					callbackContext.success("pay_success");
				} else {
					// 判断resultStatus 为非"9000"则代表可能支付失败
					// "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
					if (TextUtils.equals(resultStatus, "8000")) {
						showToast("支付结果确认中");
						callbackContext.error("");
					} else if(TextUtils.equals(resultStatus, "6001")) {
						showToast("支付已取消");
						callbackContext.error("");
					}else if(TextUtils.equals(resultStatus, "6002")) {
						showToast("连接中断，请稍后再试");
						callbackContext.error("");
					}else {
						// 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
						showToast("支付失败");
						callbackContext.error("支付失败");
					}
				}
				break;
			default:
				break;
			}
		};
	};

	/**
	 * call alipay sdk pay. 调用SDK支付
	 * 
	 */
	public void pay() {
		context = this.cordova.getActivity();
		payBody = new PayBody();
		HttpUtils httpUtils = new HttpUtils(){
			@SuppressLint("NewApi") @Override
			public void Success(String response) {
				if (!response.isEmpty()) {
					Gson gson = new Gson();
					PayBodyResponse res_pb = gson.fromJson(response, PayBodyResponse.class);
					payBody = res_pb.data;
					payFor();
				}
			}
			
			@Override
			public void Error(String error) {
				showToast(error);
			}
		};
		httpUtils.URLGet(URL, null, "utf-8");
	}
	
	/**
	 * 支付请求
	 */
	private void payFor(){
		String orderInfo = getOrderInfo(payBody);
		String sign = payBody.sign;
		try {
			/**
			 * 仅需对sign 做URL编码
			 */
			sign = URLEncoder.encode(sign, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		/**
		 * 完整的符合支付宝参数规范的订单信息
		 */
		final String payInfo = orderInfo + "&sign=\"" + sign + "\"&" + getSignType();

		Runnable payRunnable = new Runnable() {
			@Override
			public void run() {
				// 构造PayTask 对象
				PayTask alipay = new PayTask(cordova.getActivity());
				// 调用支付接口，获取支付结果
				String result = alipay.pay(payInfo, true);

				Message msg = new Message();
				msg.what = SDK_PAY_FLAG;
				msg.obj = result;
				mHandler.sendMessage(msg);
			}
		};

		// 必须异步调用
		Thread payThread = new Thread(payRunnable);
		payThread.start();
	}
	
	/**
	 * create the order info. 创建订单信息
	 * 
	 */
	private String getOrderInfo(PayBody payBody) {

		// 签约合作者身份ID
		String orderInfo = "partner=" + "\"" + payBody.partner + "\"";

		// 签约卖家支付宝账号
		orderInfo += "&seller_id=" + "\"" + payBody.seller_id + "\"";

		// 商户网站唯一订单号
		orderInfo += "&out_trade_no=" + "\"" + payBody.out_trade_no + "\"";

		// 商品名称
		orderInfo += "&subject=" + "\"" + payBody.subject + "\"";

		// 商品详情
		orderInfo += "&body=" + "\"" + payBody.body + "\"";

		// 商品金额
		orderInfo += "&total_fee=" + "\"" + payBody.total_fee + "\"";

		// 服务器异步通知页面路径
		orderInfo += "&notify_url=" + "\"" + payBody.notify_url + "\"";

		// 服务接口名称， 固定值
		orderInfo += "&service=\"" + payBody.service + "\"";

		// 支付类型， 固定值
		orderInfo += "&payment_type=\"" + payBody.payment_type + "\"";

		// 参数编码， 固定值
		orderInfo += "&_input_charset=\"" + payBody._input_charset + "\"";

		// 设置未付款交易的超时时间
		// 默认30分钟，一旦超时，该笔交易就会自动被关闭。
		// 取值范围：1m～15d。
		// m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
		// 该参数数值不接受小数点，如1.5h，可转换为90m。
		// orderInfo += "&it_b_pay=\"30m\"";

		// extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
		// orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

		// 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
		// orderInfo += "&return_url=\"m.alipay.com\"";

		// 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
		// orderInfo += "&paymethod=\"expressGateway\"";

		return orderInfo;
	}

	/**
	 * 获取签名方式
	 * 
	 */
	private String getSignType() {
		return "sign_type=\""+ payBody.sign_type + "\"";
	}
	
	// 弹出toast
	public void showToast(String content) {
		Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
	}
}
