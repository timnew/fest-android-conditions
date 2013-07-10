package org.fest.asstions.conditions.android;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import org.fest.assertions.core.Condition;

import static java.lang.String.format;
import static org.fest.assertions.api.Assertions.assertThat;

public class DialogShownCondition extends Condition<FragmentActivity> {
    private final String dialogTag;

    public DialogShownCondition(String dialogTag) {
        super(format("Dialog of tag \"%s\" is shown", dialogTag));

        this.dialogTag = dialogTag;
    }

    @Override
    public boolean matches(FragmentActivity activity) {
        assertThat(activity).isNotNull();

        FragmentManager supportFragmentManager = activity.getSupportFragmentManager();

        DialogFragment dialogFragment = (DialogFragment) supportFragmentManager.findFragmentByTag(dialogTag);

        return dialogFragment != null;
    }

    public static DialogShownCondition popedUpDialog(String dialogTag) {
        return new DialogShownCondition(dialogTag);
    }
}
