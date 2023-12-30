package com.github.davitavora.jooq.util;

import static org.apache.commons.lang3.ObjectUtils.allNull;
import static org.jooq.impl.DSL.noCondition;
import static org.jooq.impl.DSL.noField;
import static org.jooq.impl.DSL.noTable;

import java.util.function.Predicate;
import lombok.experimental.UtilityClass;
import org.jooq.Condition;
import org.jooq.Function3;
import org.jooq.SelectFieldOrAsterisk;
import org.jooq.TableLike;

@UtilityClass
public class JooqOperation {

    public static <T1, T2, T3, R> Function3<T1, T2, T3, R> nullableMapper(Function3<T1, T2, T3, R> constructor) {
        return (a1, a2, a3) -> allNull(a1, a2, a3) ? null : constructor.apply(a1, a2, a3);
    }

    public static <T> Condition conditionIf(Condition condition, T value, Predicate<T> predicate) {
        return optionalElement(condition, noCondition(), value, predicate);
    }

    public static <T> SelectFieldOrAsterisk fieldIf(SelectFieldOrAsterisk field, T value, Predicate<T> predicate) {
        return optionalElement(field, noField(), value, predicate);
    }

    public static <T> TableLike<?> tableIf(TableLike<?> table, T value, Predicate<T> predicate) {
        return optionalElement(table, noTable(), value, predicate);
    }

    private static <T, R> R optionalElement(R valueIfTrue, R valueIfFalse, T value, Predicate<T> predicate) {
        return predicate.test(value) ? valueIfTrue : valueIfFalse;
    }

}
