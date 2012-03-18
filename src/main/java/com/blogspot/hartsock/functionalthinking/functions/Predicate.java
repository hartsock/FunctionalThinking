package com.blogspot.hartsock.functionalthinking.functions;

/**
 * @author Muhammad Ashraf
 * @since 2/7/12
 */
public interface Predicate<T> {
    /**
     * lamda of kind A -> boolean
     * @param t type of input
     * @return true if T satisfies the predicate, false otherwise
     */
    boolean apply(T t);
}
