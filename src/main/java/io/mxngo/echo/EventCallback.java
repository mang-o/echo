package io.mxngo.echo;

import io.mxngo.echo.filter.IEventFilter;

import java.util.function.Consumer;

/**
 * The {@code EventCallback} class represents a callback function for a specific event type, along with an optional
 * array of filters that must pass for the callback to be executed.
 *
 * @param <T> the type of event this callback can handle
 */
public final class EventCallback<T> {

    private final Consumer<T> callback;
    private final IEventFilter[] filters;
    private final Class<T> eventType;

    public EventCallback(final Consumer<T> callback, final Class<T> eventType) {
        this(callback, new IEventFilter[0], eventType);
    }

    public EventCallback(final Consumer<T> callback, final IEventFilter[] filters, Class<T> eventType) {
        this.callback = callback;
        this.filters = filters;

        this.eventType = eventType;
    }

    public Consumer<T> getCallback() {
        return callback;
    }

    public IEventFilter[] getFilters() {
        return filters;
    }

    public Class<T> getEventType() {
        return eventType;
    }
}
