package com.ccti.cards.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CardAlreadyExists extends RuntimeException {

	public CardAlreadyExists(String message) {
	
		super(message);
	}
}
