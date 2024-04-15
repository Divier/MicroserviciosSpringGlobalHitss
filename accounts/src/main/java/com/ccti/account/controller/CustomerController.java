package com.ccti.account.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ccti.account.dto.FullCustomerDTO;
import com.ccti.account.service.ICustomerService;

import jakarta.validation.constraints.Email;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Validated
@RestController
@RequestMapping(path = "/api", produces = { MediaType.APPLICATION_JSON_VALUE })
//@AllArgsConstructor
@RequiredArgsConstructor
public class CustomerController {

	@NonNull
	private final ICustomerService customerService;

	@GetMapping("/fetchFullCustomer")
	public ResponseEntity<FullCustomerDTO> fetchFullCustomer(
			@RequestParam @Email(message = "Debe especificar un email valido") String email) {

		FullCustomerDTO fullCustomerDto = customerService.fetchFullCustomer(email);

		return ResponseEntity.status(HttpStatus.OK).body(fullCustomerDto);
	}
}
