package com.neurogine.storeapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StoreAppApplication {
    private static final Logger logger = LoggerFactory.getLogger(StoreAppApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(StoreAppApplication.class, args);
        logger.info("StoreAppApplication has started.");
    }

}
