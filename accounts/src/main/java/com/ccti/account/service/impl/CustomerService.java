package com.ccti.account.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ccti.account.dto.CardDTO;
import com.ccti.account.dto.FullCustomerDTO;
import com.ccti.account.dto.LoansDto;
import com.ccti.account.entity.Account;
import com.ccti.account.entity.Customer;
import com.ccti.account.exception.ResourceNotFound;
import com.ccti.account.mapper.AccountMapper;
import com.ccti.account.mapper.CustomerMapper;
import com.ccti.account.repository.AccountRepository;
import com.ccti.account.repository.CustomerRepository;
import com.ccti.account.service.ICustomerService;
import com.ccti.account.service.client.CardsFeignClient;
import com.ccti.account.service.client.LoansFeignClient;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerService implements ICustomerService {

	private AccountRepository accountsRepository;
	private CustomerRepository customerRepository;
	private LoansFeignClient loansFeignClient;
	private CardsFeignClient cardsFeignClient;

	@Override
	public FullCustomerDTO fetchFullCustomer(String email) {

		FullCustomerDTO fullCustomerDto = new FullCustomerDTO();

		Customer customer = customerRepository.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFound("Customer", "email", email));

		fullCustomerDto.setCustomer(CustomerMapper.mapCustomerToDto(customer));

		Account account = accountsRepository.findByCustomerId(customer.getCustomerId())
				.orElseThrow(() -> new ResourceNotFound("Account", "customerId", customer.getCustomerId().toString()));

		fullCustomerDto.setAccount(AccountMapper.mapAccountToDto(account));

		ResponseEntity<LoansDto> loansDtoResponse = loansFeignClient.fetchLoanDetails(email);
		if (loansDtoResponse != null)
			fullCustomerDto.setLoan(loansDtoResponse.getBody());

		ResponseEntity<CardDTO> cardDtoResponse = cardsFeignClient.fetchCardDetails(email);
		if (cardDtoResponse != null)
			fullCustomerDto.setCards(cardDtoResponse.getBody());

		return fullCustomerDto;
	}
}
