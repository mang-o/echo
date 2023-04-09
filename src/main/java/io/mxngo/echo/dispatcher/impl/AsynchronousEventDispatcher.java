package io.mxngo.echo.dispatcher.impl;

import io.mxngo.echo.dispatcher.AbstractEventDispatcher;
import io.mxngo.echo.subscriber.ISubscription;

import java.util.List;
import java.util.concurrent.Executor;

/**
 * The {@code AsynchronousEventDispatcher} class is a concrete implementation of the
 * {@code AbstractEventDispatcher} class, providing asynchronous dispatching of events.
 *
 * @param <T> the type of event this dispatcher can handle
 */
public final class AsynchronousEventDispatcher<T> extends AbstractEventDispatcher<T> {
    private final Executor executor;

    public AsynchronousEventDispatcher(final Executor executor) {
        this.executor = executor;
    }

    @Override
    public void dispatch(final T event, final List<ISubscription<T>> sortedSubscriptions) {
        for (final ISubscription<T> subscription : sortedSubscriptions) {
            executor.execute(() -> processEvent(event, subscription));
        }
    }
}
