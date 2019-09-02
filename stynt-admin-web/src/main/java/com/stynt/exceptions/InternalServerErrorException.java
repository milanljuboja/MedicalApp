package com.stynt.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerErrorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7688053642236022260L;

	public InternalServerErrorException(String message) {
		super(message);
	}
}
