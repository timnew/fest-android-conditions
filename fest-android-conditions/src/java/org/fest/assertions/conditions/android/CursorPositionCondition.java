package org.fest.assertions.conditions.android;

import android.view.View;
import android.widget.EditText;
import org.fest.assertions.core.Condition;

import static java.lang.String.format;

public class CursorPositionCondition extends Condition<View> {

    private final int start;
    private final int end;

    public CursorPositionCondition(int start, int end) {
        super(format("Selected text from %d to %d", start, end));

        this.start = start;
        this.end = end;
    }

    public CursorPositionCondition(int position) {
        super(format("Cursor at %d", position));

        start = end = position;
    }

    @Override
    public boolean matches(View view) {
        if (view == null)
            return false;

        if (!EditText.class.isAssignableFrom(view.getClass()))
            return false;

        EditText editText = (EditText) view;

        return editText.getSelectionStart() == start && editText.getSelectionEnd() == end;
    }

    public static CursorPositionCondition cursorPosition(int position) {
        return new CursorPositionCondition(position);
    }

    public static CursorPositionCondition cursorPosition(int start, int end) {
        return new CursorPositionCondition(start, end);
    }
}
