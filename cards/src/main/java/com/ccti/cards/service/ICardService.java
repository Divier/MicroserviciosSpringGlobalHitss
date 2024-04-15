package com.ccti.cards.service;

import com.ccti.cards.dto.CardDTO;

public interface ICardService {

	void createCard(CardDTO cardDto);
	CardDTO fetchCard(String email);
	void deleteCard(String email);
	boolean updateCard(CardDTO cardDto);
}
