package com.github.davitavora.jooq;

import com.github.davitavora.jooq.configuration.ContainerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestJooqPocApplication {

  public static void main(String[] args) {
    SpringApplication.from(JooqPocApplication::main)
        .with(ContainerConfiguration.class)
        .run(args);
  }

}

