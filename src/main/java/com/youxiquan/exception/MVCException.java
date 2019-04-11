package com.youxiquan.exception;

public class MVCException extends RuntimeException {

	private String retCd ;  //异常对应的返回码
	private String msgDes;  //异常对应的描述信息

	public MVCException() {
		super();
	}

	public MVCException(String message) {
		super(message);
		msgDes = message;
	}

	public MVCException(String retCd, String msgDes) {
		super();
		this.retCd = retCd;
		this.msgDes = msgDes;
	}
 
	public String getRetCd() {
		return retCd;
	}
 
	public String getMsgDes() {
		return msgDes;
	}
}