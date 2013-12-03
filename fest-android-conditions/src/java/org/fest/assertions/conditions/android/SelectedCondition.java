package org.fest.assertions.conditions.android;

import android.view.View;
import org.fest.assertions.core.Condition;

import static java.lang.String.format;

public class SelectedCondition extends Condition<View> {
    public static final String DESCRIPTION_TEMPLATE = "selected \"%s\"";
    private final boolean expectedSelected;

    public SelectedCondition(boolean expectedVisible) {
        super(format(DESCRIPTION_TEMPLATE, expectedVisible));

        this.expectedSelected = expectedVisible;
    }

    @Override
    public boolean matches(View view) {
        return view.isSelected() == expectedSelected;
    }

    public static SelectedCondition isSelected() {
        return new SelectedCondition(true);
    }

    public static SelectedCondition unSelected() {
        return new SelectedCondition(false);
    }
}
