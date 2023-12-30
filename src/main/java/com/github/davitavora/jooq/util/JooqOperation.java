package com.github.davitavora.jooq.util;

import static org.jooq.impl.DSL.noCondition;

import java.util.function.Predicate;
import lombok.experimental.UtilityClass;
import org.jooq.Condition;

@UtilityClass
public class JooqOperation {

    public static <T> Condition optionalCondition(T value, Predicate<T> predicate, Condition condition) {
        return predicate.test(value) ? noCondition() : condition;
    }

}
