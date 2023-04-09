package io.mxngo.echo.filter;

/**
 * The {@code IEventFilter} interface defines a method for filtering events based on custom
 * criteria.
 */
public interface IEventFilter {
    boolean filter(final Object object);
}
