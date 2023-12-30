package com.github.davitavora.jooq.model.representation;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionRepresentation(
    String name,
    String detail,
    BigDecimal amount,
    LocalDate createdAt,
    CategoryRepresentation category
) {
}
