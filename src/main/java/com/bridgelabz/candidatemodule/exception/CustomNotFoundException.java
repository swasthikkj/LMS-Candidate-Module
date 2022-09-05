package com.bridgelabz.candidatemodule.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class CustomNotFoundException extends RuntimeException {
	private int statuscode;
	private String message;

	public CustomNotFoundException(int statuscode, String message) {
		super(message);
		this.statuscode = statuscode;
		this.message = message;
	}
}
