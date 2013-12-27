package org.fest.assertions.conditions.android;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;

import org.fest.assertions.core.Condition;

import static org.robolectric.Robolectric.application;

public class ContainsBackgroundCondition extends Condition<View> {
    public static final String DESCRIPTION = "background";
    private final Drawable expectedBackground;

    public ContainsBackgroundCondition(Drawable expectedBackground) {
        super(DESCRIPTION);

        this.expectedBackground = expectedBackground;
    }

    @Override
    public boolean matches(View view) {
        Drawable background = view.getBackground();

        return expectedBackground.equals(background);
    }

    public static ContainsBackgroundCondition background(int backgroundId) {
        Drawable background = application.getResources().getDrawable(backgroundId);

        return background(background);
    }

    public static ContainsBackgroundCondition background(Drawable background) {
        return new ContainsBackgroundCondition(background);
    }
}

