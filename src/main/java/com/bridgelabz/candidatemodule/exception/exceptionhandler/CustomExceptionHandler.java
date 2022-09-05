package com.bridgelabz.candidatemodule.exception.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bridgelabz.candidatemodule.exception.CustomNotFoundException;
import com.bridgelabz.candidatemodule.util.Response;
@ControllerAdvice
public class CustomExceptionHandler {
	@ExceptionHandler(CustomNotFoundException.class)
	public ResponseEntity<Response> handleId(CustomNotFoundException ab) {
		Response response = new Response();
		response.setErrorcode(400);
		response.setMessage(ab.getMessage());
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
