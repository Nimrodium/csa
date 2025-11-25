package src;

import java.util.ArrayList;
import java.util.stream.Stream;

class ConditionSystem {

    interface Condition {
        boolean evaluate(Context state);
    }

    // private static<T,A> T fold(Stream<A> as,FunctionalInterface fn){

    // }
    // private boolean foldBooleanList()
    record And(ArrayList<String> operands) implements Condition {
        public boolean evaluate(Context state) {
            return operands
                .stream()
                .map(a -> state.has(a))
                .reduce(false, (a, b) -> a && b);
        }
    }

    record Or(ArrayList<String> operands) implements Condition {
        public boolean evaluate(Context state) {
            return operands
                .stream()
                .map(a -> state.has(a))
                .reduce(false, (a, b) -> a || b);
        }
    }

    record Not(String operand) implements Condition {
        public boolean evaluate(Context state) {
            return !state.has(operand);
        }
    }
}
