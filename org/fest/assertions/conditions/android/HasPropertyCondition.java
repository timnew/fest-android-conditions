package org.fest.asstions.conditions.android;

import org.fest.assertions.core.Condition;

import static java.lang.String.format;
import static org.fest.assertions.api.Assertions.assertThat;

public class HasPropertyCondition<T> extends Condition {

    private final T expected;
    private final String name;
    private final Class<T> type;

    public HasPropertyCondition(String name, Class<T> type, T expected) {
        super(format("has property \"%s\" with value \"%s\"", name, expected));
        this.expected = expected;

        this.name = name;
        this.type = type;
    }

    @Override
    public boolean matches(Object target) {
        if (target == null)
            return false;

        T actual = org.fest.reflect.core.Reflection.property(name).ofType(type).in(target).get();

        assertThat(actual).isEqualsToByComparingFields(expected);

        return true;
    }

    public static <T> HasPropertyCondition<?> property(String name, T expected) {
        return new HasPropertyCondition<T>(name, (Class<T>) expected.getClass(), expected);
    }

    public static <T> HasPropertyCondition<?> property(String name, Class<T> type, T expected) {
        return new HasPropertyCondition<T>(name, type, expected);
    }

    public static <T> void setProperty(Object target, String name, Class<T> type, T value) {
        org.fest.reflect.core.Reflection.property(name).ofType(type).in(target).set(value);
    }

    public static <T> void setProperty(Object target, String name, T value) {
        org.fest.reflect.core.Reflection.property(name).ofType((Class<T>) value.getClass()).in(target).set(value);
    }
}

