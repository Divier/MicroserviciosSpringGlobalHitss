package com.ccti.cards.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
//@Table(name = "CARDS")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Card extends BaseEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cardId;
	@Column(name = "email")
	private String email;
	@Column(name = "card_number")
	private String cardNumber;
	@Column(name = "card_type")
	private String cardType;
	@Column(name = "total_limit")
	private Long totalLimit;
	@Column(name = "amount_used")
	private Long amountUsed;
	@Column(name = "available_amount")
	private Long availableAmount;
}
