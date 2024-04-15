package com.ccti.cards;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

//@SpringBootTest
//@ContextConfiguration
@SpringBootTest(classes = CardsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CardsApplicationTests {

	@Test
	void contextLoads() {
	}

}
