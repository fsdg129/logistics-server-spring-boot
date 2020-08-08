package com.yaozuw.logistics_server.controller;

public class ResponseTemplate {
	public String status;
	public String message;
	public int errorCode;
	public Object payload;
	
	public ResponseTemplate(String status, String message, int errorCode, Object payload) {
		this.status = status;
		this.message = message;
		this.errorCode = errorCode;
		this.payload = payload;
	}
	
}
