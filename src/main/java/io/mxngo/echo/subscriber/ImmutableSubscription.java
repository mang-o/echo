package io.mxngo.echo.subscriber;

import io.mxngo.echo.EventCallback;

/**
 * The {@code ImmutableSubscription} class is an implementation of the {@code ISubscription} interface,
 * providing an immutable subscription object.
 *
 * @param <T> the type of event this subscription can handle
 */
public record ImmutableSubscription<T>(Object subscriber, EventCallback<T> eventCallback,
                                       int priority) implements ISubscription<T>, Comparable<ImmutableSubscription<T>> {

    @Override
    public int compareTo(final ImmutableSubscription<T> other) {
        return Integer.compare(other.priority, this.priority);
    }
}
