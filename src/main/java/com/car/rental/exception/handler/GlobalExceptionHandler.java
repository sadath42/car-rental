package com.car.rental.exception.handler;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.car.rental.dto.ErrorCode;
import com.car.rental.dto.ErrorResponse;
import com.car.rental.dto.ValidationError;
import com.car.rental.exception.CarRentalException;
import com.car.rental.exception.CarRentalValidationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(CarRentalException.class)
	public ResponseEntity<ErrorResponse> handleProplinException(CarRentalException exception) {
		LOGGER.error("Internal error  {}", exception);
		ErrorResponse errorResponse = new ErrorResponse(exception.getErrorCode(), exception.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleUnhandledException(Exception exception) {
		LOGGER.error("Unhandled exception {}", exception);
		ErrorResponse errorResponse = new ErrorResponse(ErrorCode.INTERNAL_ERROR, "Internal server error");
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(DataAccessException.class)
	public ResponseEntity<ErrorResponse> handleDataIntegrity(DataAccessException exception) {
		LOGGER.error("DataAccessException exception {}", exception);
		ErrorResponse errorResponse = new ErrorResponse(ErrorCode.INTERNAL_ERROR, exception.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorResponse> httpMessageNotReadableException(final HttpMessageNotReadableException e) {
		LOGGER.error("Json mapping exception  {}", e);
		ErrorResponse errorResponse = new ErrorResponse(ErrorCode.INVALID_JSON, e.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(CarRentalValidationException.class)
	public ResponseEntity<ErrorResponse> proliValidationException(final CarRentalValidationException e) {
		LOGGER.error("Prolin validationException {}", e);
		ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode(), e.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorResponse> handleIllegalArgumentException(final IllegalArgumentException e) {
		LOGGER.error("Prolin validationException {}", e);
		ErrorResponse errorResponse = new ErrorResponse(ErrorCode.BAD_REQUEST, e.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	

	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(MethodArgumentNotValidException exception) {
		LOGGER.error("Validation failure  {}", exception);
		BindingResult bindingResult = exception.getBindingResult();
		List<ValidationError> apiFieldErrors = bindingResult
				.getFieldErrors().stream().map(fieldError -> new ValidationError(fieldError.getField(),
						fieldError.getCode(), fieldError.getRejectedValue(), fieldError.getDefaultMessage()))
				.collect(Collectors.toList());
		List<ValidationError> globalErrors = bindingResult.getGlobalErrors().stream()
				.map(globalError -> new ValidationError(globalError.getObjectName(), globalError.getDefaultMessage()))
				.collect(Collectors.toList());
		
		apiFieldErrors.addAll(globalErrors);

		ErrorResponse errorResponse = new ErrorResponse(ErrorCode.INVALID_JSON,apiFieldErrors);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}


}
