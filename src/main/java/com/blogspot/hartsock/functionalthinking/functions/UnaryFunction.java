package com.blogspot.hartsock.functionalthinking.functions;

/**
 *
 * @author Muhammad Ashraf
 * @since 2/7/12
 */
public interface UnaryFunction<A,B> {

    /**
     * Lambda of kind A -> B
     * @param input  input
     * @return  result of lambda operation
     */
    B apply(final A input);
}
