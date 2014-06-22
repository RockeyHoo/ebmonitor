package com.jala.agency.exception;

public class ServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1380420204724797156L;
	
	public ServiceException(Throwable cause) {
		super(cause);		
	}
	
	public ServiceException(String msg) {
		super(msg);		
	}
}
