package com.github.davitavora.jooq.model.representation;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.davitavora.jooq.model.view.Save;
import io.vobiscum.jooqpoc.domain.enums.CategoryType;

public record CategoryRepresentation(
    Integer id,
    @JsonView(Save.class) String name,
    @JsonView(Save.class) CategoryType type
) {
}
