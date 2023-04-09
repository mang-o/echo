package io.mxngo.echo;

/**
 * The {@code IEventBus} interface defines methods for managing event subscriptions, unsubscriptions, and event
 * emission.
 *
 * @param <T> the type of event this event bus can handle
 */
public interface IEventBus<T> {
    void subscribe(final Object object);

    void unsubscribe(final Object object);

    void emit(final T event);
}
