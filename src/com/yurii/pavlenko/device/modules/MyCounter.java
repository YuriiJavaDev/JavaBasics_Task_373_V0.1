package com.yurii.pavlenko.device.modules;

import com.yurii.pavlenko.device.contracts.Counter;

/**
 * A concrete implementation of the smart counter device.
 */
public class MyCounter implements Counter {

    @Override
    public void increment() {
        System.out.println("Counter incremented");
    }
}