package com.jala.agency.exception;

import org.springframework.dao.DataAccessException;

public class DaoException extends DataAccessException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8060714304038052111L;


	public DaoException(String msg) {

		super(msg);

	}

	public DaoException(String msg, Throwable cause) {

		super(msg, cause);

	}

}
