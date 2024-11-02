package edu.evgen.kitchen.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;

@Configuration
public class MessagingConfig {
    @Bean
    public MappingJackson2MessageConverter messageConverter() {
        MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();

        // Регистрация модуля для работы с Java 8 Date/Time API
        objectMapper.registerModule(new JavaTimeModule());

        messageConverter.setObjectMapper(objectMapper);
        messageConverter.setTypeIdPropertyName("_typeId");

        return messageConverter;
    }
}