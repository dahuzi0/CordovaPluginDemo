package com.maxrocky.plugins.alipay;
import java.io.Serializable;
/**
 * 	请求接口返回的——支付宝请求实体类
 * 
 * 		@author MaxRocky
 *
 */
public class PayBody implements Serializable{
	
	private static final long serialVersionUID = 1L;

	// 编码格式  固定值
	public String _input_charset;
	
	// 支付描述
	public String body;
	
	// 服务器异步通知页面路径
	public String notify_url;
	
	// 商户网站唯一订单号
	public String out_trade_no;
	
	// 签约合作者身份ID
	public String partner;
	
	// 支付类型， 固定值
	public String payment_type;
	
	// 签约卖家支付宝账号
	public String seller_id;
	
	// 服务接口名称， 固定值
	public String service;
	
	// 完整的符合支付宝参数规范的订单信息
	public String sign;
	
	// 获取签名方式
	public String sign_type;
	
	//	商品名称
	public String subject;
	
	//	商品金额
	public String total_fee;

}
