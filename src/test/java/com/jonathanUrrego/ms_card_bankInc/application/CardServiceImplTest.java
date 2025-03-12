package com.jonathanUrrego.ms_card_bankInc.application;

import com.jonathanUrrego.ms_card_bankInc.application.service.CardServiceImpl;
import com.jonathanUrrego.ms_card_bankInc.domain.model.Card;
import com.jonathanUrrego.ms_card_bankInc.domain.model.CardType;
import com.jonathanUrrego.ms_card_bankInc.domain.port.CardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CardServiceImplTest {

    @Mock
    private CardRepository cardRepository;

    @InjectMocks
    private CardServiceImpl cardService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCard() {
        String productId = "123456";
        String titularName = "John Doe";
        CardType cardType = CardType.CREDITO;

        Card card = new Card(productId + "7890123456", titularName, YearMonth.now().plusYears(3), cardType);
        when(cardRepository.save(any(Card.class))).thenReturn(card);

        Card createdCard = cardService.createCard(productId, titularName, cardType);

        assertNotNull(createdCard);
        assertEquals(productId, createdCard.getCardNumber().substring(0, 6));
        assertEquals(titularName, createdCard.getTitularName());
        assertEquals(cardType, createdCard.getCardType());
        assertEquals(YearMonth.now().plusYears(3), createdCard.getExpirationDate());

        verify(cardRepository, times(1)).save(any(Card.class));
    }

    @Test
    void testRechargeCard() {
        String cardNumber = "1234567890123456";
        BigDecimal amount = BigDecimal.valueOf(100);
        Card card = new Card(cardNumber, "John Doe", YearMonth.of(2025, 12), CardType.CREDITO);
        when(cardRepository.findByCardNumber(cardNumber)).thenReturn(Optional.of(card));
        when(cardRepository.save(any(Card.class))).thenReturn(card);

        Card updatedCard = cardService.rechargeCard(cardNumber, amount);

        assertNotNull(updatedCard);
        assertEquals(amount, updatedCard.getBalance());
        verify(cardRepository, times(1)).save(card);
    }

    @Test
    void testDebitCard() {
        String cardNumber = "1234567890123456";
        BigDecimal amount = BigDecimal.valueOf(50);
        Card card = new Card(cardNumber, "John Doe", YearMonth.of(2025, 12), CardType.CREDITO);
        card.recharge(BigDecimal.valueOf(100));
        when(cardRepository.findByCardNumber(cardNumber)).thenReturn(Optional.of(card));
        when(cardRepository.save(any(Card.class))).thenReturn(card);

        Card updatedCard = cardService.debitCard(cardNumber, amount);

        assertNotNull(updatedCard);
        assertEquals(BigDecimal.valueOf(50), updatedCard.getBalance());
        verify(cardRepository, times(1)).save(card);
    }

    @Test
    void testGetCard() {
        String cardNumber = "1234567890123456";
        Card card = new Card(cardNumber, "John Doe", YearMonth.of(2025, 12), CardType.CREDITO);
        when(cardRepository.findByCardNumber(cardNumber)).thenReturn(Optional.of(card));

        Card foundCard = cardService.getCard(cardNumber);

        assertNotNull(foundCard);
        assertEquals(cardNumber, foundCard.getCardNumber());
        verify(cardRepository, times(1)).findByCardNumber(cardNumber);
    }
}
