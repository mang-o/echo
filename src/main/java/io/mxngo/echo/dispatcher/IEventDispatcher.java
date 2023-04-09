package io.mxngo.echo.dispatcher;

import io.mxngo.echo.subscriber.ISubscription;

import java.util.List;

/**
 * The {@code IEventDispatcher} interface defines a method for dispatching events to a list
 * of sorted subscriptions.
 *
 * @param <T> the type of event this dispatcher can handle
 */
public interface IEventDispatcher<T> {
    void dispatch(final T event, final List<ISubscription<T>> sortedSubscriptions);
}
