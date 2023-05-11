package io.mxngo.echo.core;

import io.mxngo.echo.IEventBus;
import io.mxngo.echo.dispatcher.DispatchStrategy;
import io.mxngo.echo.dispatcher.EventProcessor;
import io.mxngo.echo.subscriber.pool.ISubscriptionPool;

import java.util.Optional;
import java.util.concurrent.Executor;

/**
 * The {@code StandardEventBus} class is a concrete implementation of the {@code IEventBus} interface,
 * providing methods to subscribe, unsubscribe, and emit events.
 *
 * @param <T> the type of event this event bus can handle
 */
public final class StandardEventBus<T> implements IEventBus<T> {
    private final ISubscriptionPool<T> subscriptionPool;
    private final EventProcessor<T> eventProcessor;

    private StandardEventBus(final Builder<T> builder) {
        this.subscriptionPool = builder.subscriptionPool;
        final DispatchStrategy<T> dispatchStrategy = new DispatchStrategy<>(builder.executor);
        this.eventProcessor = new EventProcessor<>(subscriptionPool, dispatchStrategy);
    }

    @Override
    public void subscribe(final Object object) {
        this.subscriptionPool.register(object);
    }

    @Override
    public void unsubscribe(final Object object) {
        this.subscriptionPool.unregister(object);
    }

    @Override
    public void emit(final T event) {
        this.eventProcessor.processEvent(event);
    }

    /**
     * The {@code Builder} class is used to build instances of {@code StandardEventBus} with a
     * specific configuration, such as a custom {@code Executor} and {@code ISubscriptionPool}.
     *
     * @param <T> the type of event the event bus can handle
     */
    public static final class Builder<T> {
        private Executor executor;
        private ISubscriptionPool<T> subscriptionPool;

        public Builder<T> withExecutor(final Executor executor) {
            this.executor = executor;
            return this;
        }

        public Builder<T> withSubscriptionPool(final ISubscriptionPool<T> subscriptionPool) {
            this.subscriptionPool = subscriptionPool;
            return this;
        }

        public StandardEventBus<T> build() {
            return new StandardEventBus<>(this);
        }
    }
}
