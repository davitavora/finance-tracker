package com.github.davitavora.jooq.model.representation;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.davitavora.jooq.model.view.Save;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionRepresentation(
    Long id,
    LocalDate createdAt,
    @NotBlank
    @Size(max = 255)
    @JsonView(Save.class)
    String name,
    @Size(max = 255)
    @JsonView(Save.class)
    String detail,
    @NotNull
    @Digits(integer = 10, fraction = 2)
    @JsonView(Save.class)
    BigDecimal amount,
    @JsonView(Save.class)
    Integer categoryId
) {

}
