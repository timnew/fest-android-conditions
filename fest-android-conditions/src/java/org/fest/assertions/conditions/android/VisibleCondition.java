package org.fest.assertions.conditions.android;

import android.view.View;
import org.fest.assertions.core.Condition;

import static android.view.View.GONE;
import static org.fest.assertions.conditions.android.HasPropertyCondition.HasPropertyConditionPrimitive;
import static java.lang.String.format;

public class VisibleCondition extends Condition<View> {
    public static final String DESCRIPTION_TEMPLATE = "visible \"%s\"";
    public static final VisibleCondition INVISIBLE = new VisibleCondition(false);
    public static final VisibleCondition VISIBLE = new VisibleCondition(true);

    @SuppressWarnings("unchecked")
    public static final HasPropertyConditionPrimitive NOT_DISPLAYED = new HasPropertyConditionPrimitive("visibility", GONE);
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
}
