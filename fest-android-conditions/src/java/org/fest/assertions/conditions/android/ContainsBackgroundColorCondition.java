package org.fest.assertions.conditions.android;

import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;

import org.fest.assertions.core.Condition;

import static org.robolectric.Robolectric.application;

public class ContainsBackgroundColorCondition extends Condition<View> {

    public static final String DESCRIPTION = "backgroundColor";
    private final int expectedBackgroundColor;

    public ContainsBackgroundColorCondition(int expectedBackgroundColor) {
        super(DESCRIPTION);

        this.expectedBackgroundColor = expectedBackgroundColor;
    }

    @Override
    public boolean matches(View view) {
        ColorDrawable background = (ColorDrawable) view.getBackground();

        if (background != null && expectedBackgroundColor == background.getColor())
            return true;

        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View childView = viewGroup.getChildAt(i);
                if (matches(childView)) return true;
            }
        }

        return false;
    }

    public static ContainsBackgroundColorCondition backgroundColor(int color) {
        return new ContainsBackgroundColorCondition(color);
    }

    public static ContainsBackgroundColorCondition backgroundResColor(int colorId) {
        return new ContainsBackgroundColorCondition(application.getResources().getColor(colorId));
    }
}
