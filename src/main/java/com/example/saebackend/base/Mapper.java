package com.example.saebackend.base;

/**
 * Interface for mapping between two types.
 *
 * @param <I> The input type.
 * @param <O> The output type.
 */
public interface Mapper<I, O> {
    O mapTo(I input);
    I mapFrom(O output);
}
