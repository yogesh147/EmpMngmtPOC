package com.emp.mgnt.custom.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
/**
 * ErrorMessage fields
 */
public class ErrorMessage {

	private int statusCode;
	private Date timestamp;
	private String message;
	private String description;
}
