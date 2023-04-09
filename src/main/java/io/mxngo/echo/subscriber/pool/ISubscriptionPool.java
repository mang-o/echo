package io.mxngo.echo.subscriber.pool;

import io.mxngo.echo.subscriber.ISubscription;

import java.util.List;

/**
 * The {@code ISubscriptionPool} interface defines methods for managing subscriptions within an event bus.
 *
 * @param <T> the type of event this subscription pool can handle
 */
public interface ISubscriptionPool<T> {
    void register(final Object subscriber);

    void unregister(final Object subscriber);

    List<ISubscription<T>> getSortedSubscriptions();
}
