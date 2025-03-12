package com.jonathanUrrego.ms_card_bankInc.application.service;

import com.jonathanUrrego.ms_card_bankInc.domain.model.Card;
import com.jonathanUrrego.ms_card_bankInc.domain.model.CardType;

import java.math.BigDecimal;

public interface CardService {
    Card createCard(String productId, String titularName, CardType cardType);
    Card rechargeCard(String cardNumber, BigDecimal amount);
    Card debitCard(String cardNumber, BigDecimal amount);
    Card getCard(String cardNumber);
}
