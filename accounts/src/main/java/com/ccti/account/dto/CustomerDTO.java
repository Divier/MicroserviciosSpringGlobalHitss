package com.ccti.account.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDTO {

	@NotEmpty(message = "El nombre del cliente es requerido")
	@Size(min = 5, max = 50, message = "El nombre debe contenter entre 5 y 50 caracteres")
	private String name;
	@NotEmpty(message = "El email del cliente es requerido")
	@Email(message = "Debe especificar un email valido")
	private String email;
	@Pattern(regexp = "(^$|[0-9]{10})", message = "El numero de telefono debe tener 10 digitos")
	@NotEmpty(message = "El numero de telefono es requerido")
	private String mobileNumber;
}
