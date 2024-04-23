package com.jmp.cloud.service.impl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.jmp.")
@EnableJpaRepositories(basePackages = {"com.jmp.domain.repository"})
@EntityScan("com.jmp.domain")

public class RestApiAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApiAppApplication.class, args);
    }
}

