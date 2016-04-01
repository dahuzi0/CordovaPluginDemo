package com.maxrocky.plugins.alipay;

import java.io.Serializable;

public class PayBodyResponse implements Serializable{
	
	// code码
	public String code;
	
	// 返回信息
	public String msg;
	
	// 支付宝请求实体类
	public PayBody data;
}
