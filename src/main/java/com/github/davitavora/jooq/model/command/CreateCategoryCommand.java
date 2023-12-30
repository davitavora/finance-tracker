package com.github.davitavora.jooq.model.command;

import io.vobiscum.jooqpoc.domain.enums.CategoryType;

public record CreateCategoryCommand(
    String name,
    CategoryType type
) {
}
