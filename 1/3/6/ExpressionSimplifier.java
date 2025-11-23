import java.util.Scanner;

class ExpressionSimplifier {

    static Scanner sc = new Scanner(System.in);

    static boolean areNonNegativeSimple(int x, int y) {
        // the simplified expression:
        // S: Let (x,y) = (-1,-1) in (x >= 0) && (y >= 0) -> (-1 >= 0) && (-1 >= 0) -> False && False -> False
        return (x >= 0) && (y >= 0);
    }

    static boolean areNonNegativeComplex(int x, int y) {
        // the complex expression:
        // S: Let (x,y) = (-1,-1) in !((x < 0) || (y < 0)) -> !((-1 < 0) || (-1 < 0)) -> !(True || True) -> !True -> False
        // it can be simplified by distributing the NOT inside the expression -> (x >= 0) && (y >= 0) because of De Morgan's laws which state:
        // !(a || b) = (!a && !b)
        // and to invert a<b is -> a>=b
        return !((x < 0) || (y < 0));
    }

    public static void main(String[] args) {
        int x = sc.nextInt();
        int y = sc.nextInt();
        // assume simple is trusted
        System.out.printf(
            "testing the two expressions are they the same? %b\n",
            areNonNegativeSimple(x, y) == areNonNegativeComplex(x, y)
        );
    }
}
