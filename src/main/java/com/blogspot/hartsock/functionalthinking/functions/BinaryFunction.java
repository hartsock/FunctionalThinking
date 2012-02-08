package com.blogspot.hartsock.functionalthinking.functions;

/**
 * @author Muhammad Ashraf
 * @since 2/7/12
 */
public interface BinaryFunction<A,B> {

    /**
     * Lamda of kind (A,B) -> B
     * @param seed  seed value
     * @param input input
     * @return result of Binary operation
     */
    B apply(final B seed,final A input);
}
