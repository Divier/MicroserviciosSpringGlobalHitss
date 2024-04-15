package com.ccti.account.service.client;

import com.ccti.account.dto.CardDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cards")
public interface CardsFeignClient {

	@GetMapping(value = "/api/fetchCard", consumes = "application/json")
	public ResponseEntity<CardDTO> fetchCardDetails(@RequestParam String email);
}