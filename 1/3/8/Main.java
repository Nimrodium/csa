// Main.java
import java.util.Optional;
import src.Node;
import src.Node.NodeBuilder;
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

    in functional programming terms, the evaluation of the node tree is simply applying the recursive Node.execute() function to Context,
    with the resulting Context being the aggegation of all applications of every scene chosen by the player. this allows for easy serialization of the game state
    if desired, and makes adding new inputs into the node function trivial.

    By using an immutable and declarative format it makes defining the actual story much easier, as the NodeBuilder acts like it´s own mini DSL.
    despite being so overengineered, it is actually quite a small program, at only ~164 lines of code excluding the game definition.
*/
// after disagree track, user has to adventure through a small minigame to find a key, 
// then kill ME. gabreil ultrakill style, two endings, death and win.
// after agree track, user has to escape me ranting about linux. if you do not have the linux attribute, 
// if you fail to escape you will rot forever, if you escape, you win. if you also use linux, you sit there forever gleefully.
class Main {

    public static void main(String[] args) {
    NodeBuilder linuxRant = Node.series("anyways, i love linux, its so cool!",
    "its awesome to just be able to control your own computer just the way you want it.", 
    "and especially the reproducability of nixos.", 
    "nixos is just so awesome."
    ).after(
        Node.node().branch(
            Node.node().requires(ctx -> ctx.isValue("OS","Linux")).next(
                Node.node("you said you use Linux yeah? what distro do you use?")
            ),
            Node.node().requires(ctx -> ctx.isValue("OS","Windows")).next(
                Node.series("you filthy windows users are honestly VILE").after(escape)
            ),
            Node.node().requires(ctx -> ctx.isValue("OS","macOS")).next(
                Node.series("yknow macos is like linux.","its a unix operating system based off of freebsd","and freebsd is just like linux").after(escape)
            )
    ));
    var escape = Node.node("> you have to escape, you cant bare listen to this guy ramble for any longer\nhow do you escape?",
        Node.node("run. run as fast as you can",Node.node("you run for a while, you can hear him yell \"HEY! YOU SHOULD REALLY CONSIDER LINUX\"").next(),
        Node.node("maybe you can trick him",
            
        ),  
    );
    var opening = Node.node("you come across an opening, to the left is a locked door, to the right is ")
    var agree = Node.series("thats awesome!",
    "what languages do you prefer?",
        Node.path("i prefer languages like C, where im very close to the hardware",
            Node.node("ah interesting!, do you do your development on Windows or Linux?",
                Node.path("i develop on Windows",
                 Node.node("on that is quite odd").onEnter(ctx -> ctx.give("OS",Optional.of("Windows"))),
                ),
                Node.path("i develop on Linux",
                    Node.node("thats what i expected").onEnter(ctx -> ctx.give("OS",Optional.of("Linux"))),
                ),
                Node.path("i develop on macOS",Node.node("ew...").onEnter(ctx -> ctx.give("OS",Optional.of("macOS"))).next(linuxRant))
            ),
        ),
        Node.path("i prefer very high level dynamically typed languages like Python",
            Node.series("eh...",
            "python is very powerful but you can shoot yourself in the foot unless you really enforce static typing",
            "and that kind of defeats the point of python eh?"
            ).after(

            ),
        ),
        Node.path("i prefer strict languages that ensure no errors ever get through, like Rust.",
            Node.series("oh me too!", "Rust really is one of the best languages",
            "but the borrow checker can get very annoying when working with explicit lifetimes.").after(

            ),
        ),
        Node.path("i prefer quirky languages that aren't very practical but cool to think about, like haskell or elixir",
            Node.series("yup, i understand that lmao.",
            "im trying to learn haskell and its very fun",
            "but the Monad syntax is very confusing","whats a =>>???").after(

            ),
        ),
    );

    var disagree = Node.series("wow",
     "wow...",
     "WOW...",
     "YOU",
     "You are what is wrong with programming",
     "how... DARE you."
    ).after(Node.node(
        "so tell me, GigaOOPLover? what do you love SOO much about java",
        Node.path("i love the verbosity",Node.series(
                "why would you even think that.",
                "the verbosity present in java is incredibly awful??",
                "all modern languages arent THIS verbose"
            ).after()
        ),
        Node.path("i love the concept of setters/getters"),
        Node.path("i love how tuples aren't included.")
    ));

    Node.series("i hate java...","its level of gigaOOP is so annoying")
        .after(Node.node("do you agree", 
        Node.node("yes", agree), 
        Node.path("no, i love gigaOOP",disagree)

        )
    );
    }
}
