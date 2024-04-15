package com.ccti.account.mapper;

import com.ccti.account.dto.AccountDTO;
import com.ccti.account.entity.Account;

public class AccountMapper {

	public static AccountDTO mapAccountToDto(Account account) {
	
		AccountDTO accountDto = new AccountDTO();
		accountDto.setAccountNumber(account.getAccountNumber());
		accountDto.setAccountType(account.getAccountType());
		accountDto.setBranchAddress(account.getBranchAddress());
		return accountDto;
	}
	
	public static Account mapAccountDtoToAccount(AccountDTO accountDto) {
		
		Account account = new Account();
		account.setAccountNumber(accountDto.getAccountNumber());
		account.setAccountType(accountDto.getAccountType());
		account.setBranchAddress(accountDto.getBranchAddress());
		return account;
	}
	
	public static Account mapAccountDtoToAccountUpdating(AccountDTO accountDto, Account account) {
		
		account.setAccountNumber(accountDto.getAccountNumber());
		account.setAccountType(accountDto.getAccountType());
		account.setBranchAddress(accountDto.getBranchAddress());
		return account;
	}
}
