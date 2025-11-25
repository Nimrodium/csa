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

    String content;
    ArrayList<Path> children;
    // lambda to find when the node should be unlocked
    // requiresHook -> True when Unlocked
    Optional<Function<Context, Boolean>> requiresHook;
    // lambda to produce side effects to the Context
    Optional<Consumer<Context>> onEnterHook;

    private Node(
        String content,
        ArrayList<Path> children,
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
        ArrayList<String> displayedChildren = new ArrayList<String>();
        StringBuilder childrenString = new StringBuilder();
        for (int i = 0; i < this.children.size(); i++) {
            var a = this.children.get(i);
            var displayed = a.display(
                context,
                Integer.valueOf(i + 1).toString()
            );
            displayedChildren.add(displayed);
            childrenString.append(this.header + displayed);
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
        System.out.println(this.display(context));
        this.onEnterHook.ifPresent(fn -> fn.accept(context));

        if (this.children.size() > 0) {
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

    public record Path(String content, Node child) {
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

    // declarative builder for node.
    public static class NodeBuilder {

        String content = "";
        ArrayList<Path> children = new ArrayList<>();
        Optional<Function<Context, Boolean>> requiresHook = Optional.empty();
        Optional<Consumer<Context>> onEnterHook = Optional.empty();

        public NodeBuilder(String content) {
            this.content = content;
        }

        public NodeBuilder content(String content) {
            this.content = content;
            return this;
        }

        public NodeBuilder children(Path... paths) {
            // this.children = new ArrayList<>(Arrays.asList(paths));
            this.children.addAll(Arrays.asList(paths));
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

        public Node build() {
            return new Node(content, children, requiresHook, onEnterHook);
        }
    }
}
