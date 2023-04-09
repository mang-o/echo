package io.mxngo.echo.subscriber;

import io.mxngo.echo.EventCallback;

/**
 * The {@code ISubscription} interface defines methods for managing an event subscription.
 *
 * @param <T> the type of event this subscription can handle
 */
public interface ISubscription<T>  {
    Object subscriber();

    EventCallback<T> eventCallback();

    int priority();
}
