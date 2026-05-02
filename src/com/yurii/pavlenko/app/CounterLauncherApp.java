package com.yurii.pavlenko.app;

import com.yurii.pavlenko.device.modules.MyCounter;

/**
 * Main application for testing the smart device remote control.
 */
public class CounterLauncherApp {

    public static void main(String[] args) {
        MyCounter device = new MyCounter();

        // Calling a custom implementation
        device.increment();

        // Calling a inherited default behavior
        device.reset();
    }
}