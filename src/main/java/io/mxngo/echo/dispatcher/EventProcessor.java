package io.mxngo.echo.dispatcher;

import io.mxngo.echo.subscriber.ISubscription;
import io.mxngo.echo.subscriber.pool.ISubscriptionPool;

import java.util.List;

/**
 * The {@code EventProcessor} class is responsible for processing events by dispatching them
 * according to the provided {@code DispatchStrategy} and {@code ISubscriptionPool}.
 *
 * @param <T> the type of event this processor can handle
 */
public final class EventProcessor<T> {
    private final ISubscriptionPool<T> subscriptionPool;
    private final DispatchStrategy<T> dispatchStrategy;

    public EventProcessor(final ISubscriptionPool<T> subscriptionPool, final DispatchStrategy<T> dispatchStrategy) {
        this.subscriptionPool = subscriptionPool;
        this.dispatchStrategy = dispatchStrategy;
    }

    public void processEvent(final T event) {
        final List<ISubscription<T>> sortedSubscriptions = this.subscriptionPool.getSortedSubscriptions();
        dispatchStrategy.dispatch(event, sortedSubscriptions);
    }
}
