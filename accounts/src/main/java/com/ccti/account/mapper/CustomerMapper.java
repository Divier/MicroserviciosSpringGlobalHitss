package com.ccti.account.mapper;

import com.ccti.account.dto.CustomerDTO;
import com.ccti.account.entity.Customer;

public class CustomerMapper {

	public static CustomerDTO mapCustomerToDto(Customer customer) {
		
		CustomerDTO customerDto = new CustomerDTO();
		customerDto.setName(customer.getName());
		customerDto.setEmail(customer.getEmail());
		customerDto.setMobileNumber(customer.getMobileNumber());
		return customerDto;
	}
	
	public static Customer mapCustomerDtoToCustomer(CustomerDTO customerDto) {
		
		Customer customer = new Customer();
		customer.setName(customerDto.getName());
		customer.setEmail(customerDto.getEmail());
		customer.setMobileNumber(customerDto.getMobileNumber());
		return customer;
	}
	
	public static Customer mapCustomerDtoToCustomerUpdating(CustomerDTO customerDto, Customer customer) {
		
		customer.setName(customerDto.getName());
		customer.setEmail(customerDto.getEmail());
		customer.setMobileNumber(customerDto.getMobileNumber());
		return customer;
	}
}
