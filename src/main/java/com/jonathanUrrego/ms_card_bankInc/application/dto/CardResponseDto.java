package com.jonathanUrrego.ms_card_bankInc.application.dto;

import com.jonathanUrrego.ms_card_bankInc.domain.model.CardType;

import java.math.BigDecimal;
import java.time.YearMonth;

public class CardResponseDto {
    private String cardNumber;
    private String titularName;
    private YearMonth expirationDate;
    private CardType cardType;
    private BigDecimal balance;

    public CardResponseDto(String cardNumber, String titularName, YearMonth expirationDate, CardType cardType, BigDecimal balance) {
        this.cardNumber = cardNumber;
        this.titularName = titularName;
        this.expirationDate = expirationDate;
        this.cardType = cardType;
        this.balance = balance;
    }

    public String getCardNumber() {
        return cardNumber;
    }
    public String getTitularName() {
        return titularName;
    }
    public YearMonth getExpirationDate() {
        return expirationDate;
    }
    public CardType getCardType() {
        return cardType;
    }
    public BigDecimal getBalance() {
        return balance;
    }
}
