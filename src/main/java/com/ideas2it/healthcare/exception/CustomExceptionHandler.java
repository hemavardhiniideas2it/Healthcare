package com.ideas2it.healthcare.exception;

import java.util.NoSuchElementException;

import org.springframework.boot.context.config.ConfigDataNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.ideas2it.healthcare.dto.ResponseDTO;

@RestControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseDTO MethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
		ResponseDTO responseDTO = new ResponseDTO();
		responseDTO.setError(HttpStatus.BAD_REQUEST.value(),"Validation Failed for the request");
		return responseDTO;
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ResponseDTO NoSuchElementException(NoSuchElementException ex, WebRequest request) {
		ResponseDTO responseDTO = new ResponseDTO();
		responseDTO.setError(HttpStatus.NOT_FOUND.value(),"No Data found for the request");
		return responseDTO;
	}
	
}
