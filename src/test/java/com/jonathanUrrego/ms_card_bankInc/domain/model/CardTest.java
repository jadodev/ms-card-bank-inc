package com.jonathanUrrego.ms_card_bankInc.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.YearMonth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CardTest {
    private Card card;

    @BeforeEach
    void setUp() {
        card = new Card("1234567890123456", "John Doe", YearMonth.of(2025, 12), CardType.CREDITO);
    }

    @Test
    void testRecharge() {
        card.recharge(BigDecimal.valueOf(100));
        assertEquals(BigDecimal.valueOf(100), card.getBalance());

        card.recharge(BigDecimal.valueOf(50));
        assertEquals(BigDecimal.valueOf(150), card.getBalance());
    }

    @Test
    void testRechargeWithNegativeAmount() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            card.recharge(BigDecimal.valueOf(-10));
        });
        assertEquals("El monto debe ser positivo", exception.getMessage());
    }

    @Test
    void testDebit() {
        card.recharge(BigDecimal.valueOf(100));
        card.debit(BigDecimal.valueOf(50));
        assertEquals(BigDecimal.valueOf(50), card.getBalance());
    }

    @Test
    void testDebitWithInsufficientFunds() {
        card.recharge(BigDecimal.valueOf(50));
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            card.debit(BigDecimal.valueOf(100));
        });
        assertEquals("Fondos insuficientes", exception.getMessage());
    }

    @Test
    void testDebitWithNegativeAmount() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            card.debit(BigDecimal.valueOf(-10));
        });
        assertEquals("El monto debe ser positivo", exception.getMessage());
    }
}
