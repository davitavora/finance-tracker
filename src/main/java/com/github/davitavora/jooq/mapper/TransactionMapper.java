package com.github.davitavora.jooq.mapper;

import com.github.davitavora.jooq.model.representation.TransactionRepresentation;
import io.vobiscum.jooqpoc.domain.Tables;
import io.vobiscum.jooqpoc.domain.tables.records.FinancialTransactionRecord;
import java.util.List;
import org.jooq.DSLContext;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public abstract class TransactionMapper {

    @Autowired
    public DSLContext jooq;

    public abstract TransactionRepresentation asRepresentation(FinancialTransactionRecord projection);

    public abstract List<TransactionRepresentation> asRepresentation(List<FinancialTransactionRecord> projections);

    @Mapping(target = "id", ignore = true)
    public FinancialTransactionRecord asNewRecord(TransactionRepresentation transaction) {
        return jooq.newRecord(Tables.FINANCIAL_TRANSACTION, transaction);
    }

    public abstract void update(@MappingTarget FinancialTransactionRecord record, TransactionRepresentation representation);

}
