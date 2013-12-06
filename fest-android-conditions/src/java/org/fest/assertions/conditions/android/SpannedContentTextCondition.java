package org.fest.assertions.conditions.android;

import android.text.Spanned;

import org.fest.assertions.core.Condition;

import static java.lang.String.format;

public class SpannedContentTextCondition extends Condition<Spanned> {

    public static final String DESCRIPTION_TEMPLATE = "content text \"%s\"";

    private String expected;

    public SpannedContentTextCondition(String expected) {
        super(format(DESCRIPTION_TEMPLATE, expected));
        this.expected = expected;
    }

    @Override
    public boolean matches(Spanned spanned) {
        return spanned.toString().compareTo(expected) == 0;
    }

    public static SpannedContentTextCondition contentText(String text) {
        return new SpannedContentTextCondition(text);
    }
}
