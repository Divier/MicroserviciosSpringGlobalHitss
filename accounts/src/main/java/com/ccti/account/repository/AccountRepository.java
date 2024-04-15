package com.ccti.account.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ccti.account.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{

	Optional<Account> findByCustomerId(Long id);
	
	@Modifying
	@Transactional
	void deleteByCustomerId(Long customerId);
}
