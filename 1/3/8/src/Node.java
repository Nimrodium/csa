package src;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class Node {

    private final String header = "-----------------";
    // int id;
    String content;
    ArrayList<Path> children;
    // lambda to find when the node should be unlocked
    // requiresHook -> True when Unlocked
    Optional<Function<Context, Boolean>> requiresHook;
    // lambda to find when
    Optional<Function<Context, Void>> onEnterHook;

    String display(Context context) {
        // this.children.stream().enumerate().map((a,i) -> a.display(context,i)) but NO ENUMERATE OR EVEN ZIP FUNCTION
        // look at how much code is needed to replicate my beautiful functional pipeline oneliner...
        String paths;
        ArrayList<String> displayedChildren = new ArrayList<String>();
        StringBuilder childrenString = new StringBuilder();
        for (int i = 0; i < this.children.size(); i++) {
            var a = this.children.get(i);
            var displayed = a.display(context, Integer.valueOf(i).toString());
            displayedChildren.add(displayed);
            childrenString.append(this.header + displayed);
        }

        return new StringBuilder()
            .append(this.header)
            .append(this.content)
            .append(childrenString)
            .toString();
    }

    public record Path(
        String content,
        // int child,
        Node child,
        Optional<Consumer<Context>> onSelectHook
    ) {
        boolean isUnlocked(Context context) {
            return this.child.requiresHook.isPresent()
                ? this.child.requiresHook.get().apply(context)
                : true;
        }

        String display(Context context, String label) {
            return new StringBuilder()
                .append(
                    this.isUnlocked(context) ? ("[" + label + "]") : "[LOCKED]"
                )
                .append("| ")
                .append(this.content)
                .append(" |")
                .toString();
        }
    }
}
