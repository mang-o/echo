package io.mxngo.echo.filter;

import java.util.Arrays;
import java.util.List;

/**
 * The {@code CompositeFilter} class is an implementation of the {@code IEventFilter} interface,
 * allowing multiple filters to be combined using a specified {@code Operator}.
 */
public final class CompositeFilter implements IEventFilter {
    private final Operator operator;
    private final List<IEventFilter> filters;

    public CompositeFilter(final Operator operator, final IEventFilter... filters) {
        this.operator = operator;
        this.filters = Arrays.asList(filters);
    }

    @Override
    public boolean filter(final Object object) {
        switch (operator) {
            case AND:
                return filters.stream().allMatch(filter -> filter.filter(object));
            case OR:
                return filters.stream().anyMatch(filter -> filter.filter(object));
            case NOT:
                if (filters.size() != 1) {
                    throw new IllegalArgumentException("NOT operator should only have one filter");
                }
                return !filters.get(0).filter(object);
            default:
                throw new IllegalStateException("Unexpected operator: " + operator);
        }
    }

    public enum Operator {
        AND,
        OR,
        NOT
    }
}
