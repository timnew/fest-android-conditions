package org.fest.assertions.conditions.android;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;

import org.fest.assertions.core.Condition;
import org.robolectric.Robolectric;

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

        if (expectedBackground.equals(background))
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

    public static ContainsBackgroundCondition background(int backgroundId) {
        Drawable background = Robolectric.application.getResources().getDrawable(backgroundId);

        return background(background);
    }

    public static ContainsBackgroundCondition background(Drawable background) {
        return new ContainsBackgroundCondition(background);
    }
}
