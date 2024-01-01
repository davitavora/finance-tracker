package com.github.davitavora.jooq.repository;

import static com.github.davitavora.jooq.util.JooqOperation.conditionIf;
import static org.jooq.impl.DSL.asterisk;

import io.micrometer.common.util.StringUtils;
import io.vobiscum.jooqpoc.domain.Tables;
import io.vobiscum.jooqpoc.domain.enums.CategoryType;
import io.vobiscum.jooqpoc.domain.tables.records.CategoryRecord;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CategoryRepository {

    private final DSLContext jooq;

    public List<CategoryRecord> search(String name, CategoryType type) {
        return jooq.select(asterisk())
            .from(Tables.CATEGORY)
            .where(
                conditionIf(Tables.CATEGORY.NAME.likeIgnoreCase("%" + name + "%"), name, StringUtils::isNotBlank),
                conditionIf(Tables.CATEGORY.TYPE.eq(type), type, Objects::nonNull)
            )
            .fetchInto(CategoryRecord.class);
    }

    public void save(CategoryRecord record) {
        record.store();
    }

    public void update(CategoryRecord record) {
        record.update();
    }

    public Optional<CategoryRecord> findBy(Integer id) {
        return jooq.select(asterisk())
            .from(Tables.CATEGORY)
            .where(
                Tables.CATEGORY.ID.eq(id)
            ).fetchOptionalInto(CategoryRecord.class);
    }

    public void delete(Integer id) {
        jooq.delete(Tables.CATEGORY).where(Tables.CATEGORY.ID.eq(id)).execute();
    }

}
