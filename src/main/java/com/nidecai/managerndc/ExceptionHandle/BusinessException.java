package com.nidecai.managerndc.ExceptionHandle;

@SuppressWarnings("serial")
public class BusinessException extends Exception{
	private int errorCode;

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public BusinessException(int errorCode) {
		super();
		this.errorCode = errorCode;
	}
	public BusinessException(int errorCode,String message) {
		super(message);
		this.errorCode = errorCode;
	}
}
