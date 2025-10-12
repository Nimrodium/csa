class ReturnCalculator {

    static class Calculator {

        static int getFirstNumber() {
            return 25;
        }

        static int getSecondNumber() {
            return 8;
        }

        static double getDecimalValue() {
            return 3.5;
        }
    }

    public static void main(String[] args) {
        // binding is not necessary because Calculator.getFirstNumber() is an expression that has a return value, and so can be embedded directly into an expression, (...) is also an expression.
        // in more functional languages like rust if .. else itself is an expression and can be embdedded just like +-*/%; let val2 = if val { false } else { true };
        int totalNum = 3;
        int calc1 = Calculator.getFirstNumber() + Calculator.getSecondNumber();
        double cacl2 =
            (double) Calculator.getSecondNumber() *
            Calculator.getDecimalValue();
        double calc3 =
            (Calculator.getFirstNumber() +
                Calculator.getSecondNumber() +
                Calculator.getDecimalValue()) /
            totalNum;
        double anotherCalc = totalNum + totalNum;
    }
}
