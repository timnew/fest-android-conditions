package org.fest.assertions.conditions.android;

import com.google.common.base.Predicate;
import org.fest.assertions.core.Condition;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;

import static com.google.common.collect.Iterables.find;
import static java.lang.String.format;
import static java.util.Arrays.asList;
import static org.fest.assertions.api.Assertions.assertThat;

public abstract class HasPropertyCondition<T> extends Condition {

    public static final HashSet<String> PRIMITIVES = new HashSet<String>();

    private static void registerPrimitive(Class... types) {
        for (Class type : types)
            PRIMITIVES.add(type.getSimpleName());
    }

    static {
        registerPrimitive(
                byte.class,
                char.class,
                short.class,
                int.class,
                long.class,
                float.class,
                double.class);

        registerPrimitive(
                Byte.class,
                Character.class,
                Short.class,
                Integer.class,
                Long.class,
                Float.class,
                Double.class,
                String.class);
    }


    protected final T expected;
    private final String getterName;

    public HasPropertyCondition(String name, T expected) {
        super(format("has property \"%s\" with value \"%s\"", name, expected));
        this.expected = expected;

        getterName = makeAccessorName("get", name);
    }

    @Override
    public boolean matches(Object target) {
        if (target == null)
            return false;

        Object actual;
        try {
            Class<?> targetClass = target.getClass();
            Method getter = targetClass.getMethod(getterName);

            actual = getter.invoke(target);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return false;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            return false;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return false;
        }

        return assertEqual(actual);
    }

    protected abstract boolean assertEqual(Object actual);


    private static String makeAccessorName(String prefix, String name) {
        char firstLetter = Character.toUpperCase(name.charAt(0));
        return prefix + firstLetter + name.substring(1);
    }

    public static <T> HasPropertyCondition<T> property(String name, T expected) {
        if (expected == null)
            return new HasPropertyConditionNull<T>(name, expected);

        String typeName = expected.getClass().getSimpleName();

        if (PRIMITIVES.contains(typeName)) {
            return new HasPropertyConditionPrimitive<T>(name, expected);
        } else {
            return new HasPropertyConditionObject<T>(name, expected);
        }
    }

    public static class HasPropertyConditionNull<T> extends HasPropertyCondition<T> {
        public HasPropertyConditionNull(String name, T expected) {
            super(name, expected);
        }

        @Override
        protected boolean assertEqual(Object actual) {
            return actual == null;
        }
    }

    public static class HasPropertyConditionPrimitive<T> extends HasPropertyCondition<T> {
        public HasPropertyConditionPrimitive(String name, T expected) {
            super(name, expected);
        }

        @Override
        protected boolean assertEqual(Object actual) {
            return actual.equals(expected);
        }
    }

    public static class HasPropertyConditionObject<T> extends HasPropertyCondition<T> {
        public HasPropertyConditionObject(String name, T expected) {
            super(name, expected);
        }

        @Override
        protected boolean assertEqual(Object actual) {
            boolean result = true;

            try {
                assertThat(actual).isEqualsToByComparingFields(expected);
            } catch (RuntimeException ex) {
                result = false;
            }

            return result;
        }
    }

    public static <T> void setProperty(Object target, String name, T value) {
        String setterName = makeAccessorName("set", name);
        try {
            Method setter = getSetterMethod(target.getClass(), setterName);
            setter.invoke(target, value);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static Method getSetterMethod(Class<?> hostClass, final String setterName) {
        Method[] methods = hostClass.getMethods();

        return find(asList(methods), new Predicate<Method>() {
            @Override
            public boolean apply(Method method) {
                return method.getName().equals(setterName);
            }
        });
    }
}

