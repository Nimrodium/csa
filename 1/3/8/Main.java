// Main.java
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import src.Context;
import src.Node;
import src.Node.NodeBuilder;
import src.Node.Path;

class Main {

    public static void main(String[] args) {
        new NodeBuilder("Hi i am the root node")
            .children(
                new Path(
                    "this is a terminal selection",
                    new NodeBuilder("i am terminal").build()
                ),
                new Path(
                    "this is a locked node",
                    new NodeBuilder("i am locked")
                        .requires(ctx -> ctx.has("impossible attribute"))
                        .build()
                )
            )
            .build()
            .init();
    }
}
