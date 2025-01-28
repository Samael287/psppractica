package com.samael.prueba.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages = {"com.samael.prueba.config",
        "com.samael.prueba.controller",
        "com.samael.prueba.models",
        "com.samael.prueba.services",
})
@EnableMongoRepositories(basePackages = "com.samael.prueba.repositories")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}

