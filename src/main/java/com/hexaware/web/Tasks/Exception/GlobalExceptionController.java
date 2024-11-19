package com.hexaware.web.Tasks.Exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionController {
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorDetails> noCarFound(NotFoundException ex){
		ErrorDetails error = new ErrorDetails(LocalDateTime.now(),ex.getMessage(),"location not found ","Not_Found");
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
}
