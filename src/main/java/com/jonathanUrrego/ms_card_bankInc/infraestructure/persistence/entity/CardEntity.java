package com.jonathanUrrego.ms_card_bankInc.infraestructure.persistence.entity;

import com.jonathanUrrego.ms_card_bankInc.domain.model.CardType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Entity
@Table(name = "cards", indexes = {
        @Index(name = "idx_card_number", columnList = "cardNumber", unique = true)
})
public class CardEntity {

    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 16)
    private String cardNumber;

    @Column(nullable = false)
    private String titularName;

    @Column(nullable = false)
    private String expirationDate; // Formato: MM-yyyy

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CardType cardType;

    @Column(nullable = false)
    private BigDecimal balance;

    public CardEntity() {}

    public CardEntity(String cardNumber, String titularName, String expirationDate, CardType cardType, BigDecimal balance) {
        this.cardNumber = cardNumber;
        this.titularName = titularName;
        this.expirationDate = expirationDate;
        this.cardType = cardType;
        this.balance = balance;
    }

}
