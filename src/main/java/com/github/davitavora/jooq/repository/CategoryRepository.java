package com.github.davitavora.jooq.repository;

import static com.github.davitavora.jooq.util.JooqOperation.optionalCondition;
import static org.jooq.impl.DSL.asterisk;

import com.github.davitavora.jooq.model.projection.CategoryProjection;
import io.vobiscum.jooqpoc.domain.Tables;
import io.vobiscum.jooqpoc.domain.enums.CategoryType;
import io.vobiscum.jooqpoc.domain.tables.records.CategoryRecord;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.tools.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class CategoryRepository {

    private final DSLContext jooq;

    public List<CategoryProjection> search(String name, CategoryType type) {
        return jooq.select(asterisk())
            .from(Tables.CATEGORY)
            .where(
                optionalCondition(name, StringUtils::isBlank, Tables.CATEGORY.NAME.likeIgnoreCase("%" + name + "%")),
                optionalCondition(type, Objects::isNull, Tables.CATEGORY.TYPE.eq(type))
            )
            .fetchInto(CategoryProjection.class);
    }

    @Transactional
    public void save(CategoryRecord record) {
        jooq.executeInsert(record);
    }

    public Optional<CategoryProjection> findBy(Integer id) {
        return jooq.select(asterisk())
            .from(Tables.CATEGORY)
            .where(
                Tables.CATEGORY.ID.eq(id)
            ).fetchOptionalInto(CategoryProjection.class);
    }

    public void delete(Integer id) {
        jooq.delete(Tables.CATEGORY).where(Tables.CATEGORY.ID.eq(id)).execute();
    }

}
