package com.jonathanUrrego.ms_card_bankInc.application.service;

import com.jonathanUrrego.ms_card_bankInc.domain.model.Card;
import com.jonathanUrrego.ms_card_bankInc.domain.model.CardType;
import com.jonathanUrrego.ms_card_bankInc.domain.port.CardRepository;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.Random;

public class CardServiceImpl implements CardService{
    private final CardRepository cardRepository;
    private final Random random = new Random();

    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public Card createCard(String productId, String titularName, CardType cardType) {
        if (productId == null || productId.length() != 6 || !productId.matches("\\d{6}")) {
            throw new IllegalArgumentException("El productId debe ser numérico y tener 6 dígitos.");
        }
        String randomDigits = random.ints(10, 0, 10)
                .mapToObj(String::valueOf)
                .reduce("", String::concat);
        String cardNumber = productId + randomDigits;
        YearMonth expirationDate = YearMonth.now().plusYears(3);
        Card card = new Card(cardNumber, titularName, expirationDate, cardType);
        return cardRepository.save(card);
    }

    @Override
    public Card rechargeCard(String cardNumber, BigDecimal amount) {
        Card card = cardRepository.findByCardNumber(cardNumber)
                .orElseThrow(() -> new IllegalArgumentException("Tarjeta no encontrada."));
        card.recharge(amount);
        return cardRepository.save(card);
    }

    @Override
    public Card debitCard(String cardNumber, BigDecimal amount) {
        Card card = cardRepository.findByCardNumber(cardNumber)
                .orElseThrow(() -> new IllegalArgumentException("Tarjeta no encontrada."));
        card.debit(amount);
        return cardRepository.save(card);
    }

    @Override
    public Card getCard(String cardNumber) {
        return cardRepository.findByCardNumber(cardNumber)
                .orElseThrow(() -> new IllegalArgumentException("Tarjeta no encontrada."));
    }
}
