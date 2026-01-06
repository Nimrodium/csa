// Main.java
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

// java forward reference problem makes this terrible

// endings -- 
// get going
// stay with linux guy (if use linux)
// escape successfully
// fight and win
// fight and lose
class Main {
    static final NodeBuilder insideDoor = Node.node().requires(ctx -> ctx.has("key")).next(Node.node("the door leads to freedom!, you are free from the linux man"));
// so turns out java doesnt like supercyclic references. i spent too long TRYING to get this to work, only for it to stackoverflow, so i dont have time to reorganize, im keeping the static mess.
    static final NodeBuilder opening;
    static final NodeBuilder goBackOpening;
    static final NodeBuilder goInsideDoor;
    static final NodeBuilder fightLoop = Node.series("he elegantly shoots up and back down, hitting you to the ground once more.",
    "he leans in for another blow, you have the chance to react")
    .after(Node.node("what do you do",
        Node.node("punch","you punch him",new Node.Path("",
            Node.series("hes weak. punch him again",
                "again","AGAIN","AGAINNNN"
            ).after(Node.node("he finally dies. you have sucessfully escaped from him")))),
        new Node.Path("shoot",Node.node("you shoot him").requires(ctx -> ctx.has("gun"))).next(Node.node(
            "excellent shot, he drops dead. you have sucessfully escaped from him"
        ))
    ));
    // static final NodeBuilder check = Node.node(); 
    static final NodeBuilder fight;
    static {
            // static final NodeBuilder 
        fight = Node.series("you see the linux user approach you with murder in his eyes",
        "Turn Back Now.",
        "Lest you cross the will of Linux",
        "YOU INSIGNIFICANT THING",
        "I WILL TEAR YOU LIMB FROM LIMB",
        "YOUR KIND IS NOT WELCOME IN THE WALLS OF THIS PALACE"
        ).after(
                Node.node("he goes in for the first attack at lightning speed",
                    Node.node("parry his attack","you missed your parry, for you underestimated his speed.").next(fightLoop),
                    Node.node("dodge","you fail to dodge, for you underestimate his speed").next(fightLoop)
                )
            );
        //    .onEnter(ctx -> {ctx.give("hp","100");ctx.give("oppHp","100");}); // unfortunately unused
        
        goBackOpening = Node.node();
        goInsideDoor = Node.node();
        opening = Node.node(
        "you come across an opening, to the left is a door, to the right is a long hallway",
        Node.path(
            "check out the door",
            Node.node("the door appears to be locked").branch(
                new Node.Path("unlock with a key",Node.node().requires(ctx -> ctx.has("key")).next(goInsideDoor)),
                new Node.Path("break in",Node.node().requires(ctx -> ctx.has("axe")).next(goInsideDoor)),
                new Node.Path("go back",goBackOpening) // java illegal forward references makes this terrible.
                )
            ),

            Node.path("check under the floor boards",
            Node.node("theres an axe under the floorboards")
            .onEnter(ctx -> ctx.give("axe")).next(goBackOpening)
            ),

            Node.path("> go into the hallway",
                Node.series("> the hallway is very long")
                .after("> there is a skeleton with a key around its neck at the end of the hall").branch(
                    new Node.Path("take key",
                            Node.node("> you snap the necklace off the skeletons neck, the head falls off").onEnter(ctx -> ctx.give("key")).next(goBackOpening)
                            // .onEnter(ctx -> ctx.give("key")).next(getOpening()))
                        ),
                    Node.node("look behind the skeleton","you see he had hid a gun behind him",
                        new Node.Path("take the gun",Node.node("you take the gun").onEnter(ctx -> ctx.give("gun"))).next(fight),
                        new Node.Path("take the key instead",Node.node("you take the key").onEnter(ctx -> ctx.give("key"))).next(goBackOpening)
                        )
                    )
                )
            );
        // );
        
    // this is a bit gross
    // could add real conditional nodes BUT i dont have much time.
    // conditional only works if only one node is accessible at a time else it presents the choice matrix

    // fightLoop = Node.node("your turn").branch(
    //  new Node.Path("punch",Node.node("",new Node.Path("",goCheck)).onEnter(ctx -> reduceOpponentHp(ctx, 1))),
    //  new Node.Path("shoot",Node.node("",new Node.Path("",goCheck)).requires(ctx -> ctx.has("gun")).onEnter(ctx -> reduceOpponentHp(ctx, 10)))
    // );

    //  check =  Node.node().branch(
    //     new Node.Path("",Node.node().requires(ctx -> getIntKey(ctx, "hp") <= 0).next(Node.node("you die, boowomp"))),
    //     new Node.Path("",Node.node().requires(ctx -> getIntKey(ctx,"oppHp") <= 0).next(Node.node("you kill the guy! you win!!!"))),
    //     new Node.Path("",Node.node().requires(ctx -> (getIntKey(ctx,"oppHp") >0) && (getIntKey(ctx, "hp") >0)).next(fightLoop))
    // );
    //    goBackOpening.next(opening);
    //    goInsideDoor.next(insideDoor);
    //    goCheck.next(check);
    //    goFightLoop.next(fightLoop);
}

 

   
   
   

    static final NodeBuilder escape = Node.node(
        "> you have to escape, you cant bare listen to this guy ramble for any longer\nhow do you escape?",
        Node.path("run. run as fast as you can",Node.node("you run for a while, you can hear him yell \"HEY! YOU SHOULD REALLY CONSIDER LINUX\"").lazyNext(() -> opening)),
        Node.node("maybe you can trick him","you have 2 options:", 
            Node.path("reinstall windows on his computer",Node.node("NOOOOO!!!!! HOW DARE YOU!!!!!!").next(
                Node.series(
                    "my...",
                    "beautiful...",
                    "rice...",
                    "you WILL pay for this!"
                    ).after(fight)
                )
            )
        ),
        new Node.Path("give him a free and open source heart surgery",fight)
    );

    public static void main(String[] args) {
    NodeBuilder linuxRant = Node.series("anyways, i love linux, its so cool!",
    "its awesome to just be able to control your own computer just the way you want it.", 
    "and especially the reproducability of nixos.", 
    "nixos is just so awesome."
    ).after(
        Node.node().branch(
            new Node.Path("",Node.node().requires(ctx -> ctx.isValue("OS","Linux")).next(
                Node.series("i think we are gonna be best of friends").after("the two of you go on to become the best friends ever, through the love of linux. the end :)")
            )),
            new Node.Path("",Node.node().requires(ctx -> ctx.isValue("OS","Windows")).next(
                Node.series("you filthy windows users are honestly VILE").after(escape)
            )),
            new Node.Path("",Node.node().requires(ctx -> ctx.isValue("OS","macOS")).next(
                Node.series("yknow macos is like linux.","its a unix operating system based off of freebsd","and freebsd is just like linux").after(escape)
            ))
    ));
    
    // NodeBuilder insideDoor = Node.node().requires(ctx -> ctx.has("key"));
    NodeBuilder goBackOpening = Node.node().next(opening);




   
    NodeBuilder getGoing = Node.node("well i should get going, it was nice talking to you im glad you could agree that java is terrible");
    NodeBuilder agree = Node.series("thats awesome!").after(
    Node.node("what languages do you prefer?",
        Node.path("i prefer languages like C, where im very close to the hardware",
            Node.node("ah interesting!, do you do your development on Windows or Linux?",
                Node.path("i develop on Windows",
                    Node.node("disgusting").onEnter(ctx -> ctx.give("OS","Windows")).next(fight)
                ),
                Node.path("i develop on Linux",
                    Node.node("thats what i expected").onEnter(ctx -> ctx.give("OS","Linux")).next(linuxRant)
                ),
                Node.path("i develop on macOS",Node.node("ew...").onEnter(ctx -> ctx.give("OS","macOS")).next(linuxRant))
            )
        ),
        Node.path("i prefer very high level dynamically typed languages like Python",
            Node.series("eh...",
            "python is very powerful but you can shoot yourself in the foot unless you really enforce static typing",
            "and that kind of defeats the point of python eh?"
            ).after(
                getGoing
            )
        ),
        Node.path("i prefer strict languages that ensure no errors ever get through, like Rust.",
            Node.series("oh me too!", "Rust really is one of the best languages",
            "but the borrow checker can get very annoying when working with explicit lifetimes.").after(
                getGoing
            )
        ),
        Node.path("i prefer quirky languages that aren't very practical but cool to think about, like haskell or elixir",
            Node.series("yup, i understand that lmao.",
            "im trying to learn haskell and its very fun",
            "but the Monad syntax is very confusing","whats a =>>???").after(
                getGoing
            )
        ))
    );

    NodeBuilder disagree = Node.series("wow",
     "wow...",
     "WOW...",
     "YOU",
     "You are what is wrong with programming",
     "how... DARE you."
    ).after(Node.node(
        "so tell me, GigaOOPLover? what do you love SOO much about java",
        Node.path("i love the verbosity",Node.series(
                "why would you even think that.",
                "the verbosity present in java is incredibly awful??"
                ).after(
                    Node.node("all modern languages arent THIS verbose",
                        Node.path("tbh yeah thats fair, java is kinda bad there",getGoing),
                        Node.node("nah java is based there, verbosity equals safety",
                            "NO!!! it doesnt !! im sobbing. you are clearly too lost",Node.path("",fight)
                        )
                )
            )
        ),
        Node.path("i love the concept of setters/getters",Node.node("its a disgusting feature of mutable procedural programming...").next(fight)
        ),
        Node.path("i love how tuples aren't included.",Node.node("this is by far the worst opinion").next(fight))
    ));

    Node.series("i hate java...","its level of gigaOOP is so annoying")
        .after(Node.node("do you agree", 
        Node.path("yes", agree), 
        Node.path("no, i love gigaOOP",disagree)
        )
    ).build().init();
    }
}
