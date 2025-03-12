package com.jonathanUrrego.ms_card_bankInc.infraestructure.persistence.adapter;

import com.jonathanUrrego.ms_card_bankInc.domain.model.Card;
import com.jonathanUrrego.ms_card_bankInc.domain.model.CardType;
import com.jonathanUrrego.ms_card_bankInc.infraestructure.persistence.entity.CardEntity;
import com.jonathanUrrego.ms_card_bankInc.infraestructure.persistence.repository.CardJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class CardRepositoryAdapterTest {
    @Mock
    private CardJpaRepository cardJpaRepository;

    @InjectMocks
    private CardRepositoryAdapter cardRepositoryAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        Card card = new Card("1234567890123456", "John Doe", YearMonth.of(2025, 12), CardType.CREDITO);
        card.setBalance(BigDecimal.valueOf(100));
        CardEntity cardEntity = new CardEntity("1234567890123456", "John Doe", "12-2025", CardType.CREDITO, BigDecimal.valueOf(100));
        when(cardJpaRepository.save(any(CardEntity.class))).thenReturn(cardEntity);

        Card savedCard = cardRepositoryAdapter.save(card);

        assertNotNull(savedCard);
        assertEquals(card.getCardNumber(), savedCard.getCardNumber());
        assertEquals(card.getTitularName(), savedCard.getTitularName());
        assertEquals(card.getExpirationDate(), savedCard.getExpirationDate());
        assertEquals(card.getCardType(), savedCard.getCardType());
        assertEquals(card.getBalance(), savedCard.getBalance());

        verify(cardJpaRepository, times(1)).save(any(CardEntity.class));
    }

    @Test
    void testFindByCardNumber() {
        String cardNumber = "1234567890123456";
        CardEntity cardEntity = new CardEntity(cardNumber, "John Doe", "12-2025", CardType.CREDITO, BigDecimal.valueOf(100));
        when(cardJpaRepository.findByCardNumber(cardNumber)).thenReturn(Optional.of(cardEntity));

        Optional<Card> foundCard = cardRepositoryAdapter.findByCardNumber(cardNumber);

        assertTrue(foundCard.isPresent());
        assertEquals(cardNumber, foundCard.get().getCardNumber());
        assertEquals("John Doe", foundCard.get().getTitularName());
        assertEquals(YearMonth.of(2025, 12), foundCard.get().getExpirationDate());
        assertEquals(CardType.CREDITO, foundCard.get().getCardType());
        assertEquals(BigDecimal.valueOf(100), foundCard.get().getBalance());

        verify(cardJpaRepository, times(1)).findByCardNumber(cardNumber);
    }
}
