package com.github.davitavora.jooq.mapper;

import com.github.davitavora.jooq.model.representation.CategoryRepresentation;
import io.vobiscum.jooqpoc.domain.tables.records.CategoryRecord;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface CategoryMapper {

    CategoryRepresentation asRepresentation(CategoryRecord projection);

    List<CategoryRepresentation> asRepresentation(List<CategoryRecord> projections);

    @Mapping(target = "id", ignore = true)
    CategoryRecord asNewRecord(CategoryRepresentation category);

    void update(@MappingTarget CategoryRecord record, CategoryRepresentation representation);

}
