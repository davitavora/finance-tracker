package com.github.davitavora.jooq.mapper;

import com.github.davitavora.jooq.model.command.CreateCategoryCommand;
import com.github.davitavora.jooq.model.projection.CategoryProjection;
import com.github.davitavora.jooq.model.representation.CategoryRepresentation;
import io.vobiscum.jooqpoc.domain.tables.records.CategoryRecord;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper
public interface CategoryMapper {

    List<CategoryRepresentation> toRepresentation(List<CategoryProjection> projections);

    CategoryRepresentation toRepresentation(CategoryProjection projection);

    CategoryRecord toRecord(CreateCategoryCommand command);

}
