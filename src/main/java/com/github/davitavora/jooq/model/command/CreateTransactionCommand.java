package com.github.davitavora.jooq.model.command;

import java.math.BigDecimal;

public record CreateTransactionCommand(
    String name,
    String detail,
    BigDecimal amount,
    Integer categoryId
) {
}
