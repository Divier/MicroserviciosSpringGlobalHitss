package com.ccti.cards.mapper;

import com.ccti.cards.dto.CardDTO;
import com.ccti.cards.entity.Card;

public class CardMapper {

	public static CardDTO mapCardToCardDto(Card card) {
	
		CardDTO cardDto = new CardDTO();
		cardDto.setAmountUsed(card.getAmountUsed());
		cardDto.setAvailableAmount(card.getAvailableAmount());
		cardDto.setCardNumber(card.getCardNumber());
		cardDto.setCardType(card.getCardType());
		cardDto.setEmail(card.getEmail());
		cardDto.setTotalLimit(card.getTotalLimit());
		return cardDto;
	}
	
	public static Card mapCardDtoToCard(CardDTO cardDto, Long cardId) {
		
		Card card = new Card();
		card.setAmountUsed(cardDto.getAmountUsed());
		card.setAvailableAmount(cardDto.getAvailableAmount());
		card.setCardNumber(cardDto.getCardNumber());
		card.setCardType(cardDto.getCardType());
		card.setTotalLimit(cardDto.getTotalLimit());
		return card;
	}
	
	public static Card mapCardDtoToCardUpdating(CardDTO cardDto, Card card) {
		
		card.setAmountUsed(cardDto.getAmountUsed());
		card.setAvailableAmount(cardDto.getAvailableAmount());
		card.setCardNumber(cardDto.getCardNumber());
		card.setCardType(cardDto.getCardType());
		card.setTotalLimit(cardDto.getTotalLimit());
		return card;
	}
}
