package com.example.saebackend.base;

public interface Mapper<I, O> {
    O mapTo(I input);
    I mapFrom(O output);
}
