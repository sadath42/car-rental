package com.car.rental.exception;

import com.car.rental.dto.ErrorCode;

public class CarRentalException extends RuntimeException {



	final ErrorCode errorCode;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CarRentalException(String message, Exception e, ErrorCode errCode) {
		super(message, e);
		errorCode = errCode;
	}

	public CarRentalException(String message, ErrorCode errCode) {
		super(message);
		errorCode = errCode;
	}

	public CarRentalException(Exception e, ErrorCode errCode) {
		super(e);
		errorCode = errCode;
	}
	
	public ErrorCode getErrorCode() {
		return errorCode;
	}

}
