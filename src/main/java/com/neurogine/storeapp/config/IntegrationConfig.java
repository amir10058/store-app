package com.neurogine.storeapp.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.StandardIntegrationFlow;
import org.springframework.integration.handler.LoggingHandler;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

@Configuration
public class IntegrationConfig {
    private static final Logger logger = LoggerFactory.getLogger(IntegrationConfig.class);

    @Bean
    public MessageChannel storeChannel() {
        return new DirectChannel();
    }

    @Bean
    public StandardIntegrationFlow storeFlow() {
        return IntegrationFlows.from("storeChannel")
                .log(LoggingHandler.Level.INFO, "com.neurogine.storeapp", Message::getPayload)
                .handle(message -> {
                    logger.info("Handling message: {}", message.getPayload());
                })
                .get();
    }
}
