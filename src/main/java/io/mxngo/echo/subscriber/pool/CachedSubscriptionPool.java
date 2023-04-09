package io.mxngo.echo.subscriber.pool;

import io.mxngo.echo.subscriber.ISubscription;
import io.mxngo.echo.subscriber.NullRejectingSubscriptionParser;
import io.mxngo.echo.subscriber.SubscriptionRegistry;

import java.util.List;
import java.util.Set;

/**
 * The {@code CachedSubscriptionPool} class is an implementation of the {@code ISubscriptionPool} interface,
 * providing a caching mechanism for subscriptions.
 *
 * @param <T> the type of event this subscription pool can handle
 */
public final class CachedSubscriptionPool<T> implements ISubscriptionPool<T> {
    private final SubscriptionRegistry<T> registry;

    public CachedSubscriptionPool() {
        this.registry = new SubscriptionRegistry<>();
    }

    public void register(final Object subscriber) {
        final Set<ISubscription<T>> subscriptions = NullRejectingSubscriptionParser.parseSubscriptions(subscriber);
        for (final ISubscription<T> subscription : subscriptions) {
            registry.add(subscription);
        }
    }

    public void unregister(final Object subscriber) {
        registry.removeSubscriptionsOfSubscriber(subscriber);
    }

    public List<ISubscription<T>> getSortedSubscriptions() {
        return registry.getSortedSubscriptions();
    }
}
