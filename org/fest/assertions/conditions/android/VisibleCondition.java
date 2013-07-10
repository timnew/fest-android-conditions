package org.fest.asstions.conditions.android;

import android.view.View;
import org.fest.assertions.core.Condition;

import static android.view.View.GONE;
import static java.lang.String.format;

public class VisibleCondition extends Condition<View> {
    public static final String DESCRIPTION_TEMPLATE = "visible \"%s\"";
    private final boolean expectedVisible;

    public VisibleCondition(boolean expectedVisible) {
        super(format(DESCRIPTION_TEMPLATE, expectedVisible));

        this.expectedVisible = expectedVisible;
    }

    @Override
    public boolean matches(View view) {

        boolean actualVisible = view.getVisibility() == View.VISIBLE;

        return actualVisible == expectedVisible;
    }

    public static VisibleCondition invisible() {
        return new VisibleCondition(false);
    }

    public static VisibleCondition visible() {
        return new VisibleCondition(true);
    }

    public static HasPropertyCondition<Integer> notDisplayed() {
        return new HasPropertyCondition("visibility", int.class, GONE);
    }
}
