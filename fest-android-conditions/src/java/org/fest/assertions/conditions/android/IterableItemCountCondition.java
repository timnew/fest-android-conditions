package org.fest.assertions.conditions.android;

import org.fest.assertions.core.Condition;

import static java.lang.String.format;

public class IterableItemCountCondition<T> extends Condition<Iterable<T>> {

    private Condition<T> childCondition;
    private CountCriteria criteria;

    private IterableItemCountCondition(String message, Condition<T> childCondition, CountCriteria criteria) {
        super(message);

        this.childCondition = childCondition;
        this.criteria = criteria;
    }

    @Override
    public boolean matches(Iterable<T> items) {
        int total = 0;
        int count = 0;

        for (T item : items) {
            total++;

            if (childCondition.matches(item))
                count++;
        }

        return criteria.match(total, count);
    }

    public static <T> IterableItemCountCondition<T> each(Condition<T> childCondition) {
        return all(childCondition);
    }

    public static <T> IterableItemCountCondition<T> all(Condition<T> childCondition) {
        String message = format("All items satisfy %s", childCondition.description().toString());
        return new IterableItemCountCondition<T>(message, childCondition, new CountCriteria() {
            @Override
            public boolean match(int total, int count) {
                return total == count;
            }
        });
    }

    public static <T> IterableItemCountCondition<T> any(Condition<T> childCondition) {
        String message = format("Exists items satisfy %s", childCondition.description().toString());
        return new IterableItemCountCondition<T>(message, childCondition, new CountCriteria() {
            @Override
            public boolean match(int total, int count) {
                return count > 0;
            }
        });
    }

    public static <T> IterableItemCountCondition<T> none(Condition<T> childCondition) {
        String message = format("None items satisfy %s", childCondition.description().toString());
        return new IterableItemCountCondition<T>(message, childCondition, new CountCriteria() {
            @Override
            public boolean match(int total, int count) {
                return count == 0;
            }
        });
    }

    public static <T> IterableItemCountCondition<T> atLeast(Condition<T> childCondition, final int minimum) {
        String message = format("At least %d items satisfy %s", minimum, childCondition.description().toString());
        return new IterableItemCountCondition<T>(message, childCondition, new CountCriteria() {
            @Override
            public boolean match(int total, int count) {
                return count >= minimum;
            }
        });
    }

    public static <T> IterableItemCountCondition<T> atMost(Condition<T> childCondition, final int maximum) {
        String message = format("At most %d items satisfy %s", maximum, childCondition.description().toString());
        return new IterableItemCountCondition<T>(message, childCondition, new CountCriteria() {
            @Override
            public boolean match(int total, int count) {
                return count <= maximum;
            }
        });
    }

    public static interface CountCriteria {
        boolean match(int total, int count);
    }
}
