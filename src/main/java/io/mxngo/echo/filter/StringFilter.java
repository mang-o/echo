package io.mxngo.echo.filter;

import java.util.function.Function;

/**
 * The {@code StringFilter} class is an implementation of the {@code IEventFilter} interface,
 * filtering events based on whether a specific string is present in the extracted value.
 *
 * @param <T> the type of event this filter can handle
 */
public final class StringFilter<T> implements IEventFilter {
    private final String searchString;
    private final Function<T, String> valueExtractor;

    public StringFilter(final String searchString, final Function<T, String> valueExtractor) {
        this.searchString = searchString;
        this.valueExtractor = valueExtractor;
    }

    @Override
    public boolean filter(final Object event) {
        final T typedEvent = (T) event;
        final String value = valueExtractor.apply(typedEvent);
        return value.contains(searchString);
    }
}
