import java.util.Scanner;

class BooleanValidator {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Enter value for a (true/false): ");
        boolean a = sc.nextBoolean();
        System.out.print("Enter value for b (true/false): ");
        boolean b = sc.nextBoolean();
        System.out.print("Enter value for c (true/false): ");
        boolean c = sc.nextBoolean();
        System.out.printf(
            "!(a && b) == (!a||!b) -> %s\n",
            !(a && b) == (!a || !b) ? "EQUIVALENT" : "NOT EQUIVALENT"
        );
        System.out.printf(
            "!(a || b) == (!a && !b) -> %s\n",
            !(a || b) == (!a && !b) ? "EQUIVALENT" : "NOT EQUIVALENT"
        );
        System.out.printf(
            "!(a && b && c) == (!a || !b || !c) -> %s\n",
            !(a && b && c) == (!a || !b || !c) ? "EQUIVALENT" : "NOT EQUIVALENT"
        );
    }
}
