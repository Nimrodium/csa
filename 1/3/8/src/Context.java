// src/Context.java
package src;
import java.util.Random;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

// Context represents the game state and any inputs into nodes, currently just the scanner and attribute store.
// functionally, each Node is a pure function, which is applied to Context, and the final context is the application of every Node.
public class Context {
    Random rand;
    Scanner sc;
    HashMap<String, String> store;

    public Context() {
        this.rand = new Random();
        this.store = new HashMap<String, String>();
        this.sc = new Scanner(System.in);
    }

    public boolean has(String attr) {
        return this.store.containsKey(attr);
    }

    public Optional<String> get(String attr) {
        if (this.store.containsKey(attr)) {
            // (this.store.get(attr))
            return Optional.of(this.store.get(attr));
        } else return Optional.empty();
    }

    public void give(String attr, String value) {
        this.store.put(attr, value);
    }
    public void give(String attr) {
        this.store.put(attr,"");
    }

    public boolean isValue(String key, String value){
        var attr = this.get(key);
        return attr.map(v -> v.equals(value)).orElse(false);
    }

    public void take(String attr) {
        this.store.remove(attr);
    }

    // set of nPaths = [1..=nPaths]
    int querySelection(List<Boolean> allowed) {
        if (allowed.stream().filter(b -> b).count() == 1){
            this.anyKey();
            int i = 0;
            // gross but no zip impl so i cannot simply return stream().enumerate().findFirst((_,true)).0;
            for (boolean bool:allowed){
                if (bool){
                    return i;
                }else i++;
            }
        }   
        System.out.print("Input a number");
       
        while (true) {
            try {System.out.print(":");
            System.out.flush();
            int response = this.sc.nextInt();
            if (0 < response && response <= allowed.size()) {
                var path = allowed.get(response - 1);
                if (path) {
                    return response - 1;
                } else System.out.printf("selection %d is locked\n", response);
            }}catch (InputMismatchException e){
                System.err.println("must be a number");
            }
        }
        
    }

    void anyKey() {
        sc.nextLine();
    }
}
