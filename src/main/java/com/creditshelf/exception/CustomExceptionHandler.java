package com.creditshelf.exception;


import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler 
{

	@ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({RecordNotFoundException.class})
    public void handle(RecordNotFoundException e) {}
	
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ConstraintViolationException.class})
    public void handle(ConstraintViolationException e) {}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ValidationException.class})
    public void handle(ValidationException e) {}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({CreditShelfException.class})
    public void handle(CreditShelfException e) {}
	
	
	
}
