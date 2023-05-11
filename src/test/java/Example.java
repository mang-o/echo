import io.mxngo.echo.*;
import io.mxngo.echo.core.*;
import io.mxngo.echo.filter.*;
import io.mxngo.echo.subscriber.pool.*;

public final class Example {
    public static void main(final String[] args) {
        final IEventBus<Object> eventBus = new StandardEventBus.Builder<>()
                .withSubscriptionPool(new CachedSubscriptionPool<>())
                .build();

        final Subscriber subscriber = new Subscriber();
        eventBus.subscribe(subscriber);

        eventBus.emit(new Event());

        eventBus.unsubscribe(subscriber);
    }

    public static final class Subscriber {
        @Callback
        private final EventCallback<Event> onMessage = new EventCallback<>(event -> {
            System.out.println("Penis!");
        }, Event.class);

        @Callback(priority = 2)
        private final EventCallback<PenisEvent> onPenis = new EventCallback<>(event -> {
            System.out.println(event.ok);
        }, PenisEvent.class);
     }

    public static class Event {
        public int test = 0;
    }

    public static class PenisEvent extends Event {
        public double ok = 2.0;
    }
}
