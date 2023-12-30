package com.github.davitavora.jooq.mapper;

import com.github.davitavora.jooq.model.command.CreateTransactionCommand;
import com.github.davitavora.jooq.model.projection.TransactionProjection;
import com.github.davitavora.jooq.model.representation.TransactionRepresentation;
import io.vobiscum.jooqpoc.domain.tables.records.FinancialTransactionRecord;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper
public interface TransactionMapper {

    List<TransactionRepresentation> toRepresentation(List<TransactionProjection> projections);

    TransactionRepresentation toRepresentation(TransactionProjection projection);

    FinancialTransactionRecord toRecord(CreateTransactionCommand command);

}
