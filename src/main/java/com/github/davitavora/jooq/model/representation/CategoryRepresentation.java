package com.github.davitavora.jooq.model.representation;

import io.vobiscum.jooqpoc.domain.enums.CategoryType;

public record CategoryRepresentation(
    Integer id,
    String name,
    CategoryType type
) {
}
