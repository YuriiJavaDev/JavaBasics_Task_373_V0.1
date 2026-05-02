## Default methods in interfaces.

### 1. Introduction

Once upon a time (before Java 8), interfaces were very strict: they could only declare abstract methods (without implementation) and constants (**public static final**). This was convenient until a major problem arose: **library evolution**.

**Imagine the situation**

You've developed a popular library that has the following interface:

```java
public interface Movable {
    void move(int x, int y);
}
```

Thousands of programmers around the world write their own classes implementing this interface. After a couple of years, you realize that everyone is missing a **reset()** method, which returns the object to its original position. You add the following to the interface:

```java
public interface Movable {
    void move(int x, int y);
    void reset();
}
```

And then the apocalypse begins: all projects using your interface stop compiling! Now they're required to implement a new method, and no one knew about it. Migration becomes a pain.

**Default Methods — the Solution!**

Java 8 introduced **default methods**: now you can add a method with an implementation directly to an interface! All old classes automatically receive a default implementation, and their code doesn't break. And if you want, you can override the method yourself.

### 2. Default Method Syntax

A default method is a regular method with an implementation within an interface, marked with the **default** keyword.

```java
public interface Movable {
    void move(int x, int y);
    
    default void reset() {
        // Typical implementation: return to the origin
        move(0, 0);
    }
}
```

**Explanation:**

- All interface methods are **public** and **abstract** by default, but default methods are not abstract, but have a body.
- The **default** keyword is always written before the method's return type.

#### How does this look in a class?

```java
public class Robot implements Movable {
    private int x, y;
    
    @Override
    public void move(int x, int y) {
        this.x = x;
        this.y = y;
        System.out.println("Robot moved to (" + x + ", " + y + ")");
    }
    
    // Reset() is optional—the default version will work!
}
```

Now, if we call **reset()** on the **Robot** object, the implementation from the **Movable** interface will be called:

```java
public class Main {
    public static void main(String[] args) {
        Movable robot = new Robot();
        robot.move(10, 20); // Robot moved to (10, 20)
        robot.reset(); // Robot moved to (0, 0)
    }
}
```

### 3. Default Methods in the Standard Library

Default methods were added for a reason, but to allow for the development of the vast standard Java interfaces without breaking old code.

**Example: List interface (Java 8+)**

In Java 8, methods with implementations were added to the **List** interface, for example, **forEach, **replaceAll, **sort**:

```java
default void forEach(Consumer<Entity> action) {
    for (Entity e : this) {
        action.accept(e);
    }
}
```

If you implement your own list and don't override **forEach**, it will still work thanks to the default method.

You can learn more about generic types (**Consumer<Entity>**) in Level 26 **:P**

### 4. Why are default methods needed?

- **API evolution without breaking code:** you can add new methods to the interface without having to implement them in all existing classes. - **Universal Behavior Patterns:** You can declare default behavior so that classes can use or override it.
- **Reduced Duplication:** If behavior is the same for most implementations, you don't need to copy code into every class.

**Analogy**

Imagine you have an apartment lease agreement (an interface). It used to say, "The tenant is obligated to pay for water." Then they added, "The tenant is obligated to pay for electricity." Without default methods, you'd have to rewrite all the leases with all the tenants! But with default methods, you simply add a clause, and if someone needs it, they can agree on their own.

### 5. Limitations and Features of Default Methods

#### Default methods cannot override methods of the Object class

You cannot declare a default method in an interface with a signature that matches **equals, **hashCode**, or **toString** from the **Object** class. This prevents confusion, as every object in Java already has these methods.

```java
// Compilation error!
interface Broken {
    default boolean equals(Object obj){
        return false;
    }
}
```

#### Default Method Conflicts

What if a class implements two interfaces, each of which has a default method with the same signature? The Java compiler will honestly say, "Decide for yourself, I don't know what to do!"

```java
interface A {
    default void hello(){
        System.out.println("Hello from A");
    }
}

interface B {
default void hello(){
        System.out.println("Hello from B");
    }
}

class C implements A, B {
    // Required conflict resolution:
    @Override
    public void hello() {
        // You can choose whose method to call, or implement your own
        A.super.hello(); // or B.super.hello();
    }
}
```

If you don't implement **hello()** in class **C**, you will get a compilation error.

#### Default methods can call other interface methods

A default method can call other interface methods, even abstract ones. The main thing is that the implementation is in the class.

```java
interface Printer {
    void print(String text);
    
    default void printTwice(String text) {
        print(text);
        print(text);
    }
}
```

### 6. Example: Developing an Application with a Default Method

Let's look at an example of using default methods in the **Movable** interface:

```java
public interface Movable {
    void move(int x, int y);
    
    default void reset() {
        move(0, 0);
    }
}
```

And there's a **Robot** class that implements this interface:

```java
public class Robot implements Movable {
    private int x = 5;
    private int y = 7;
    
    @Override
    public void move(int x, int y) {
        this.x = x;
        this.y = y;
        System.out.println("The robot has moved to (" + x + ", " + y + ")");
    }
    
    // We don't implement reset() — we'll use the default method!
}
```

Now let's try calling both methods:

```java
public class Main {
    public static void main(String[] args) {
        Movable robot = new Robot();
        robot.move(10, 20); // The robot has moved to (10, 20)
        robot.reset(); // The robot has moved to (0, 0)
    }
}
```

If we want **Robot** to reset in a special way, simply override reset() in the class:

```java
@Override
public void reset() {
    System.out.println("The robot is turning off and returning to the base!");
    move(0, 0);
}
```

### 7. Default Methods and Multiple Interface Implementations

Default methods are especially useful when a class implements multiple interfaces. However, there is a caveat: if both interfaces have a default method with the same signature, the compiler will require explicit conflict resolution.

#### Conflict Example

```java
interface A {
    default void show() { System.out.println("A"); }
}
interface B {
    default void show() { System.out.println("B"); }
}

class C implements A, B {
    @Override
    public void show() {
        // Explicitly choose whose default method to use
        A.super.show(); // or B.super.show();
    }
}
```

### 8. Diagram: How a Default Method Call Works

```
+-------------------+
|     Movable       |
|-------------------|
| +move(int, int)   | <- abstract method
| +reset()          | <- default method
+-------------------+
        ^
        |
+-------------------+
|     Robot         |
|-------------------|
| +move(int, int)   | <- implements
|                   | (reset() does not implement)
+-------------------+
        |
    Calling reset()
        |
    The implementation
    from the Movable interface is used
```

*Calling a Default Method: Default Implementation from an Interface*

### 9. Common Mistakes When Working with Default Methods

**Mistake №1: Trying to create a default method without an implementation.**

A default method must have a body! If you write **default void foo();**, the compiler will immediately say, "Did you forget the curly braces?"

**Mistake №2: Conflicting default methods from different interfaces.**

If a class implements two interfaces with the same default method, you must explicitly resolve the conflict—otherwise, the compiler won't let you compile the code.

**Mistake №3: Attempting to declare a default method with a method signature from Object.**

You can't create an **equals, **hashCode, **toString** default method in an interface—only abstract methods with those names.

**Mistake №4: Forgetting that default methods aren't "magic," but simply a convenience.**

Default methods don't negate the principle that an interface is a contract. If the default behavior is not suitable, always override the default method in the class.
