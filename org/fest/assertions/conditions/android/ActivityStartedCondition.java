package org.fest.asstions.conditions.android;

import android.app.Activity;
import android.content.Intent;

import com.xtremelabs.robolectric.shadows.ShadowActivity;
import com.xtremelabs.robolectric.shadows.ShadowIntent;

import org.fest.assertions.core.Condition;

import java.io.Serializable;

import static com.xtremelabs.robolectric.Robolectric.shadowOf;
import static org.fest.assertions.api.Assertions.assertThat;

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
