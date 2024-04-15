package com.ccti.account.dto;

import jakarta.validation.Valid;
import lombok.Data;

@Data
public class CustomerAccountDTO {

	@Valid
	public CustomerDTO customerDTO;
	@Valid
	public AccountDTO accountDTO;
}
