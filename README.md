# Smart Device Remote: Default Methods (JavaBasics_Task_373_V0.1)

## 📖 Description
In hardware and software interface design, certain operations are universal across all implementations. This project demonstrates the use of **Interface Default Methods** to provide a standard "factory reset" functionality. By defining `reset()` within the `Counter` interface, we ensure that every implementing device possesses this capability without requiring manual re-implementation. This promotes code reuse and ensures a consistent behavior across different device models.

## 📋 Requirements Compliance
- **Universal Blueprint**: Defined the `Counter` interface with both abstract and default methods.
- **Standardized Logic**: Implemented `reset()` as a default method to provide a "Counter reset" message.
- **Specific Implementation**: Developed the `MyCounter` class to handle the unique `increment()` logic.
- **Execution Flow**: Demonstrated the seamless transition between custom and inherited behaviors in the main application.

## 🚀 Architectural Stack
- Java 8+ (Interfaces, Default Methods, Polymorphism)

## 🏗️ Implementation Details
- **Counter**: The interface acting as the smart device's functional blueprint.
- **MyCounter**: A concrete implementation of the counting logic.
- **CounterLauncherApp**: The entry point for testing device remote operations.

## 📋 Expected result
```text
Counter incremented
Counter reset
```

## 💻 Code Example

Project Structure:

    JavaBasics_Task_373/
    ├── src/
    │   └── com/yurii/pavlenko/
    │                 ├── app/
    │                 │   └── CounterLauncherApp.java
    │                 └── device/
    │                     ├── contracts/
    │                     │   └── Counter.java
    │                     └── modules/
    │                         └── MyCounter.java
    ├── LICENSE
    ├── TASK.md
    ├── THEORY.md
    └── README.md

Code
```java
package com.yurii.pavlenko.app;

import com.yurii.pavlenko.device.modules.MyCounter;

public class CounterLauncherApp {

    public static void main(String[] args) {
        MyCounter device = new MyCounter();

        device.increment();
        device.reset();
    }
}
```
```java
package com.yurii.pavlenko.device.contracts;

public interface Counter {
    void increment();
    default void reset() {
        System.out.println("Counter reset");
    }
}
```
```java
package com.yurii.pavlenko.device.modules;

import com.yurii.pavlenko.device.contracts.Counter;

public class MyCounter implements Counter {

    @Override
    public void increment() {
        System.out.println("Counter incremented");
    }
}
```

## ⚖️ License
This project is licensed under the **MIT License**.

Copyright (c) 2026 Yurii Pavlenko

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files...

License: [MIT](LICENSE)
