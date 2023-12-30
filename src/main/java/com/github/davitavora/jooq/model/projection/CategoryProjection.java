package com.github.davitavora.jooq.model.projection;

import io.vobiscum.jooqpoc.domain.enums.CategoryType;

public record CategoryProjection(
    Integer id,
    String name,
    CategoryType type
) {
}
