package com.github.davitavora.jooq.model.representation;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.davitavora.jooq.model.view.Save;
import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionRepresentation(
    Long id,
    LocalDate createdAt,
    @JsonView(Save.class) String name,
    @JsonView(Save.class) String detail,
    @JsonView(Save.class) BigDecimal amount,
    @JsonView(Save.class) Integer categoryId
) {

}
