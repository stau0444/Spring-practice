package com.ugo.practice.exception;

import org.springframework.dao.DataAccessException;

public class OutOfStockException extends DataAccessException {

	public OutOfStockException(String msg) {
		super(msg);
	}

}
