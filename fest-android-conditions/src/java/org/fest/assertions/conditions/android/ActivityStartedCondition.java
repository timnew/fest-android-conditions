package org.fest.assertions.conditions.android;

import android.app.Activity;
import android.content.Intent;

import org.fest.assertions.core.Condition;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowIntent;

import java.io.Serializable;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.robolectric.Robolectric.shadowOf;

public class ActivityStartedCondition extends Condition<Activity> {

    private final String expectedActivityName;
    private ShadowIntent shadowIntent;

    public ActivityStartedCondition(Class expectedActivityClass) {
        expectedActivityName = expectedActivityClass.getName();
    }

    @Override
    public boolean matches(Activity activity) {

        ShadowActivity shadowActivity = shadowOf(activity);
        Intent intent = shadowActivity.getNextStartedActivity();
        shadowIntent = shadowOf(intent);

        String actualActivityName = shadowIntent.getIntentClass().getName();

        return expectedActivityName == actualActivityName;
    }

    public ShadowIntent getIntent() {
        return shadowIntent;
    }

    public static ActivityStartedCondition startedActivity(Class expectedActivityClass) {
        return new ActivityStartedCondition(expectedActivityClass);
    }

    public WithSerializableData withSerializableData(String name, Serializable expectedValue) {
        return new WithSerializableData(this, name, expectedValue);
    }

    public class WithSerializableData extends Condition<Activity> {

        private final ActivityStartedCondition parentCondition;
        private final String name;
        private final Serializable expectedValue;

        public WithSerializableData(ActivityStartedCondition parentCondition, String name, Serializable expectedValue) {
            this.parentCondition = parentCondition;
            this.name = name;
            this.expectedValue = expectedValue;
        }

        @Override
        public boolean matches(Activity activity) {
            if (!parentCondition.matches(activity)) {
                return false;
            }

            Serializable data = parentCondition.getIntent().getSerializableExtra(name);

            assertThat(data).isEqualsToByComparingFields(expectedValue);

            return true;
        }
    }
}
