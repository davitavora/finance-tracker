package com.github.davitavora.jooq.mapper;

import com.github.davitavora.jooq.domain.Tables;
import com.github.davitavora.jooq.domain.tables.records.CategoryRecord;
import com.github.davitavora.jooq.model.representation.CategoryRepresentation;
import java.util.List;
import org.apache.commons.lang3.ObjectUtils;
import org.jooq.DSLContext;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(imports = ObjectUtils.class)
public abstract class CategoryMapper {

    @Autowired
    private DSLContext jooq;

    public abstract CategoryRepresentation asRepresentation(CategoryRecord projection);

    public abstract List<CategoryRepresentation> asRepresentation(List<CategoryRecord> projections);

    @BeanMapping(ignoreByDefault = true, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "name", target = "name", conditionExpression = "java(ObjectUtils.notEqual(record.getName(), representation.name()))")
    @Mapping(source = "type", target = "type", conditionExpression = "java(ObjectUtils.notEqual(record.getType(), representation.type()))")
    public abstract void updateChangedFields(@MappingTarget CategoryRecord record, CategoryRepresentation representation);

    @Mapping(target = "id", ignore = true)
    public CategoryRecord asNewRecord(CategoryRepresentation category) {
        return jooq.newRecord(Tables.CATEGORY, category);
    }

}
