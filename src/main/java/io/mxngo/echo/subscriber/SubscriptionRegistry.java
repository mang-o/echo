package io.mxngo.echo.subscriber;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The {@code SubscriptionRegistry} class is a utility class responsible for managing a set of
 * subscriptions.
 *
 * @param <T> the type of event this subscription registry can handle
 */
public final class SubscriptionRegistry<T> {
    private final Set<ISubscription<T>> subscriptions;

    public SubscriptionRegistry() {
        this.subscriptions = ConcurrentHashMap.newKeySet();
    }

    public synchronized void add(final ISubscription<T> subscription) {
        subscriptions.add(subscription);
    }

    public synchronized void removeSubscriptionsOfSubscriber(final Object subscriber) {
        subscriptions.removeIf(subscription -> subscription.subscriber() == subscriber);
    }

    public List<ISubscription<T>> getSortedSubscriptions() {
        final List<ISubscription<T>> sortedSubscriptions = new ArrayList<>(subscriptions);
        sortedSubscriptions.sort(Comparator.comparingInt(ISubscription<T>::priority).reversed());
        return sortedSubscriptions;
    }
}
