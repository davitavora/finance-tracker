package com.github.davitavora.jooq.model.projection;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionProjection(
    Long id,
    String name,
    String detail,
    BigDecimal amount,
    LocalDate createdAt,
    CategoryProjection category
) {
}
