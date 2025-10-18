import java.util.Scanner;
import java.util.function.Function;

public class RestaurantOrderCalculator {

    // copying the generic function from other file, now im glad i made it generic because it reduces so much boilerplate
    // it is a bit impractical but i wanted to learn how to use generics and using a lambda.
    // defines a generic function which continuouslly reads from stdin until a token can be parsed into T.
    static <T> T getInput(
        Scanner stdin,
        String prompt,
        Function<String, T> parser // Function<A,B> == Function :: A -> B; type signature of a lambda expression.
    ) {
        while (true) {
            System.out.print(prompt + " "); // print prompt
            System.out.flush(); // flush stdout buffer
            String nx = stdin.nextLine(); // read next line from stdin;
            try {
                // this try;catch is a poor mans match {Ok(a) => a, Err(err) => println!("{err}")}
                return parser.apply(nx); // apply lambda
            } catch (Exception e) {
                // if this was production code i might try to find a more specific error to catch.
                System.out.printf(
                    "try again (\"%s\" is not the expected type)\n", // i wish there was a clearer way of formatting a string, like rust or python's way. "{var}"
                    nx
                ); // print err and continue loop
            }
        }
    }

    public static void main(String[] args) {
        // despite the price variables being under the prompt for user and collect header.
        // it tells me to set fixed prices. and it doesnt make sense for the user to be able to define
        // the price. so im defining it here
        final double ppAppetizer = 8.50;
        final double ppMain = 15.75;

        Scanner stdin = new Scanner(System.in);
        String customerName = getInput(stdin, "Name (str):", a -> a); // a -> a is a nop lambda. 
        // if i wanted to make this a more proper module, id provide a overloaded form that does not take in a lambda and just returns the string.
        int nAppetizers = getInput(stdin, "appetizers (int):", Integer::parseInt); // in a proper module i might also provide wrappers that fill in the lambda. getInt();
        int nMainCourse = getInput(stdin,"# Main courses (int):",Integer::parseInt);
        boolean add18PTip = getInput(stdin, "add an 18% tip? (Y/n):", a -> a.toLowerCase().equals("y")); // turns out Y/n is extremely easy to implement using getInput.
        stdin.close();
        double appetizerPrice = ppAppetizer*nAppetizers;
        double mainCoursePrice = ppMain*nMainCourse;      
        double subtotal = appetizerPrice+mainCoursePrice;
        double total = subtotal+(subtotal*( add18PTip ? 0.18 : 0.0 ));
        System.out.printf("customer name: %s\nappetizers ordered: %d\tprice: %.2f\nmain courses ordered: %d\tprice: %.2f\n18%% tip: %b\nsubtotal: %.2f\ntotal: %.2f\n",
        customerName,
        nAppetizers,
        appetizerPrice,
        nMainCourse,
        mainCoursePrice,
        add18PTip,
        subtotal,
        total);
    }
}
