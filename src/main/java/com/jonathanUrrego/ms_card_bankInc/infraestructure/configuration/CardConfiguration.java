package com.jonathanUrrego.ms_card_bankInc.infraestructure.configuration;

import com.jonathanUrrego.ms_card_bankInc.application.service.CardService;
import com.jonathanUrrego.ms_card_bankInc.application.service.CardServiceImpl;
import com.jonathanUrrego.ms_card_bankInc.domain.port.CardRepository;
import com.jonathanUrrego.ms_card_bankInc.infraestructure.persistence.adapter.CardRepositoryAdapter;
import com.jonathanUrrego.ms_card_bankInc.infraestructure.persistence.repository.CardJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CardConfiguration {

    @Bean
    public CardRepository cardRepository(CardJpaRepository cardJpaRepository) {
        return new CardRepositoryAdapter(cardJpaRepository);
    }

    @Bean
    public CardService cardService(CardRepository cardRepository) {
        return new CardServiceImpl(cardRepository);
    }
}

