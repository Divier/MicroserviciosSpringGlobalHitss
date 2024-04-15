package com.ccti.cards.controller;

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

import com.ccti.cards.dto.ContactInfoDTO;
import com.ccti.cards.dto.CardDTO;
import com.ccti.cards.dto.ErrorResponseDTO;
import com.ccti.cards.dto.ResponseDTO;
import com.ccti.cards.service.ICardService;

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

@Tag(name = "Controlador para servicio de tarjetas", description = "Endpoint para las operaciones CRUD del Servicio")
@Validated
@RestController
@RequestMapping(path = "/api", produces = { MediaType.APPLICATION_JSON_VALUE })
//@AllArgsConstructor
@RequiredArgsConstructor
public class CardsController {

	@NonNull
	private ICardService iCardService;
	
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

	@Operation(summary = "Crear Tarjetas", description = "Crea una tarjeta en el sistema")
	@ApiResponses({
			@ApiResponse(responseCode = "201", description = "HTTP Status Created", content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
			@ApiResponse(responseCode = "500", description = "HTTP Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))) })
	@PostMapping("/createCard")
	public ResponseEntity<ResponseDTO> createCard(@Valid @RequestBody CardDTO cardDTO) {
		
		iCardService.createCard(cardDTO);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDTO("201", "La tarjeta ha sido creada correctamente!!"));
	}

	@GetMapping("/fetchCard")
	public ResponseEntity<CardDTO> fetchCard(
			@RequestParam @Email(message = "Debe especificar un email valido") String email) {

		CardDTO cardDTO = iCardService.fetchCard(email);
		return ResponseEntity.status(HttpStatus.OK).body(cardDTO);
	}

	@DeleteMapping("/deleteCard")
	public ResponseEntity<ResponseDTO> deleteCard(
			@RequestParam @Email(message = "Debe especificar un email valido") String email) {

		iCardService.deleteCard(email);
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(new ResponseDTO("202", "La tarjeta ha sido eliminada!!"));
	}

	@PatchMapping("/updateCard")
	public ResponseEntity<ResponseDTO> updateCard(@Valid @RequestBody CardDTO cardDto) {

		if (iCardService.updateCard(cardDto)) {
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(new ResponseDTO("202", "La tarjeta ha sido actualizada!!"));
		}
		return null;
	}
}
