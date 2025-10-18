import java.util.Scanner;
import java.util.function.Function;

public class StudentInfoCollector {

    // a full record for this is a bit impractical too, but i wanted to use one again to get familiar.
    static record Student(
        String name,
        int age,
        double GPA,
        boolean fullTime,
        int gradYear
    ) {
        @Override
        public String toString() {
            return String.format(
                "-- student --\nname: %s\nage: %d\nGPA: %.2f\nfullTime: %b\ngradYear: %d",
                this.name,
                this.age,
                this.GPA,
                this.fullTime,
                this.gradYear
            );
        }
    }

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
            String nx = stdin.next(); // read next token from stdin;
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
        Scanner stdin = new Scanner(System.in);
        String name = getInput(stdin, "name:", a -> a);
        int age = getInput(stdin, "age:", Integer::parseInt);
        double GPA = getInput(stdin, "GPA:", Double::parseDouble);
        boolean fullTime = getInput(stdin, "fullTime", Boolean::parseBoolean); // parseBoolean interprets any value other than "true" as false, which is ok i guess but i wouldve liked it to err if not on the strenum true false
        int gradYear = getInput(stdin, "gradYear", Integer::parseInt);
        Student student = new Student(name, age, GPA, fullTime, gradYear);
        System.out.println(student);
        stdin.close(); // end of program will close fd0 anyways.
    }
}
