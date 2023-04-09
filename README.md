# echo

Echo is a powerful, flexible, and easy-to-use Java event bus library that allows you to manage event-driven communication between components in a clean and decoupled manner.

<h2> Features </h2>

- Asynchronous and synchronous event dispatching
- Prioritization of event subscribers
- Built-in event filtering mechanism
- Thread-safe event subscription and unsubscription
- Support for custom subscription pools
- Simple annotation-based event subscription

<h2> Getting Started </h2>

- To start using Echo, add the library to your project's dependencies. You will have to build the artifact yourself for now.

<h3> Code Example </h3>

Here's a basic example of how to use Echo:

<pre>
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
</pre>

<h3>Creating an EventBus</h3>

To create an EventBus, use the StandardEventBus.Builder class:

<pre>
    final IEventBus<String> eventBus = new StandardEventBus.Builder<String>()
        .withSubscriptionPool(new CachedSubscriptionPool<>())
        .build();
        </pre>

<h3>Subscribing to Events</h3>

To subscribe to events, create a subscriber class with a field annotated with @Callback:

<pre>
public final class Subscriber {
    @Callback
        private final EventCallback<String> onMessage = new EventCallback<>(this::handleMessage);

        private void handleMessage(final String message) {
            System.out.println("Received: " + message);
        }
    }
}
</pre>

Then, subscribe the instance of the class to the EventBus:

<pre>
final Subscriber subscriber = new Subscriber();
eventBus.subscribe(subscriber);
</pre>

<h3>Emitting Events</h3>

To emit an event, call the emit method on the EventBus instance:

<pre>
eventBus.emit("Hello, EventBus!");
</pre>

<h3>Unsubscribing from Events</h3>

To unsubscribe a subscriber from the EventBus, call the unsubscribe method:

<pre>
eventBus.unsubscribe(subscriber);
</pre>

<h3>Event Filtering</h3>

You can use event filters to selectively process events. For example, you can use the StringFilter to filter events based on a string:

<pre>
public final class Subscriber {
    @Callback
    private final EventCallback<String> onMessage = new EventCallback<>(this::handleMessage,
        new StringFilter<>("EventBus", String::toString));

    private void handleMessage(final String message) {
        System.out.println("Received: " + message);
    }
}
</pre>

In this example, the handleMessage method will only be called if the emitted event contains the string "EventBus".