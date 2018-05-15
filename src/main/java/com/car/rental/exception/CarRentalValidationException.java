package com.car.rental.exception;

import com.car.rental.dto.ErrorCode;

public class CarRentalValidationException extends CarRentalException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CarRentalValidationException(String message, Exception e,ErrorCode errCode) {
		super(message, e, errCode);
	}
	
	public CarRentalValidationException(String message, ErrorCode errCode) {
		super(message,errCode);
	}

	public CarRentalValidationException(Exception e, ErrorCode errCode) {
		super(e,errCode);
	}

}
