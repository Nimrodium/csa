package src;

/*
    Shared class when i can so that i can use tuples and zip in projects, else i will go insane.
*/
import java.util.ArrayList;
import java.util.stream.Stream;

class Utils {

    static record Tuple<A, B>(A a, B b) {}

    static record Triple<A, B, C>(A a, B b, C c) {}

    public static <A, B> Stream<Tuple<A, B>> zip(Stream<A> a, Stream<B> b) {
        // Iterable<A> a1 = () -> a.iterator();
        var bIterator = b.iterator();
        int impureIndex = 0;
        // B y = bIterator.hasNext() ? bIterator.next() :
        // a.map(x -> {B y = bIterator.hasNext(); return new Tuple(x,);});
        var result = new ArrayList<Tuple<A, B>>();
        for (A ax : (Iterable<A>) a::iterator) {
            if (bIterator.hasNext()) {
                result.add(new Tuple<A, B>(ax, bIterator.next()));
            } else break;
        }
        return result.stream();
    }
}
