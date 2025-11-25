// src/Context.java
package src;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

// Context represents the game state and any inputs into nodes, currently just the scanner and attribute store.
// functionally, each Node is a pure function, which is applied to Context, and the final context is the application of every Node.
public class Context {

    Scanner sc;
    HashMap<String, Integer> store;

    public Context() {
        this.store = new HashMap<String, Integer>();
        this.sc = new Scanner(System.in);
    }

    public boolean has(String attr) {
        return this.store.containsKey(attr);
    }

    public Optional<Integer> get(String attr) {
        if (this.store.containsKey(attr)) {
            // (this.store.get(attr))
            return Optional.of(this.store.get(attr));
        } else return Optional.empty();
    }

    public void give(String attr, Optional<Integer> value) {
        this.store.put(attr, value.isPresent() ? value.get() : 0);
    }

    public void take(String attr) {
        this.store.remove(attr);
    }

    // set of nPaths = [1..=nPaths]
    int querySelection(List<Boolean> allowed) {
        System.out.print("Input a number");
        while (true) {
            System.out.print(":");
            System.out.flush();
            int response = this.sc.nextInt();
            if (0 < response) {
                var path = allowed.get(response - 1);
                if (path) {
                    return response - 1;
                } else System.out.printf("selection %d is locked\n", response);
            }
        }
    }

    void anyKey() {
        sc.next();
    }
}
