package com.github.davitavora.jooq.repository;

import static com.github.davitavora.jooq.util.JooqOperation.conditionIf;

import com.github.jooq.domain.Tables;
import com.github.jooq.domain.tables.records.FinancialTransactionRecord;
import io.micrometer.common.util.StringUtils;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TransactionRepository {

    private final DSLContext jooq;

    public List<FinancialTransactionRecord> search(String name,
                                                   Integer categoryId,
                                                   LocalDate createdAt) {
        return jooq
            .select(Tables.FINANCIAL_TRANSACTION.asterisk())
            .from(Tables.FINANCIAL_TRANSACTION)
            .where(
                conditionIf(Tables.FINANCIAL_TRANSACTION.NAME.likeIgnoreCase("%" + name + "%"), name, StringUtils::isNotBlank),
                conditionIf(Tables.FINANCIAL_TRANSACTION.CATEGORY_ID.eq(categoryId), categoryId, Objects::nonNull),
                conditionIf(Tables.FINANCIAL_TRANSACTION.CREATED_AT.eq(createdAt), createdAt, Objects::nonNull)
            )
            .fetchInto(FinancialTransactionRecord.class);
    }

    public Optional<FinancialTransactionRecord> findBy(Long id) {
        return jooq
            .select(Tables.FINANCIAL_TRANSACTION.asterisk())
            .from(Tables.FINANCIAL_TRANSACTION)
            .where(Tables.FINANCIAL_TRANSACTION.ID.eq(id))
            .fetchOptionalInto(FinancialTransactionRecord.class);
    }

    public void save(FinancialTransactionRecord record) {
        jooq.executeInsert(record);
    }

    public void update(FinancialTransactionRecord record) {
        record.update();
    }

    public void delete(Long id) {
        jooq.delete(Tables.FINANCIAL_TRANSACTION).where(Tables.FINANCIAL_TRANSACTION.ID.eq(id)).execute();
    }

}
