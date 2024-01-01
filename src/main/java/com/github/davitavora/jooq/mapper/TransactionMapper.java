package com.github.davitavora.jooq.mapper;

import com.github.davitavora.jooq.model.representation.TransactionRepresentation;
import com.github.jooq.domain.Tables;
import com.github.jooq.domain.tables.records.FinancialTransactionRecord;
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
public abstract class TransactionMapper {

    @Autowired
    public DSLContext jooq;

    public abstract TransactionRepresentation asRepresentation(FinancialTransactionRecord projection);

    public abstract List<TransactionRepresentation> asRepresentation(List<FinancialTransactionRecord> projections);

    @BeanMapping(ignoreByDefault = true, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "name", target = "name", conditionExpression = "java(ObjectUtils.notEqual(record.getName(), representation.name()))")
    @Mapping(source = "detail", target = "detail", conditionExpression = "java(ObjectUtils.notEqual(record.getDetail(), representation.detail()))")
    @Mapping(source = "amount", target = "amount", conditionExpression = "java(ObjectUtils.notEqual(record.getAmount(), representation.amount()))")
    @Mapping(source = "categoryId", target = "categoryId", conditionExpression = "java(ObjectUtils.notEqual(record.getCategoryId(), representation.categoryId()))")
    public abstract void updateChangedFields(@MappingTarget FinancialTransactionRecord record, TransactionRepresentation representation);

    @Mapping(target = "id", ignore = true)
    public FinancialTransactionRecord asNewRecord(TransactionRepresentation transaction) {
        return jooq.newRecord(Tables.FINANCIAL_TRANSACTION, transaction);
    }

}
