import java.util.HashMap;
import java.util.Optional;

public class Context {

    HashMap<String, Integer> store = new HashMap<String, Integer>();

    boolean has(String attr) {
        return this.store.containsKey(attr);
    }

    Optional<Integer> get(String attr) {
        if (this.store.containsKey(attr)) {
            // (this.store.get(attr))
            return Optional.of(this.store.get(attr));
        } else return Optional.empty();
    }

    void give(String attr, Optional<Integer> value) {
        this.store.put(attr, value.isPresent() ? value.get() : 0);
    }

    void take(String attr) {
        this.store.remove(attr);
    }
}
