package com.ccti.account.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(
		name = "Cards",
		description = "Esquema que almacena datos de tarjetas"
)
@Data
public class CardDTO {

	@NotEmpty(message = "El email asociado a la tarjeta es requerido")
	@Schema(
			description = "Email asociado a la tarjeta"
	)
	private String email;
	@NotEmpty(message = "El numero de la tarjeta es requerido")
	@Schema(
			description = "Numero de la tarjeta"
	)
	private String cardNumber;
	@NotEmpty(message = "El tipo de la tarjeta es requerido")
	@Schema(
			description = "Tipo de la tarjeta"
	)
	private String cardType;
	@NotNull(message = "El saldo total de la tarjeta es requerido")
	@Schema(
			description = "Saldo total de la tarjeta"
	)
	private Long totalLimit;
	@NotNull(message = "El saldo utilizado de la tarjeta es requerida")
	@Schema(
			description = "Saldo utilizado de la tarjeta"
	)
	private Long amountUsed;
	@NotNull(message = "El saldo disponible de la tarjeta es requerido")
	@Schema(
			description = "Saldo disponible de la tarjeta"
	)
	private Long availableAmount;
}
