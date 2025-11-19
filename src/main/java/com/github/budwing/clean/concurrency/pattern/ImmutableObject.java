package com.github.budwing.clean.concurrency.pattern;

/**
 * Immutable Object is a concurrency pattern where an object's state cannot be modified after it has been created.
 * This immutability ensures that the object can be safely shared between multiple threads without the need for synchronization,
 * as there are no changes to its state that could lead to inconsistent views or race conditions.
 * Immutable objects are typically created through constructors or factory methods, and all fields are declared as final.
 * Any modification to an immutable object results in the creation of a new instance with the desired changes.
 * This pattern is particularly useful in multi-threaded environments, as it simplifies the design and reasoning about concurrent code.
 * Common examples of immutable objects include strings and wrapper classes in many programming languages. 
 * @see <a href="https://en.wikipedia.org/wiki/Immutable_object">Immutable object - Wikipedia</a>
 */
public class ImmutableObject {
    private final int value;
    public ImmutableObject(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
