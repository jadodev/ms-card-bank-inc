package com.jonathanUrrego.ms_card_bankInc.infraestructure.controller;

import com.jonathanUrrego.ms_card_bankInc.application.dto.CardPaymentDto;
import com.jonathanUrrego.ms_card_bankInc.application.dto.CardRechargeDto;
import com.jonathanUrrego.ms_card_bankInc.application.dto.CardResponseDto;
import com.jonathanUrrego.ms_card_bankInc.application.dto.CardRequestDto;
import com.jonathanUrrego.ms_card_bankInc.application.service.CardService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/cards")
@Validated
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping
    public ResponseEntity<CardResponseDto> createCard(@Valid @RequestBody CardRequestDto request) {
        var card = cardService.createCard(request.getProductId(), request.getTitularName(), request.getCardType());
        var response = new CardResponseDto(card.getCardNumber(), card.getTitularName(), card.getExpirationDate(), card.getCardType(), card.getBalance());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{cardNumber}/recharge")
    public ResponseEntity<CardResponseDto> rechargeCard(@PathVariable String cardNumber, @Valid @RequestBody CardRechargeDto rechargeDto) {
        var card = cardService.rechargeCard(cardNumber, rechargeDto.getAmount());
        var response = new CardResponseDto(card.getCardNumber(), card.getTitularName(), card.getExpirationDate(), card.getCardType(), card.getBalance());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{cardNumber}/debit")
    public ResponseEntity<CardResponseDto> debitCard(@PathVariable String cardNumber, @Valid @RequestBody CardPaymentDto paymentDto) {
        var card = cardService.debitCard(cardNumber, paymentDto.getAmount());
        var response = new CardResponseDto(card.getCardNumber(), card.getTitularName(), card.getExpirationDate(), card.getCardType(), card.getBalance());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{cardNumber}")
    public ResponseEntity<CardResponseDto> getCard(@PathVariable String cardNumber) {
        var card = cardService.getCard(cardNumber);
        var response = new CardResponseDto(card.getCardNumber(), card.getTitularName(), card.getExpirationDate(), card.getCardType(), card.getBalance());
        return ResponseEntity.ok(response);
    }
}
