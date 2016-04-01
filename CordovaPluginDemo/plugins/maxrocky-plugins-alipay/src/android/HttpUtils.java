package com.maxrocky.plugins.alipay;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;

/**
 * HTTP工具类
 * 
 * @author MaxRocky
 * 
 */
public class HttpUtils {

	/**
	 * 定义编码格式 UTF-8
	 */
	public static final String URL_PARAM_DECODECHARSET_UTF8 = "UTF-8";
	
	/**
	 * 定义编码格式 GBK
	 */
	public static final String URL_PARAM_DECODECHARSET_GBK = "GBK";
	
	private static final String URL_PARAM_CONNECT_FLAG = "&";
	
	private static final String EMPTY = "";

	private static MultiThreadedHttpConnectionManager connectionManager = null;

	private static int connectionTimeOut = 25000;

	private static int socketTimeOut = 25000;

	private static int maxConnectionPerHost = 20;

	private static int maxTotalConnections = 20;

	private static HttpClient client;

	static{
		connectionManager = new MultiThreadedHttpConnectionManager();
		connectionManager.getParams().setConnectionTimeout(connectionTimeOut);
		connectionManager.getParams().setSoTimeout(socketTimeOut);
		connectionManager.getParams().setDefaultMaxConnectionsPerHost(maxConnectionPerHost);
		connectionManager.getParams().setMaxTotalConnections(maxTotalConnections);
		client = new HttpClient(connectionManager);
	}
	
	
	/**
	 * GET方式提交数据
	 * @param url
	 * 			待请求的URL
	 * @param params
	 * 			要提交的数据
	 * @param enc
	 * 			编码
	 * @return
	 * 			响应结果
	 * @throws IOException
	 * 			IO异常
	 */
	public void URLGet(String url, Map<String, String> params, String enc){

		String response = EMPTY;
		GetMethod getMethod = null;		
		StringBuffer strtTotalURL = new StringBuffer(EMPTY);
		
	    if(strtTotalURL.indexOf("?") == -1) {
	      strtTotalURL.append(url).append("?").append(getUrl(params, enc));
	    } else {
	    	strtTotalURL.append(url).append("&").append(getUrl(params, enc));
	    }
	    
		try {
			getMethod = new GetMethod(strtTotalURL.toString());
			getMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=" + enc);
			//执行getMethod
			int statusCode = client.executeMethod(getMethod);
			if(statusCode == HttpStatus.SC_OK) {
				response = getMethod.getResponseBodyAsString();
				Success(response);
			}else{
				Error("网络异常");
			}
		}catch(HttpException e){
			Error("网络异常");
			e.printStackTrace();
		}catch(IOException e){
			Error("网络异常");
			e.printStackTrace();
		}finally{
			if(getMethod != null){
				getMethod.releaseConnection();
				getMethod = null;
			}
		}
	}	

	/**
	 * 据Map生成URL字符串
	 * @param map
	 * 			Map
	 * @param valueEnc
	 * 			URL编码
	 * @return
	 * 			URL
	 */
	private static String getUrl(Map<String, String> map, String valueEnc) {
		
		if (null == map || map.keySet().size() == 0) {
			return (EMPTY);
		}
		StringBuffer url = new StringBuffer();
		Set<String> keys = map.keySet();
		for (Iterator<String> it = keys.iterator(); it.hasNext();) {
			String key = it.next();
			if (map.containsKey(key)) {
				String val = map.get(key);
				String str = val != null ? val : EMPTY;
				try {
					str = URLEncoder.encode(str, valueEnc);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				url.append(key).append("=").append(str).append(URL_PARAM_CONNECT_FLAG);
			}
		}
		String strURL = EMPTY;
		strURL = url.toString();
		if (URL_PARAM_CONNECT_FLAG.equals(EMPTY + strURL.charAt(strURL.length() - 1))) {
			strURL = strURL.substring(0, strURL.length() - 1);
		}
		
		return (strURL);
	}
	
	public void Success(String response){}
	
	public void Error(String error){}
}
