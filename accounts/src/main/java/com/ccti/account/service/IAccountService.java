package com.ccti.account.service;

import com.ccti.account.dto.CustomerAccountDTO;
import com.ccti.account.dto.CustomerDTO;

public interface IAccountService {

	void createAccount(CustomerDTO customerDTO);
	CustomerAccountDTO fetchAccount(String email);
	void deleteAccount(String email);
	boolean updateAccount(CustomerAccountDTO accountDTO);
}
