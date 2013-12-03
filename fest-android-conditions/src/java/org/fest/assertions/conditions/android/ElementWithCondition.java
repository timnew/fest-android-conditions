package org.fest.assertions.conditions.android;

import com.google.common.base.Predicate;
import org.fest.assertions.core.Condition;

import static com.google.common.collect.Iterables.any;

public class ElementWithCondition extends Condition<Iterable> {

    private final Condition elementCondition;

    public ElementWithCondition(Condition elementCondition) {
        super("Element with");

        this.elementCondition = elementCondition;
    }

    @Override
    public boolean matches(Iterable iterable) {
        return any(iterable, new Predicate() {
            @Override
            public boolean apply(Object target) {
                return elementCondition.matches(target);
            }
        });
    }

    public static ElementWithCondition elementWith(Condition elementCondition) {
        return new ElementWithCondition(elementCondition);
    }
}
