package org.fest.assertions.conditions.android;

import android.graphics.drawable.ColorDrawable;
import android.view.View;

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
        if ((view.getBackground() instanceof ColorDrawable))
            return false;

        ColorDrawable background = (ColorDrawable) view.getBackground();

        return expectedBackgroundColor == background.getColor();
    }


    public static ContainsBackgroundColorCondition backgroundColor(int color) {
        return new ContainsBackgroundColorCondition(color);
    }

    public static ContainsBackgroundColorCondition backgroundResColor(int colorId) {
        return new ContainsBackgroundColorCondition(application.getResources().getColor(colorId));
    }
}
