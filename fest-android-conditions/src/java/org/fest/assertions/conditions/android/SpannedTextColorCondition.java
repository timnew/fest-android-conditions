package org.fest.assertions.conditions.android;

import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import org.fest.assertions.core.Condition;
import org.robolectric.Robolectric;

import static java.lang.String.format;
import static org.robolectric.Robolectric.application;

public class SpannedTextColorCondition extends Condition<Spanned> {

    public static final String DESCRIPTION_TEMPLATE = "Foreground #%s from %d to %d";
    private int start;
    private int stop;
    private int expected;

    public SpannedTextColorCondition(int start, int stop, int expectedColor) {
        super(format(DESCRIPTION_TEMPLATE, expectedColor, start, stop));
        this.start = start;
        this.stop = stop;
        this.expected = expectedColor;
    }

    @Override
    public boolean matches(Spanned spanned) {
        ForegroundColorSpan[] spans = spanned.getSpans(start, stop, ForegroundColorSpan.class);

        if (spans.length == 0)
            return false;

        return spans[0].getForegroundColor() == expected;
    }

    public static SpannedTextColorCondition textColor(int color) {
        return textColor(color, 0, 0);
    }

    public static SpannedTextColorCondition textColor(int color, int start) {
        return textColor(color, start, start);
    }

    public static SpannedTextColorCondition textColor(int color, int start, int stop) {
        return new SpannedTextColorCondition(start, stop, color);
    }

    public static SpannedTextColorCondition textResColor(int resId) {
        return textColor(application.getResources().getColor(resId));
    }

    public static SpannedTextColorCondition textResColor(int resId, int start) {
        return textColor(application.getResources().getColor(resId), start, start);
    }

    public static SpannedTextColorCondition textResColor(int resId, int start, int stop) {
        return textColor(application.getResources().getColor(resId), start, stop);
    }

}
