package com.github.davitavora.jooq.model.representation;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.davitavora.jooq.model.view.Save;
import com.github.jooq.domain.enums.CategoryType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CategoryRepresentation(
    Integer id,
    @NotBlank
    @Size(max = 255)
    @JsonView(Save.class)
    String name,
    @NotNull
    @JsonView(Save.class)
    CategoryType type
) {
}
