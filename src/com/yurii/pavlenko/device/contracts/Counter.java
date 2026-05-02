package com.yurii.pavlenko.device.contracts;

/**
 * Blueprint for a smart counting device.
 */
public interface Counter {

    /**
     * Abstract method to be implemented by specific counter types.
     */
    void increment();

    /**
     * Default method providing a standard reset operation.
     */
    default void reset() {
        System.out.println("Counter reset");
    }
}