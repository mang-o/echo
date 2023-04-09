package io.mxngo.echo.dispatcher.impl;

import io.mxngo.echo.dispatcher.AbstractEventDispatcher;
import io.mxngo.echo.subscriber.ISubscription;

import java.util.List;

/**
 * The {@code SynchronousEventDispatcher} class is a concrete implementation of the
 * {@code AbstractEventDispatcher} class, providing synchronous dispatching of events.
 *
 * @param <T> the type of event this dispatcher can handle
 */
public final class SynchronousEventDispatcher<T> extends AbstractEventDispatcher<T> {
    @Override
    public void dispatch(final T event, final List<ISubscription<T>> sortedSubscriptions) {
        for (final ISubscription<T> subscription : sortedSubscriptions) {
            processEvent(event, subscription);
        }
    }
}
