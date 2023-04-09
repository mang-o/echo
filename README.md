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

```java
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
```

<h3>Creating an EventBus</h3>

To create an EventBus, use the StandardEventBus.Builder class:

```java
    final IEventBus<String> eventBus = new StandardEventBus.Builder<String>()
        .withSubscriptionPool(new CachedSubscriptionPool<>())
        .build();
```

<h3>Subscribing to Events</h3>

To subscribe to events, create a subscriber class with a field annotated with @Callback:

```java
public final class Subscriber {
    @Callback
        private final EventCallback<String> onMessage = new EventCallback<>(this::handleMessage);

        private void handleMessage(final String message) {
            System.out.println("Received: " + message);
        }
    }
}
```

Then, subscribe the instance of the class to the EventBus:

```java
final Subscriber subscriber = new Subscriber();
eventBus.subscribe(subscriber);
```

<h3>Emitting Events</h3>

To emit an event, call the emit method on the EventBus instance:

```java
eventBus.emit("Hello, EventBus!");
```

<h3>Unsubscribing from Events</h3>

To unsubscribe a subscriber from the EventBus, call the unsubscribe method:

```java
eventBus.unsubscribe(subscriber);
```

<h3>Event Filtering</h3>

You can use event filters to selectively process events. For example, you can use the StringFilter to filter events based on a string:

```java
public final class Subscriber {
    @Callback
    private final EventCallback<String> onMessage = new EventCallback<>(this::handleMessage,
        new StringFilter<>("EventBus", String::toString));

    private void handleMessage(final String message) {
        System.out.println("Received: " + message);
    }
}
```

In this example, the handleMessage method will only be called if the emitted event contains the string "EventBus".