package com.ninja.wrapper;

public class SuccessfulWrapper {
	protected String message;
	protected String code = "000";
	public String getMessage() {
		return message;
	}
	
	public SuccessfulWrapper(String message) {
		this.message = message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	
}
