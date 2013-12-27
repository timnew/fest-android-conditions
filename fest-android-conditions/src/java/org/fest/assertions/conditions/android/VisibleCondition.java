package org.fest.assertions.conditions.android;

import android.view.View;

import org.fest.assertions.core.Condition;

import static java.lang.String.format;

public class VisibleCondition extends Condition<View> {
    public static final String DESCRIPTION_TEMPLATE = "visible \"%s\"";

    public static final VisibleCondition INVISIBLE = new VisibleCondition(View.INVISIBLE);
    public static final VisibleCondition VISIBLE = new VisibleCondition(View.VISIBLE);
    public static final VisibleCondition GONE = new VisibleCondition(View.GONE);
    public static final VisibleCondition NOT_VISIBLE = new VisibleCondition(View.VISIBLE, true);

    private final int expectedVisible;
    private boolean not;

    public VisibleCondition(int expectedVisible) {
        this(expectedVisible, false);
    }

    public VisibleCondition(int expectedVisible, boolean not) {
        super(format(DESCRIPTION_TEMPLATE, expectedVisible));

        this.not = not;

        this.expectedVisible = expectedVisible;
    }

    @Override
    public boolean matches(View view) {
        return (view.getVisibility() == expectedVisible) ^ not;
    }
}
