package com.github.davitavora.jooq.mapper;

import com.github.davitavora.jooq.model.representation.TransactionRepresentation;
import io.vobiscum.jooqpoc.domain.tables.records.FinancialTransactionRecord;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface TransactionMapper {

    TransactionRepresentation asRepresentation(FinancialTransactionRecord projection);

    List<TransactionRepresentation> asRepresentation(List<FinancialTransactionRecord> projections);

    @Mapping(target = "id", ignore = true)
    FinancialTransactionRecord asNewRecord(TransactionRepresentation transaction);

    void update(@MappingTarget FinancialTransactionRecord record, TransactionRepresentation representation);

}
