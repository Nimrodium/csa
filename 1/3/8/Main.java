// Main.java
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import src.Context;
import src.Node;
import src.Node.NodeBuilder;
import src.Node.Path;

/*
    -- Documentation --
        I am well aware this is excessively overengineered but I wanted to do this for fun,
    as a year ago I designed something like this in python a year ago.  however it was very
    bad because it was designed with a lot of mutability and i was just starting to learn
    how to use OOP, and didn´t even know about FP.

    This program defines an execution engine for a declarative and immutable Node Tree where each Node represents a single scene,
    every node is linked together via a Path object, which represents the transition to another node.
    every node has two defined hook functions, a requiresHook, which is evaluated in execution of the parent to determine if a path is unlocked
    and an onEnter hook which is evaluated when the node is executed,
    the program introduces mutability with the Context container, which exposes simple key-value pairs to the tree as it is being evaluated,
    hooks are to be used to access and modify this state in order to conditionally lock nodes. Context is threaded through all evaluation functions.

    in functional programming terms, the evaluation of the node tree is simply applying the recursive Node.execute() function to a Context monad,
    with the resulting Context being the aggegation of all applications of every scene chosen by the player. this allows for easy serialization of the game state
    if desired, and makes adding new inputs into the node function trivial.

    By using an immutable and declarative format it makes defining the actual story much easier, as the NodeBuilder acts like it´s own mini DSL.
    despite being so overengineered, it is actually quite a small program, at only ~164 lines of code excluding the game definition.
*/
class Main {

    public static void main(String[] args) {
        new NodeBuilder("Hi i am the root node")
            .branch(
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

        new NodeBuilder("Ah, youre finally awake.")
    }
}
