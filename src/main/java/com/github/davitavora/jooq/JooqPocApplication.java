package com.github.davitavora.jooq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.github.davitavora")
public class JooqPocApplication {

    public static void main(String[] args) {
        SpringApplication.run(JooqPocApplication.class, args);
    }

}
