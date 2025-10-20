public class ScientificCalculator {

    //     The area of a circle using the radius (π × r²)
    static double CircularRadius(double r) {
        return Math.PI * Math.pow(r, 2);
    }

    //    The hypotenuse of a right triangle with both legs equal to the radius
    static double rightTriangleHypo(double r) {
        return Math.sqrt(2) * r;
    }

    //     The result of raising the base to the exponent power
    static double raiseBase(double b, double e) {
        return Math.pow(b, e);
    }

    //     The absolute difference between the area and the hypotenuse
    static double absDifAreaHypo(double r) {
        return Math.abs(CircularRadius(r) - rightTriangleHypo(r));
    }

    public static void main(String[] args) {
        double radius = 12.5;
        int angle = 45;
        int base = 3;
        int exponent = 4;

        var a = CircularRadius(radius);
        var b = rightTriangleHypo(radius);
        var c = raiseBase(base, exponent);
        var d = absDifAreaHypo(radius);
        //     The square root of the sum of all previous calculations
        var sqSum = Math.sqrt(a + b + c + d);

        System.out.printf("Area of circle (π × r²): %.2f\n", a);
        System.out.printf("Hypotenuse of triangle (√2 × r): %.2f\n", b);
        System.out.printf("Base^Exponent (b^e): %.2f\n", c);
        System.out.printf("Absolute difference |area - hypotenuse|: %.2f\n", d);
        System.out.printf("Square root of sum: %.2f\n", sqSum);
    }
}
