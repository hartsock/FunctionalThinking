package com.blogspot.hartsock.functionalthinking.functions;

/**
 * @author Muhammad Ashraf
 * @since 2/7/12
 */
public interface Function<T> {

    /**
     * lamda of type A -> Void
     * @param input input
     */
    void apply(final T input);
}
