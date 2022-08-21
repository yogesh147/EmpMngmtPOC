package com.emp.mgnt.custom.exception;

import java.util.Date;

import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Controller Advice Custom file for custom exception handling to whole
 * application
 */
@ControllerAdvice
public class EmployeeCustomException extends ResponseEntityExceptionHandler {

	/**
	 * @param ex      is Invalid Department exception class
	 * @param request is WebRequest for generic web request interceptors, giving
	 *                them access to general request metadata
	 * @return custom exception with Response Entity
	 */
	@ExceptionHandler(InvalidDepartmentException.class)
	public ResponseEntity<ErrorMessage> invalidDepartmentException(InvalidDepartmentException ex, WebRequest request) {

		ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), new Date(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<ErrorMessage>(message, HttpStatus.BAD_REQUEST);
	}

	/**
	 * @param ex      is invalid data access resource usage exception class
	 * @param request is WebRequest for generic web request interceptors, giving
	 *                them access to general request metadata
	 * @return custom exception with Response Entity
	 */
	@ExceptionHandler(InvalidDataAccessResourceUsageException.class)
	public ResponseEntity<ErrorMessage> sExceptionHandler(Exception ex, WebRequest request) {

		ErrorMessage message = new ErrorMessage(HttpStatus.UNPROCESSABLE_ENTITY.value(), new Date(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<ErrorMessage>(message, HttpStatus.UNPROCESSABLE_ENTITY);
	}

	/**
	 * @param ex      is global exception handler exception class
	 * @param request is WebRequest for generic web request interceptors, giving
	 *                them access to general request metadata
	 * @return custom exception with Response Entity
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessage> globalExceptionHandler(Exception ex, WebRequest request) {

		ErrorMessage message = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), new Date(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<ErrorMessage>(message, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}