package com.ccti.account.dto;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "contact")
public class ContactInfoDTO {

	private String message;
	private Map<String, String> details;
	private List<String> phoneNumbers;
}
