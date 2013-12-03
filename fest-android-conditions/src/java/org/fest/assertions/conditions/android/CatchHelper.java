package org.fest.assertions.conditions.android;

public class CatchHelper {
    public static <T extends Throwable> T catchException(Class<T> exceptionClass, Runnable behavior) {
        Throwable caught = null;

        try {
            behavior.run();
        } catch (Throwable ex) {
            caught = ex;
        }

        if (caught != null && exceptionClass.isAssignableFrom(caught.getClass()))
            //noinspection unchecked
            return (T) caught;

        return null;
    }
}
