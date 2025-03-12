package com.jonathanUrrego.ms_card_bankInc.domain.port;

import com.jonathanUrrego.ms_card_bankInc.domain.model.Card;

import java.util.Optional;

public interface CardRepository {
    Card save(Card card);
    Optional<Card> findByCardNumber(String cardNumber);
}
