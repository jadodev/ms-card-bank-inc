package com.jonathanUrrego.ms_card_bankInc.infraestructure.persistence.adapter;

import com.jonathanUrrego.ms_card_bankInc.domain.model.Card;
import com.jonathanUrrego.ms_card_bankInc.domain.port.CardRepository;
import com.jonathanUrrego.ms_card_bankInc.infraestructure.persistence.entity.CardEntity;
import com.jonathanUrrego.ms_card_bankInc.infraestructure.persistence.repository.CardJpaRepository;

import java.time.YearMonth;
import java.util.Optional;

public class CardRepositoryAdapter implements CardRepository {
    private final CardJpaRepository cardJpaRepository;

    public CardRepositoryAdapter(CardJpaRepository cardJpaRepository) {
        this.cardJpaRepository = cardJpaRepository;
    }

    @Override
    public Card save(Card card) {
        CardEntity entity = mapToEntity(card);
        CardEntity savedEntity = cardJpaRepository.save(entity);
        return mapToDomain(savedEntity);
    }

    @Override
    public Optional<Card> findByCardNumber(String cardNumber) {
        return cardJpaRepository.findByCardNumber(cardNumber)
                .map(this::mapToDomain);
    }

    private CardEntity mapToEntity(Card card) {
        String expirationDateStr = String.format("%02d-%04d",
                card.getExpirationDate().getMonthValue(), card.getExpirationDate().getYear());
        CardEntity entity = new CardEntity(card.getCardNumber(), card.getTitularName(), expirationDateStr, card.getCardType(), card.getBalance());
        if (card.getId() != null) {
            entity.setId(card.getId());
        }
        return entity;
    }

    private Card mapToDomain(CardEntity entity) {
        String[] parts = entity.getExpirationDate().split("-");
        int month = Integer.parseInt(parts[0]);
        int year = Integer.parseInt(parts[1]);
        YearMonth expirationDate = YearMonth.of(year, month);
        Card card = new Card(entity.getCardNumber(), entity.getTitularName(), expirationDate, entity.getCardType());
        card.setId(entity.getId());
        card.setBalance(entity.getBalance());
        return card;
    }
}
