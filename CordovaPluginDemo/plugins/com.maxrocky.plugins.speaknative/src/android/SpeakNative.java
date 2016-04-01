package com.maxrocky.plugins.speaknative;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;


import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;

public class SpeakNative extends CordovaPlugin{

	// 讯飞APPID
	private final String APP_ID = "56c3d11d";
	private String mEngineType = SpeechConstant.TYPE_CLOUD;
	
	private final String START_TO_SPEAK = "start_to_speak";

	private final String START = "请开始说话...";
	private final String FAILED = "听写失败,错误码：";
	private final String INIT_FAILED = "初始化失败，错误码：";
	private final String SOUND_VOLUME = "当前正在说话，音量大小：";
	private final String SPEAK_START = "开始说话";
	private final String SPEAK_END = "结束说话";

	// 函数调用返回值
	int ret = 0;
	// 语音听写UI
	private RecognizerDialog mIatDialog;
	// 语音听写对象
	SpeechRecognizer mIat = null;
	// 用HashMap存储听写结果
	private HashMap<String, String> mIatResults = new LinkedHashMap<String, String>();
	//返回的结果==声音转成的文字
	private String backString;
	public CallbackContext callbackContext;
	
	
	@Override
	public boolean execute(String action, JSONArray data,
			final CallbackContext callbackContext) throws JSONException {
		this.callbackContext = callbackContext;
		if(action.equals(START_TO_SPEAK)) {
			cordova.getActivity().runOnUiThread(new Runnable(){  
				  
	            @Override  
	            public void run() {  
	                // TODO Auto-generated method stub  
	            	setParams();
	    			startRecognize();
	                Log.i("123", "success=="+backString);
	                
	            }  
	              
	        });
			
		} else {
			destory();
			callbackContext.error("```````");
			return false;
		}
//		callbackContext.success(backString);
        return true;  
	}

	public void startRecognize() {
		mIatResults.clear();
		mIatDialog.setListener(mRecognizerDialogListener);
		mIatDialog.show();
		showToast(START);
		
	}

	/**
	 * 听写监听器。
	 */
	private RecognizerListener mRecognizerListener = new RecognizerListener() {

		@Override
		public void onBeginOfSpeech() {
			// 此回调表示：sdk内部录音机已经准备好了，用户可以开始语音输入
			showToast(SPEAK_START);
		}

		@Override
		public void onError(SpeechError error) {
			// Tips：
			// 错误码：10118(您没有说话)，可能是录音机权限被禁，需要提示用户打开应用的录音权限。
			// 如果使用本地功能（语记）需要提示用户开启语记的录音权限。
			showToast(error.getPlainDescription(true));
		}

		@Override
		public void onEndOfSpeech() {
			// 此回调表示：检测到了语音的尾端点，已经进入识别过程，不再接受语音输入
			showToast(SPEAK_END);
		}

		@Override
		public void onResult(RecognizerResult results, boolean isLast) {
			

			backString=printResult(results);
			if (isLast) {
				// TODO 最后的结果
			}
		}

		@Override
		public void onVolumeChanged(int volume, byte[] data) {
			showToast(SOUND_VOLUME + volume);
		}

		@Override
		public void onEvent(int eventType, int arg1, int arg2, Bundle obj) {
			// 以下代码用于获取与云端的会话id，当业务出错时将会话id提供给技术支持人员，可用于查询会话日志，定位出错原因
			// 若使用本地能力，会话id为null
			// if (SpeechEvent.EVENT_SESSION_ID == eventType) {
			// String sid = obj.getString(SpeechEvent.KEY_EVENT_SESSION_ID);
			// Log.d(TAG, "session id =" + sid);
			// }
		}
	};

	/**
	 * 听写UI监听器
	 */
	private RecognizerDialogListener mRecognizerDialogListener = new RecognizerDialogListener() {
		public void onResult(RecognizerResult results, boolean isLast) {
			backString = printResult(results);
			callbackContext.success(backString);
			Log.i("123", "RecognizerDialogListener=="+backString);
		}
		/**
		 * 识别回调错误.
		 */
		public void onError(SpeechError error) {
			showToast(error + "");
		}
	};

	public String printResult(RecognizerResult results) {
		
		String text=JsonParser.parseIatResult(results.getResultString());

		String sn = null;
		// 读取json结果中的sn字段
		try {
			JSONObject resultJson = new JSONObject(results.getResultString());
			sn = resultJson.optString("sn");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		mIatResults.put(sn, text);

		StringBuffer resultBuffer = new StringBuffer();
		for (String key : mIatResults.keySet()) {
			resultBuffer.append(mIatResults.get(key));
		}

		return resultBuffer.toString();
	}

	/**
	 * 初始化监听器。
	 */
	private InitListener mInitListener = new InitListener() {

		@Override
		public void onInit(int code) {
			if (code != ErrorCode.SUCCESS) {
				showToast(INIT_FAILED + code);
			}
		}
	};

	/**
	 * 参数设置
	 * 
	 * @param param
	 * @return
	 */
	public void setParams() {
		 Context context = this.cordova.getActivity();
		SpeechUtility.createUtility(context, SpeechConstant.APPID + "="
				+ APP_ID + ",engine_mode=auto");
		mIat = SpeechRecognizer.createRecognizer(context, mInitListener);
		mIatDialog = new RecognizerDialog(cordova.getActivity(), mInitListener);
		
		
		

		// 清空参数
		mIat.setParameter(SpeechConstant.PARAMS, null);

		// 设置听写引擎
		mIat.setParameter(SpeechConstant.ENGINE_TYPE, mEngineType);
		// 设置返回结果格式
		mIat.setParameter(SpeechConstant.RESULT_TYPE, "json");
		
		// 设置语言
		mIat.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
		// 设置语言区域
		mIat.setParameter(SpeechConstant.ACCENT, "mandarin");

		// 设置语音前端点:静音超时时间，即用户多长时间不说话则当做超时处理
		mIat.setParameter(SpeechConstant.VAD_BOS, "4000");

		// 设置语音后端点:后端点静音检测时间，即用户停止说话多长时间内即认为不再输入， 自动停止录音
		mIat.setParameter(SpeechConstant.VAD_EOS, "1000");

		// 设置标点符号,设置为"0"返回结果无标点,设置为"1"返回结果有标点
		mIat.setParameter(SpeechConstant.ASR_PTT, "1");

		// 设置音频保存路径，保存音频格式支持pcm、wav，设置路径为sd卡请注意WRITE_EXTERNAL_STORAGE权限
		// 注：AUDIO_FORMAT参数语记需要更新版本才能生效
		mIat.setParameter(SpeechConstant.AUDIO_FORMAT, "wav");
		mIat.setParameter(SpeechConstant.ASR_AUDIO_PATH,
				Environment.getExternalStorageDirectory() + "/msc/iat.wav");
	}

	// 弹出toast
	private void showToast(String content) {
		 Toast.makeText(cordova.getActivity(), content, Toast.LENGTH_SHORT).show();
		  
		
	}

	// 清空
	public void destory() {
		mIat.cancel();
		mIat.destroy();
	}
}

