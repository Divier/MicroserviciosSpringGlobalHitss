package com.ccti.account.service;

import com.ccti.account.dto.FullCustomerDTO;

public interface ICustomerService {

	FullCustomerDTO fetchFullCustomer(String email);
}
