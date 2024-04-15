package com.ccti.cards.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ccti.cards.dto.CardDTO;
import com.ccti.cards.entity.Card;
import com.ccti.cards.exception.CardAlreadyExists;
import com.ccti.cards.exception.ResourceNotFound;
import com.ccti.cards.mapper.CardMapper;
import com.ccti.cards.repository.CardRepository;
import com.ccti.cards.service.ICardService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CardService implements ICardService {

	private CardRepository cardRepository;
	
	@Override
	public void createCard(CardDTO cardDto) {

		Optional<Card> existingCard = cardRepository.findByEmail(cardDto.getEmail());
		
		if(existingCard.isPresent()) {
			throw new CardAlreadyExists("El correo electronico " + cardDto.getEmail() + " ya esta asociado a una tarjeta");
		}
		
		Card card = new Card();
		card.setAmountUsed(cardDto.getAmountUsed());
		card.setAvailableAmount(cardDto.getAvailableAmount());
		card.setCardNumber(cardDto.getCardNumber());
		card.setCardType(cardDto.getCardType());
		card.setEmail(cardDto.getEmail());
		card.setTotalLimit(cardDto.getTotalLimit());
		cardRepository.save(card);
	}

	@Override
	public CardDTO fetchCard(String email) {
		
		Card card = cardRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFound("Tarjeta", "email", email));
		CardDTO cardDTO = CardMapper.mapCardToCardDto(card);
		return cardDTO;
	}

	@Override
	public void deleteCard(String email) {

		Card card = cardRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFound("Tarjeta", "email", email));
		cardRepository.deleteById(card.getCardId());
	}

	@Override
	public boolean updateCard(CardDTO cardDTO) {
		
		Card card = cardRepository.findByEmail(cardDTO.getEmail()).orElseThrow(() -> new ResourceNotFound("Tarjeta", "email", cardDTO.getEmail()));
		cardRepository.save(CardMapper.mapCardDtoToCardUpdating(cardDTO, card));		
		return true;
	}	
}