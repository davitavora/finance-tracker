package com.github.davitavora.jooq.repository;

import static com.github.davitavora.jooq.util.JooqOperation.optionalCondition;

import com.github.davitavora.jooq.model.projection.TransactionProjection;
import io.vobiscum.jooqpoc.domain.Tables;
import io.vobiscum.jooqpoc.domain.tables.records.FinancialTransactionRecord;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.tools.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class TransactionRepository {

    private final DSLContext jooq;

    public List<TransactionProjection> search(String name, Integer categoryId,
                                              LocalDate createdAt) {
        return jooq
            .select(
                Tables.FINANCIAL_TRANSACTION.asterisk()
                    .except(Tables.FINANCIAL_TRANSACTION.CATEGORY_ID),
                Tables.FINANCIAL_TRANSACTION.category())
            .from(Tables.FINANCIAL_TRANSACTION.join(Tables.CATEGORY)
                .on(Tables.CATEGORY.ID.eq(Tables.FINANCIAL_TRANSACTION.CATEGORY_ID)))
            .where(
                optionalCondition(name, StringUtils::isBlank, Tables.FINANCIAL_TRANSACTION.NAME.likeIgnoreCase("%" + name + "%")),
                optionalCondition(categoryId, Objects::isNull, Tables.FINANCIAL_TRANSACTION.CATEGORY_ID.eq(categoryId)),
                optionalCondition(createdAt, Objects::isNull, Tables.FINANCIAL_TRANSACTION.CREATED_AT.eq(createdAt))
            )
            .fetchInto(TransactionProjection.class);
    }

    @Transactional
    public void save(FinancialTransactionRecord record) {
        jooq.executeInsert(record);
    }

}
