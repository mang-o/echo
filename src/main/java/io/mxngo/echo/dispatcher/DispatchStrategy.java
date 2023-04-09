package io.mxngo.echo.dispatcher;

import io.mxngo.echo.dispatcher.impl.AsynchronousEventDispatcher;
import io.mxngo.echo.dispatcher.impl.SynchronousEventDispatcher;
import io.mxngo.echo.subscriber.ISubscription;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executor;

/**
 * The {@code DispatchStrategy} class determines the strategy to use when dispatching events,
 * selecting between {@code AsynchronousEventDispatcher} and {@code SynchronousEventDispatcher}.
 *
 * @param <T> the type of event this strategy can handle
 */
public final class DispatchStrategy<T> {
    private final IEventDispatcher<T> dispatcher;

    public DispatchStrategy(final Optional<Executor> executor) {
        this.dispatcher = executor.isPresent() ? new AsynchronousEventDispatcher<>(executor.get()) : new SynchronousEventDispatcher<>();
    }

    public void dispatch(final T event, final List<ISubscription<T>> sortedSubscriptions) {
        dispatcher.dispatch(event, sortedSubscriptions);
    }
}
