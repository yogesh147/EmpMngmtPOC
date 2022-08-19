package com.emp.mgnt.custom.exception;
public class InvalidDepartmentException extends RuntimeException {

	private static final long serialVersionUID = -7560701092032901522L;

	public InvalidDepartmentException() {
		super();
	}

	public InvalidDepartmentException(String message) {
		super(message);
	}

	public InvalidDepartmentException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidDepartmentException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidDepartmentException(Throwable cause) {
		super(cause);
	}

}
