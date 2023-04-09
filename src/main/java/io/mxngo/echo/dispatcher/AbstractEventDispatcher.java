package io.mxngo.echo.dispatcher;

import io.mxngo.echo.EventCallback;
import io.mxngo.echo.filter.IEventFilter;
import io.mxngo.echo.subscriber.ISubscription;

/**
 * The {@code AbstractEventDispatcher} class is an abstract implementation of the {@code IEventDispatcher}
 * interface, providing a common method to process events and apply filters.
 *
 * @param <T> the type of event this dispatcher can handle
 */
public abstract class AbstractEventDispatcher<T> implements IEventDispatcher<T> {
    protected void processEvent(final T event, final ISubscription<T> subscription) {
        final EventCallback<T> callback = subscription.eventCallback();
        final IEventFilter[] filters = callback.getFilters();

        for (final IEventFilter filter : filters) {
            if (!filter.filter(event)) {
                return;
            }
        }

        callback.getCallback().accept(event);
    }
}
