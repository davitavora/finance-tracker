package com.github.davitavora.jooq.mapper;

import com.github.davitavora.jooq.model.representation.CategoryRepresentation;
import io.vobiscum.jooqpoc.domain.Tables;
import io.vobiscum.jooqpoc.domain.tables.records.CategoryRecord;
import java.util.List;
import org.jooq.DSLContext;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public abstract class CategoryMapper {

    @Autowired
    private DSLContext jooq;

    public abstract CategoryRepresentation asRepresentation(CategoryRecord projection);

    public abstract List<CategoryRepresentation> asRepresentation(List<CategoryRecord> projections);

    public abstract void update(@MappingTarget CategoryRecord record, CategoryRepresentation representation);

    @Mapping(target = "id", ignore = true)
    public CategoryRecord asNewRecord(CategoryRepresentation category) {
        return jooq.newRecord(Tables.CATEGORY, category);
    }

}
