package com.ccti.account.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.ccti.account.dto.CustomerAccountDTO;
import com.ccti.account.dto.CustomerDTO;
import com.ccti.account.entity.Account;
import com.ccti.account.entity.Customer;
import com.ccti.account.exception.CustomerAlreadyExists;
import com.ccti.account.exception.ResourceNotFound;
import com.ccti.account.mapper.AccountMapper;
import com.ccti.account.mapper.CustomerMapper;
import com.ccti.account.repository.AccountRepository;
import com.ccti.account.repository.CustomerRepository;
import com.ccti.account.service.IAccountService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountService implements IAccountService {

	private AccountRepository accountRepository;
	private CustomerRepository customerRepository;
	
	@Override
	public void createAccount(CustomerDTO customerDto) {

		Customer customer = createCustomer(customerDto);
		long accNumber = 10000000000L + new Random().nextInt(900000000);
		Account account = new Account();
		account.setCustomerId(customer.getCustomerId());
		account.setAccountType("saving");
		account.setAccountNumber(accNumber);
		account.setBranchAddress("Main Branch");	
		//account.setCreatedBy("system");
		//account.setCreatedAt(LocalDateTime.now());
		accountRepository.save(account);
	}
	
	private Customer createCustomer(CustomerDTO customerDto) {
		
		Optional<Customer> existingCustomer = customerRepository.findByEmail(customerDto.getEmail());
		
		if(existingCustomer.isPresent()) {
			throw new CustomerAlreadyExists("El correo electronico " + customerDto.getEmail() + " ya esta registrado");
		}
		
		Customer customer = CustomerMapper.mapCustomerDtoToCustomer(customerDto);
		//customer.setCreatedBy("System");
		//customer.setCreatedAt(LocalDateTime.now());
		customerRepository.save(customer);
		return customer;
	}

	@Override
	public CustomerAccountDTO fetchAccount(String email) {
		
		Customer customer = customerRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFound("Cliente", "email", email));
		Account account = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(() -> new ResourceNotFound("Cuenta", "id de cliente", customer.getCustomerId().toString()));

		CustomerAccountDTO customerAccountDTO = new CustomerAccountDTO();
		customerAccountDTO.setCustomerDTO(CustomerMapper.mapCustomerToDto(customer));
		customerAccountDTO.setAccountDTO(AccountMapper.mapAccountToDto(account));
		return customerAccountDTO;
	}

	@Override
	public void deleteAccount(String email) {

		Customer customer = customerRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFound("Cliente", "email", email));
		Account account = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(() -> new ResourceNotFound("Cuenta", "id de cliente", customer.getCustomerId().toString()));

		accountRepository.deleteByCustomerId(account.getCustomerId());
		customerRepository.deleteById(account.getCustomerId());
	}

	@Override
	public boolean updateAccount(CustomerAccountDTO customerAccountDTO) {
		
		Customer customer = customerRepository.findByEmail(customerAccountDTO.getCustomerDTO().getEmail()).orElseThrow(() -> new ResourceNotFound("Cliente", "email", customerAccountDTO.getCustomerDTO().getEmail()));
		Customer updateCustomer = CustomerMapper.mapCustomerDtoToCustomerUpdating(customerAccountDTO.getCustomerDTO(), customer);
		
		Account account = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(() -> new ResourceNotFound("Cuenta", "id de cliente", customer.getCustomerId().toString()));
		Account updateAcount = AccountMapper.mapAccountDtoToAccountUpdating(customerAccountDTO.getAccountDTO(), account);

		accountRepository.save(updateAcount);
		customerRepository.save(updateCustomer);
		
		return true;
	}	
}