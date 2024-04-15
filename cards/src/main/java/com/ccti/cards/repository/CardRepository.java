package com.ccti.cards.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ccti.cards.entity.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Long>{

	Optional<Card> findByEmail(String email);
	
	@Modifying
	@Transactional
	void deleteByCardId(Long cardId);
}
