package org.fest.assertions.conditions.android;

import android.view.View;
import org.fest.assertions.core.Condition;

public class ViewEnabledCondition extends Condition<View> {
    private final boolean enabled;

    public ViewEnabledCondition(boolean enabled) {
        super(enabled ? "Enabled" : "Disabled");

        this.enabled = enabled;
    }

    @Override
    public boolean matches(View view) {
        return view.isEnabled() == enabled;
    }

    public static ViewEnabledCondition enabled() {
        return new ViewEnabledCondition(true);
    }

    public static ViewEnabledCondition disabled() {
        return new ViewEnabledCondition(false);
    }
}
