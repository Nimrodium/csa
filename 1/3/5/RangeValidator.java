import java.util.Scanner;

class RangeValidator {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int x = sc.nextInt();
        int y = sc.nextInt();
        int z = sc.nextInt();

        int pass = 0;
        if (1 <= x && x <= 100) {
            System.out.println(x);
            pass += 1;
            System.out.println("x = [1..=100]");
        } else System.out.println("x != [1..=100]");
        if (50 <= y && y <= 150) {
            System.out.println(y);
            pass += 1;
            System.out.println("y = [50..=150]");
        } else System.out.println("y != [50..=150]");
        if (z < 0 || z > 200) {
            System.out.println(z);
            pass += 1;
            System.out.println("z = [..0] + [200..]");
        } else System.out.println("z != [..0] + [200..]");
        System.out.println(pass < 3 ? "Some values out of range" : "All valid");
    }
}
