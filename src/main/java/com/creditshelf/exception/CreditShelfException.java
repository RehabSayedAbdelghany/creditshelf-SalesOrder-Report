package com.creditshelf.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CreditShelfException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	public CreditShelfException(String message) {
        super(message);
    }
}
