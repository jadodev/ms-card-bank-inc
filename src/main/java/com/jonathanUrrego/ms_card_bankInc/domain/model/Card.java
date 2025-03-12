package com.jonathanUrrego.ms_card_bankInc.domain.model;

import lombok.Setter;

import java.math.BigDecimal;
import java.time.YearMonth;

public class Card {
    @Setter
    private Long id;
    private String cardNumber;
    private String titularName;
    private YearMonth expirationDate;
    private CardType cardType;
    @Setter
    private BigDecimal balance;

    public Card(String cardNumber, String titularName, YearMonth expirationDate, CardType cardType) {
        this.cardNumber = cardNumber;
        this.titularName = titularName;
        this.expirationDate = expirationDate;
        this.cardType = cardType;
        this.balance = BigDecimal.ZERO;
    }

    public void recharge(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto debe ser positivo");
        }
        balance = balance.add(amount);
    }

    public void debit(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto debe ser positivo");
        }
        if (balance.compareTo(amount) < 0) {
            throw new IllegalArgumentException("Fondos insuficientes");
        }
        balance = balance.subtract(amount);
    }

    public Long getId() {
        return id;
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
