package com.ccti.accounts;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.ccti.account.AccountsApplication;

//@SpringBootTest
//@ContextConfiguration
@SpringBootTest(classes = AccountsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AccountsApplicationTests {

	@Test
	void contextLoads() {
	}

}
