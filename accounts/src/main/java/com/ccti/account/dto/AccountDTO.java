package com.ccti.account.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(
		name = "Accounts",
		description = "Esquema que almacena datos de cuentas"
)
@Data
public class AccountDTO {

	@Schema(
			description = "Numero de la cuenta en el sistema"
	)
	@NotNull(message = "El numero de cuenta es requerido")
	//@Pattern(regexp = "(^$|[0-9]{10})", message = "El numero de cuenta debe tener 10 digitos")
	private Long accountNumber;
	@Schema(
			description = "Tipo de la cuenta"
	)
	@NotEmpty(message = "El tipo de cuenta es requerido")
	private String accountType;
	@Schema(
			description = "Direccion de la sucursal bancaria"
	)
	@NotEmpty(message = "La direccion es requerida")
	private String branchAddress;
}
