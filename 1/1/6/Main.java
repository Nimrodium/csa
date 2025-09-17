/*
    program requires:
    * camelCase [*]
    * multiline and inline comments [*|*]
    * print and println methods [*|*]
    * variables [*]
    * arithmetic expressions [*]
    * a compound assignment operator [*]
    * conversion between int and double data types [*]
*/
// Choose any integer, double it,
//  add 6, divide it in half,
//  and subtract the number you started with.
//  The answer is always 3!

// x + (6/2) - x
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
        System.out.print("There is a math riddle used to teach distributive, commutative, and associative properties:\n");
        System.out.println("\t\"Choose any integer, double it.\n\tadd 6,\n\t divide it in half,\n\tand subtract the number you started with.\n\tthe answer will always be 3!\"");
        System.out.println("use the following interactive loop to observe this riddle, to exit use Ctrl+C.");
        Scanner stdin = new Scanner(System.in);
        while (true) {
            try{
                double input = stdin.nextDouble();
                int x =  6/2;
                System.out.println("first the division is evaluated, 6/2 = " + x);
                int y = x;
                x+=input; // conversion to int from double happens here
                System.out.println("then the input is added, " + y + " + " + input + " = " + x);
                x-=input;
                System.out.println("then the input is subtracted, " + (int)(y+input) + " - " + input + " = " + x);
                System.out.println("`" + input + "+(6/2)- " + input + " = 3` because x cancels out and you are left with 3");

            }catch (InputMismatchException e) {
                System.out.println("invalid non integer input, please try again");
                stdin.nextLine(); // to consume token else it gets stuck in infinite loop.
            }
        }
    }
}
