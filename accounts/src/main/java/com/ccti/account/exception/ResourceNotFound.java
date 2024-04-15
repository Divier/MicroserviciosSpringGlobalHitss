package com.ccti.account.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class ResourceNotFound extends RuntimeException {
	
	public ResourceNotFound(String resoucreName, String fieldName, String fieldValue) {
		super(String.format("No se encontro ningun %s con el valor %s en el campo %s", resoucreName, fieldValue, fieldName));
	}
}
