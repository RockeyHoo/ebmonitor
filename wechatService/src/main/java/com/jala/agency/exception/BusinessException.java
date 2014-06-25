package com.jala.agency.exception;

public class BusinessException extends RuntimeException {
	
	private static final long serialVersionUID = 333420192708940966L;

	@SuppressWarnings("unused")
	private int code;
	
	public BusinessException(int code,String message) {
		super(message);
        this.code = code;
    }
	
	public BusinessException(String message) {
        super(message);
    }
	
	public BusinessException(Throwable cause) {
		super(cause);
	}
	
	public BusinessException(int code,Throwable cause) {
		super(cause);
		this.code = code;
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public BusinessException(int code,String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}
	
}
