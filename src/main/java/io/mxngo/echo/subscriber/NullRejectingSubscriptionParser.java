package io.mxngo.echo.subscriber;

import io.mxngo.echo.Callback;
import io.mxngo.echo.EventCallback;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

/**
 * The {@code NullRejectingSubscriptionParser} class is a utility class responsible for parsing
 * subscriptions from a target object. This class rejects any null subscriptions.
 */
public final class NullRejectingSubscriptionParser {
    public static <T> Set<ISubscription<T>> parseSubscriptions(final Object target) {
        final Set<ISubscription<T>> subscriptions = new HashSet<>();
        final Class<?> targetClass = target.getClass();
        for (final Field field : targetClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(Callback.class)) {
                final ISubscription<T> subscription = parseSubscription(target, field);
                subscriptions.add(subscription);
            }
        }
        return subscriptions;
    }

    private static <T> ISubscription<T> parseSubscription(final Object target, final Field field) {
        if (!EventCallback.class.isAssignableFrom(field.getType())) {
            throw new IllegalArgumentException("Callback annotated fields must be of type EventCallback");
        }

        final Callback callbackAnnotation = field.getAnnotation(Callback.class);
        final int priority = callbackAnnotation.priority();

        EventCallback<T> eventCallback = null;
        try {
            field.setAccessible(true);
            eventCallback = (EventCallback<T>) field.get(target);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Error accessing Callback annotated field", e);
        }

        return new ImmutableSubscription<>(target, eventCallback, priority);
    }
}
