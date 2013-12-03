package org.fest.assertions.conditions.android;

import android.app.Application;
import android.content.Intent;
import org.fest.assertions.core.Condition;
import org.robolectric.shadows.ShadowApplication;

import static org.robolectric.Robolectric.shadowOf;
import static java.lang.String.format;

public class ServiceStartedCondition extends Condition<Application> {

    private Intent expectedService;

    public ServiceStartedCondition(Intent expectedService) {
        super(format("Service %s started", expectedService.getType()));

        this.expectedService = expectedService;
    }

    @Override
    public boolean matches(Application application) {
        ShadowApplication shadowApplication = shadowOf(application);
        Intent nextStartedService = shadowApplication.getNextStartedService();

        return nextStartedService == expectedService;
    }

    public static ServiceStartedCondition startedService(Intent expectedService) {
        return new ServiceStartedCondition(expectedService);
    }
}
