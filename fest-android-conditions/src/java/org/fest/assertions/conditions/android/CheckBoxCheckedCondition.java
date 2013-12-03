package org.fest.assertions.conditions.android;

import android.view.View;
import android.widget.CheckBox;
import org.fest.assertions.core.Condition;

public class CheckBoxCheckedCondition extends Condition<View> {
    private final boolean checked;

    public CheckBoxCheckedCondition(boolean checked) {
        super(checked ? "Checked" : "Unchecked");

        this.checked = checked;
    }

    @Override
    public boolean matches(View view) {
        CheckBox checkBox = (CheckBox) view;

        return checkBox.isChecked() == checked;
    }

    public static CheckBoxCheckedCondition checked() {
        return new CheckBoxCheckedCondition(true);
    }

    public static CheckBoxCheckedCondition unchecked() {
        return new CheckBoxCheckedCondition(false);
    }
}
