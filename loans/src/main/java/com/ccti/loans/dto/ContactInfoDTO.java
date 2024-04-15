package com.ccti.loans.dto;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "contact")
public record ContactInfoDTO(String message, Map<String, String> details, List<String> phoneNumbers) {

	
}
