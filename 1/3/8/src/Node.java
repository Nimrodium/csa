// src/Node.java
package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class Node {

    private final String header = "-----------------\n";

    public String content;
    List<ResolvedPath> children;
    // lambda to find when the node should be unlocked
    // requiresHook -> True when Unlocked
    Optional<Function<Context, Boolean>> requiresHook;
    // lambda to produce side effects to the Context
    Optional<Consumer<Context>> onEnterHook;
    private Node(
        String content,
        List<ResolvedPath> children,
        Optional<Function<Context, Boolean>> requiresHook,
        Optional<Consumer<Context>> onEnterHook
    ) {
        this.content = content;
        this.children = children;
        this.requiresHook = requiresHook;
        this.onEnterHook = onEnterHook;
    }

    // would make more sense to make this toString, but it feels too specific for that.
    public String display(Context context) {
        // this.children.stream().enumerate().map((a,i) -> a.display(context,i)) but NO ENUMERATE OR EVEN ZIP FUNCTION
        // look at how much code is needed to replicate my beautiful functional pipeline oneliner...
        // var displayedChildren = new ArrayList<String>();
        StringBuilder childrenString = new StringBuilder();
        for (int i = 0; i < this.children.size(); i++) {
            var a = this.children.get(i);
            var displayed = a.display(context,
                Integer.toString(i + 1)
            );
            // displayedChildren.add(displayed);
            childrenString.append(this.header).append(displayed);
        }

        return new StringBuilder()
            .append(this.header)
            .append(this.content)
            .append("\n")
            .append(childrenString)
            .toString();
    }

    // Core function of the game engine, this evaluates the Node function, applied to Context, and recurses down the tree until a terminal node.
    // recursive evaluation of the Node tree using Context as the mutable state.
    public Context execute(Context context) {
        System.out.println("children:" + this.children);
        System.out.println(this.display(context));
        this.onEnterHook.ifPresent(fn -> fn.accept(context));

        if (!this.children.isEmpty()) {
            // generate unlocked map
            List<Boolean> allowed = this.children.stream()
                .map(p -> p.isUnlocked(context))
                .toList();
            if (allowed.stream().allMatch(b -> b == false)) {
                System.out.println(
                    "All Options locked, cannot continue. Press any button to acknoledge and exit."
                );
                context.anyKey();
                return context;
            }
            if (this.children.size() == 1) {
                // implicitly because lock check passed this means that the single option is true
                // if (!this.content.equals("")) context.anyKey(); // if no content message, immediately recurse.
                context.anyKey();
                return this.children.get(0).child.execute(context);
            }
            var selection = context.querySelection(allowed);

            return this.children.get(selection).child.execute(context); // recurse into child
        } else return context; // recursive return
    }

    // entrypoint wrapper for tree evaluation. sets up Context.
    // Context is returned just so that it can be recovered, eg. if state wants to be saved. else it would be dropped at the end of the tree.
    public Context init() {
        Context context = new Context();
        return this.execute(context);
    }

    public record Path(String content, NodeBuilder child) {
        ResolvedPath build() {
            return new ResolvedPath(this.content, this.child.build());
        }
    }

    public record ResolvedPath(String content, Node child) {
        boolean isUnlocked(Context context) {
            return this.child.requiresHook.isPresent()
                ? this.child.requiresHook.get().apply(context)
                : true;
        }

        String display(Context context, String label) {
            return new StringBuilder()
                .append(
                    this.isUnlocked(context)
                        ? ("\n[" + label + "]")
                        : "[LOCKED]"
                )
                .append("| ")
                .append(this.content)
                .append(" |")
                .toString();
        }
    }
    // wrapper to decrease verbosity
    public static NodeBuilder node(){
        return new NodeBuilder();
    }
    public static NodeBuilder node(String content){
        return new NodeBuilder(content);
    }
    public static NodeBuilder node(String content, Path... paths){
        return new NodeBuilder().content(content).branch(paths);
    }
    // silentNode is meant just for side-effects.
    public static NodeBuilder silentNode(){
        return new NodeBuilder("");
    }
    // public static Path path(String content, ){

    // }
    // declarative builder for node.
    public static class NodeBuilder {
        private final String nextText = "[ Next ]";
        String content = "";
        ArrayList<Path> children = new ArrayList<>();
        Optional<Function<Context, Boolean>> requiresHook = Optional.empty();
        Optional<Consumer<Context>> onEnterHook = Optional.empty();

        // @Override
        // public toString(){
            
        // }

        public NodeBuilder(String content) {
            this.content = content;
        }

        public NodeBuilder content(String content) {
            this.content = content;
            return this;
        }

        public NodeBuilder() {}

        public NodeBuilder branch(Path... paths) {
            // this.children = new ArrayList<>(Arrays.asList(paths));
            this.children.addAll(Arrays.asList(paths));
            return this;
        }
        public NodeBuilder addSingle(Path path){
            this.branch(path);
            return this;
        }

        public NodeBuilder requires(Function<Context, Boolean> lambda) {
            this.requiresHook = Optional.of(lambda);
            return this;
        }

        public NodeBuilder onEnter(Consumer<Context> lambda) {
            this.onEnterHook = Optional.of(lambda);
            return this;
        }
        public NodeBuilder next(NodeBuilder next){
            this.children.add(new Path(this.nextText,next));
            return this;
        }
        public NodeBuilder next(String content,NodeBuilder next){
            var child = new NodeBuilder(content);

            this.children.add(new Path(this.nextText,child.next(next)));
            return this;
        }
        public NodeBuilder next(List<String> contents, NodeBuilder next) {
            if (contents.isEmpty()) {
                return this; // no op
            }
            var nodes = contents
                .reversed()
                .stream()
                .map(c -> new NodeBuilder(c))
                .toList();
            NodeBuilder tail = nodes.get(0);
            tail.children.add(new Path(nextText, next));
            var root = nodes
                .stream()
                .reduce((subTree, newHead) -> {
                    // System.out.println(subTree.content);
                    newHead.children.add(new Path(nextText, subTree));
                    return newHead;
                })
                .get();
            System.out.printf("root: %s\ntail: %s\n",root.content,tail.content);
            this.children.add(new Path(this.nextText, root));
            return this;
        }

        public Node build() {
            return new Node(
                content,
                children
                    .stream()
                    .map(p -> p.build())
                    .toList(),
                requiresHook,
                onEnterHook
            );
        }
    }
}
// NodeBuilder("hi").next("youre finally awake.").next("how are you...").branch(new Path("1",new NodeBuilder("")))
// NodeBuilder("hi").next("youre finally awake", "how are you...", "are you fine")
// fold list into NodeBuilder with nested child objects

// foldr list by generating a nodebuilder and then building it, then next value append node, build it, ..., if empty acc then then root, just new nodebuilder content build. if not empty then new, append, build.
// with addition of ResolvedPath (runtime path), i can now return just an alias to the last nodebuilder. yes.
