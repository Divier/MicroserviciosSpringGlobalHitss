package com.ccti.account.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ccti.account.dto.ErrorResponseDTO;

@ControllerAdvice
public class GlobalHandlerException extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException methodArgumentNotValidException,
			HttpHeaders httpHeaders,
			HttpStatusCode httpStatus,
			WebRequest webRequest
			) {
		Map<String, String> validationErrors = new HashMap<>();
		List<ObjectError> validationErrorList = methodArgumentNotValidException.getBindingResult().getAllErrors();
		
		for(ObjectError obError : validationErrorList) {
			String fieldName = ((FieldError) obError).getField();
			String valMessage = obError.getDefaultMessage();
			validationErrors.put(fieldName, valMessage);
		}
		return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDTO> handlerGlobalException(Exception exception, WebRequest webRequest) {
		ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(webRequest.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), LocalDateTime.now());
		return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(CustomerAlreadyExists.class)
	public ResponseEntity<ErrorResponseDTO> handlerCustomerAlreadyExists(CustomerAlreadyExists customerAlreadyExists, WebRequest webRequest) {
		
		ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(webRequest.getDescription(false), HttpStatus.BAD_REQUEST, customerAlreadyExists.getMessage(), LocalDateTime.now());
		return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ResourceNotFound.class)
	public ResponseEntity<ErrorResponseDTO> handlerResourceNotFound(ResourceNotFound resourceNotFound, WebRequest webRequest) {
		ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(webRequest.getDescription(false), HttpStatus.NOT_FOUND, resourceNotFound.getMessage(), LocalDateTime.now());
		return new ResponseEntity<>(errorResponseDTO, HttpStatus.NOT_FOUND);
	}
}
