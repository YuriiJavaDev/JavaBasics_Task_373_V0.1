### Imagine you're programming a remote control for a new smart device. The device needs to be able to perform a basic increment operation and have a standard factory reset function. The reset function is common to many devices, so its logic should be defined directly in the device's "blueprint" (interface).

#### First, define a Counter interface that includes an abstract increment() method and a default reset() method. The reset() method should simply output "Counter reset" when called. Then, create a MyCounter class, which will be the concrete implementation of your Counter. The increment() method in MyCounter should output "Counter incremented." In the main part of the program, create an instance of MyCounter and call increment() and then reset() to demonstrate how the device performs its functions.

```java
public class CounterLauncherApp {
    public static void main(String[] args) {
        // Create the device and demonstrate the methods
        MyCounter device = new MyCounter();
        device.increment(); // should print "Counter incremented"
        device.reset(); // should print "Counter reset"
    }
}
```
