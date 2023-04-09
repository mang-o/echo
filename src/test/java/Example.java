import io.mxngo.echo.*;
import io.mxngo.echo.core.*;
import io.mxngo.echo.filter.*;
import io.mxngo.echo.subscriber.pool.*;

public class Example {
    public static void main(final String[] args) {
        final IEventBus<String> eventBus = new StandardEventBus.Builder<String>()
                .withSubscriptionPool(new CachedSubscriptionPool<>())
                .build();

        final Subscriber subscriber = new Subscriber();
        eventBus.subscribe(subscriber);

        eventBus.emit("Hello, EventBus!");

        eventBus.unsubscribe(subscriber);
    }

    public static final class Subscriber {
        @Callback
        private final EventCallback<String> onMessage = new EventCallback<>(this::handleMessage);

        private void handleMessage(final String message) {
            System.out.println("Received: " + message);
        }
    }
}
