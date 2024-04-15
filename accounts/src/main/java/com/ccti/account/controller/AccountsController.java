package com.ccti.account.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ccti.account.dto.ContactInfoDTO;
import com.ccti.account.dto.CustomerAccountDTO;
import com.ccti.account.dto.CustomerDTO;
import com.ccti.account.dto.ErrorResponseDTO;
import com.ccti.account.dto.ResponseDTO;
import com.ccti.account.service.IAccountService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Tag(
		name = "Controlador para servicio de cuenta",
		description = "Endpoint para las operaciones CRUD del Servicio"
)
@Validated
@RestController
@RequestMapping(path = "/api", produces = { MediaType.APPLICATION_JSON_VALUE })
//@AllArgsConstructor
@RequiredArgsConstructor
public class AccountsController {

	@NonNull //Como se deja el @RequiredArgsConstructor el spring inyecta en el contructor este objeto ya inicializado, ya q no es recomendable usar el @Autorwired
	private IAccountService iAccountService;

	@Value("${build.version}")
	private String buildVersion;
	
	@NonNull
	private Environment environment;
	
	@NonNull
	private ContactInfoDTO contactInfoDTO;
	
	@GetMapping(value = "/buil-version", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> getBuildInfo() {
		return ResponseEntity.status(HttpStatus.OK).body(buildVersion);
	}
	
	@GetMapping(value = "/java-home", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> getJavaHome() {
		return ResponseEntity.status(HttpStatus.OK).body(environment.getProperty("JAVA_HOME"));
	}
	
	@GetMapping(value = "/contact-info", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ContactInfoDTO> getContactInfo() {
		return ResponseEntity.status(HttpStatus.OK).body(contactInfoDTO);
	}
	
	@Operation(
			summary = "Crear Cuentas",
			description = "Crea un cliente y una cuenta en el sistema"
	)
	@ApiResponses({
			@ApiResponse(
					responseCode = "201",
					description = "HTTP Status Created",
					content = @Content(
						schema = @Schema(implementation = ResponseDTO.class)
					)
			),
			@ApiResponse(
					responseCode = "500",
					description = "HTTP Internal Server Error",
					content = @Content(
						schema = @Schema(implementation = ErrorResponseDTO.class)
					)
			)
	})
	@PostMapping("/create")
	public ResponseEntity<ResponseDTO> createAccount(@Valid @RequestBody CustomerDTO customerDTO) {
		iAccountService.createAccount(customerDTO);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDTO("201", "La cuenta ha sido creada correctamente!!"));
	}
	
	@GetMapping("/fetchAccount")
	public ResponseEntity<CustomerAccountDTO> fetchAccount(
			@RequestParam 
			@Email(message = "Debe especificar un email valido")
			String email) {
		CustomerAccountDTO customerAccountDTO = iAccountService.fetchAccount(email);
		return ResponseEntity.status(HttpStatus.OK)
				.body(customerAccountDTO);
	}
	
	@DeleteMapping("/deleteAccountByEmail")
	public ResponseEntity<ResponseDTO> deleteAccount(
			@RequestParam 
			@Email(message = "Debe especificar un email valido")
			String email) {
		iAccountService.deleteAccount(email);
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(new ResponseDTO("202", "La cuenta ha sido eliminada!!"));
	}
	
	@PatchMapping("/updateAccountByEmail")
	public ResponseEntity<ResponseDTO> updateAccount(@Valid @RequestBody CustomerAccountDTO customerAccountDTO) {
		
		if(iAccountService.updateAccount(customerAccountDTO)) {
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(new ResponseDTO("202", "La cuenta ha sido actualizada!!"));
		}
		return null;
	}
}
