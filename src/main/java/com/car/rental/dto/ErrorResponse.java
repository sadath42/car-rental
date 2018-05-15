package com.car.rental.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author shanu
 *
 */
@JsonInclude(Include.NON_EMPTY)
public class ErrorResponse {

	final private ErrorCode errorCode;

	private int statusCode;

	private String message;

	List<ValidationError> apiFieldErrors;

	public ErrorResponse(ErrorCode errorCode, String message) {
		super();
		this.errorCode = errorCode;
		this.message = message;
		if (errorCode != null)
			statusCode = errorCode.getStatusCode();
	}

	public ErrorResponse(ErrorCode errorCode, List<ValidationError> apiFieldErrors) {
		this.errorCode = errorCode;
		this.apiFieldErrors = apiFieldErrors;
		if (errorCode != null)
			statusCode = errorCode.getStatusCode();
	}

	public List<ValidationError> getApiFieldErrors() {
		return apiFieldErrors;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
