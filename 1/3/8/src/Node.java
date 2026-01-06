// src/Node.java
package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Node {

    private static final String HEADER = "-----------------\n";
    private static final String NEXT_TEXT = "[ Next ]";

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
        StringBuilder childrenString = new StringBuilder();
        for (int i = 0; i < this.children.size(); i++) {
            var a = this.children.get(i);
            var displayed = a.display(context, Integer.toString(i + 1));

            childrenString
                // .append(Node.header)
                .append(displayed);
        }

        return new StringBuilder()
            // .append(Node.header)
            .append(this.content)
            .append("\n")
            .append(childrenString)
            .toString();
    }

    private Context recurseIntoUserSelection(
        Context context,
        List<Boolean> allowed
    ) {
        return this.children.get(
            context.querySelection(allowed)
        ).child.evaluate(context);
    }

    private Context recurseIntoOnlyChild(Context context) {
        return this.children.get(0).child.evaluate(context);
    }

    private void show(Context context) {
        System.out.println(this.display(context));
    }

    private boolean hasOneChild() {
        return this.children.size() == 1;
    }

    // Core function of the game engine, this evaluates the Node function, applied to Context, and recurses down the tree until a terminal node.
    // recursive evaluation of the Node tree using Context as the mutable state.
    public Context evaluate(Context context) {
        this.onEnterHook.ifPresent(fn -> fn.accept(context));
        if (!this.children.isEmpty()) {
            // generate unlocked map
            var allowed = this.children.stream()
                .map(p -> p.isUnlocked(context))
                .toList();
            boolean cannotContinue = allowed.stream().noneMatch(a -> a);
            if (cannotContinue) {
                this.show(context);
                System.out.println(
                    "All Options locked, cannot continue. Press any button to acknoledge and exit."
                );
                context.anyKey();
                return context;
            }
            if (this.hasOneChild()) {
                // if (!this.content.isEmpty()) this.show(context);
                // return this.recurseIntoOnlyChild(context);
                if (this.content.isEmpty()) {
                    // System.out.println("skipping this node");
                    return this.recurseIntoOnlyChild(context);
                } else {
                    this.show(context);
                    context.anyKey();
                    return this.recurseIntoOnlyChild(context);
                }
            } else {
                this.show(context);
                return this.recurseIntoUserSelection(context, allowed);
            }
        } else {
            // tail condition 
            this.show(context);
            return context;
        } 
    }

    // entrypoint wrapper for tree evaluation. sets up Context.
    // Context is returned just so that it can be recovered, eg. if state wants to be saved. else it would be dropped at the end of the tree.
    public Context init() {
        System.out.println("init");
        Context context = new Context();
        return this.evaluate(context);
    }

    public record Path(String content, NodeBuilder child) {
        ResolvedPath build() {
            return new ResolvedPath(this.content, this.child.build());
        }
        public Path next(NodeBuilder node) {
            this.child.next(node);
            return this;
        }
    }

    public record ResolvedPath(String content, Node child) {
        boolean isUnlocked(Context context) {
            return this.child.requiresHook.map(fn -> fn.apply(context)).orElse(
                true
            );
        }

        String display(Context context, String label) {
            return new StringBuilder()
                .append(
                    this.isUnlocked(context)
                        ? ("\n[" + label + "]")
                        : "\n[LOCKED]"
                )
                // .append("")
                .append(this.content)
                // .append("")/
                .toString();
        }
    }

    // Node.series(""...).after(Node.node("", "")) -> NodeBuilder
    // i hate java so much
    // this is essentially a partially applied function because java doesnt support varargs at the front.
    public static Series series(String... contents) {
        return new Series(contents);
    }

    public static record Series(String[] contents) {
        public NodeBuilder after(NodeBuilder node) {
            return new NodeBuilder().next(List.of(contents), node);
        }

        public NodeBuilder after(String content) {
            // return new NodeBuilder(content);
            return this.after(Node.node(content));
        }
    }

    // wrapper to decrease verbosity
    public static NodeBuilder node() {
        return new NodeBuilder();
    }
    
    public static NodeBuilder node(String content) {
        return new NodeBuilder(content);
    }

    public static Path path(String pathEntry, NodeBuilder tail) {
        return new Path(pathEntry, tail);
    }

    public static NodeBuilder node(String content, Path... paths) {
        return new NodeBuilder(content).branch(paths);
    }
    public static Path node(String pathEntry, String content, Path... paths) {
        // return new Path(pathEntry, new NodeBuilder(content).branch(paths));
        return new Path(pathEntry, new NodeBuilder(content).branch(paths));
    }

    // silentNode is meant just for side-effects.
    public static NodeBuilder silentNode() {
        return new NodeBuilder("");
    }

    // public static Path path(String content, ){

    // }
    // declarative builder for node.
    public static class NodeBuilder {

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

        public NodeBuilder addSingle(Path path) {
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

        public NodeBuilder next(NodeBuilder next) {
            this.children.add(new Path(Node.NEXT_TEXT, next));
            return this;
        }
        public NodeBuilder lazyNext(Supplier<NodeBuilder> node){
            return node.get();
        }   
        public NodeBuilder next(String content, NodeBuilder next) {
            var child = new NodeBuilder(content);

            this.children.add(new Path(Node.NEXT_TEXT, child.next(next)));
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
            tail.children.add(new Path(NEXT_TEXT, next));
            var root = nodes
                .stream()
                .reduce((subTree, newHead) -> {
                    // System.out.println(subTree.content);
                    newHead.children.add(new Path(NEXT_TEXT, subTree));
                    return newHead;
                })
                .get();
            // System.out.printf("root: %s\ntail: %s\n",root.content,tail.content);
            this.children.add(new Path(Node.NEXT_TEXT, root));
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
