import java.util.Scanner;

public class ShippingCalculator {

    static double calculateShippingCost(double w) {
        if (w <= 2) {
            return 3.50;
        } else if (w > 2 && w <= 5) {
            return 5.75;
        } else if (w > 5 && w <= 10) {
            return 8.90;
        } else if (w > 10 && w <= 20) {
            return 12.50;
        } else {
            return 15.0 + (0.50 * (w - 20));
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter weight of package:");
        // Your code here
        System.out.println(calculateShippingCost(sc.nextDouble()));
        sc.close();
    }
}
