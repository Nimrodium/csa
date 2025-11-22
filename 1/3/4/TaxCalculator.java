import java.util.Scanner;

class TaxCalculator {

    static Scanner sc = new Scanner(System.in);

    static int getTaxRate(double annualIncome) {
        if (annualIncome <= 25_000) {
            return 5;
        } else if (annualIncome <= 50_000) {
            return 10;
        } else if (annualIncome <= 100_000) {
            return 15;
        } else if (annualIncome <= 200_000) {
            return 20;
        } else {
            return 25;
        }
    }

    public static void main(String[] args) {
        System.out.println("enter Annual Income");
        double annualIncome = sc.nextDouble();
        double taxRate = getTaxRate(annualIncome);
        double taxxed = annualIncome * (taxRate / 100);
        System.out.printf(
            "annual income: %.2f\neffective tax rate: %.2f%%\ntax amount: $%.2f",
            annualIncome,
            taxRate,
            taxxed
        );
    }
}
