package com.github.davitavora.jooq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestJooqPocApplication {

  public static void main(String[] args) {
    SpringApplication.from(JooqPocApplication::main)
        .run(args);
  }

}

