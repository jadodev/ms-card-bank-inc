package com.jonathanUrrego.ms_card_bankInc.infraestructure.persistence.repository;

import com.jonathanUrrego.ms_card_bankInc.infraestructure.persistence.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardJpaRepository extends JpaRepository<CardEntity, Long> {
    public Optional<CardEntity> findByCardNumber(String cardNumber);
}
