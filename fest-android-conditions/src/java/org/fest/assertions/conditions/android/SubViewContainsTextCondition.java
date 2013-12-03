package org.fest.assertions.conditions.android;

import android.view.View;
import org.robolectric.Robolectric;

import static java.lang.String.format;

@SuppressWarnings("UnusedDeclaration")
public class SubViewContainsTextCondition extends ContainsTextCondition {

    private final int subViewId;

    public SubViewContainsTextCondition(int subViewId, String expectedText) {
        super(expectedText);

        this.subViewId = subViewId;
    }

    @Override
    public boolean matches(View view) {
        View subView = view.findViewById(subViewId);
        return super.matches(subView);
    }

    public static SubViewContainsTextCondition subViewWithText(int subViewId, Object expectedText) {
        return new SubViewContainsTextCondition(subViewId, expectedText.toString());
    }

    public static SubViewContainsTextCondition subViewWithText(int subViewId, String expectedText) {
        return new SubViewContainsTextCondition(subViewId, expectedText);
    }

    public static SubViewContainsTextCondition subViewWithText(int subViewId, String format, Object... args) {
        return new SubViewContainsTextCondition(subViewId, format(format, args));
    }

    public static SubViewContainsTextCondition subViewWithText(int subViewId, int formatId, Object... args) {
        return new SubViewContainsTextCondition(subViewId, format(Robolectric.application.getString(formatId), args));
    }

    public static SubViewContainsTextCondition subViewWithoutText(int subViewId) {
        return new SubViewContainsTextCondition(subViewId, "");
    }
}

