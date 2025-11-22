/*
 * Activity 1.3.2
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GuessChecker132 {

    static Scanner sc = new Scanner(System.in);

    static List<Integer> splitDigit(int n) {
        // let
        List<Integer> xs = new ArrayList<>();
        int value = n;
        // in
        while (true) {
            // let
            int total = value / 10;
            int digit = value % 10;
            // in
            xs.add(digit);
            if (total != 0) {
                value = total;
                continue; // redundant but makes code 1% more readable, due to the impl im referencing "itoa.nsm:45 jifnz r8,$!std_itoa_ascii_loop"
            } else {
                break;
            }
        }
        System.err.println(n + "->" + xs.reversed());
        return xs.reversed();
    }

    static int compare(List<Integer> guess, List<Integer> target) {
        // was gonna do basically return guess.stream().zip(target.stream()).map((a,b) -> a==b ? 1 : 0).sum(); but java disappoints me once more by not having tuples... or enumeration methods
        int c = 0;
        for (int i = 0; i < guess.size(); i++) {
            int g = guess.get(i);
            int t = target.get(i);
            if (g == t) {
                System.out.printf("pos: %d : HIT\n", i);
                c += 1;
            } else {
                System.out.printf("pos: %d : MISS\n", i);
            }
        }
        return c;
    }

    public static void main(String[] args) {
        /* Add any variables you will need throughout the program here. */
        // haha at the bottom of the pltw it says that in LATER programs itll become like a true guessing game with multiple guesses, whoops... it was too easy to just implement that now ok.
        while (true) {
            // Generate a random number
            System.out.println("Guess the random number...");
            int targetNumber = getRandomNumber();
            // System.out.println(targetNumber); // uncomment for debugging
            guessing: while (true) {
                // Get the user's guess
                int guess = getGuess();
                // System.out.println(guess); // uncomment for debugging
                /*your code here*/
                int hits = compare(splitDigit(guess), splitDigit(targetNumber));
                System.out.printf("hits: %d\n", hits);
                if (hits == 4) {
                    System.out.println(
                        "!!!! YOU GOT THE NUMBER RIGHT !!!!\nnext number..."
                    );
                    break guessing;
                }
            }
        }
        // close Scanner when done
        // sc.close();
        // program is terminated via ^C so sc.close() never runs, and the resource is closed during jvm shutdown anyways.
    }

    // Checks to ensure no duplicate digits in a int.
    public static boolean hasDupes(int num) {
        boolean[] digs = new boolean[10];
        while (num > 0) {
            if (digs[num % 10]) return true;
            digs[num % 10] = true;
            num /= 10;
        }
        return false;
    }

    // Creates a new random 4 digit code 1000-9999 with no duplicates.
    public static int getRandomNumber() {
        int target = (int) (Math.random() * 9000 + 1000);
        while (hasDupes(target)) target = (int) (Math.random() * 9000 + 1000);
        return target;
    }

    // Prompts the user for a guess and repeats until valid guess is made.
    public static int getGuess() {
        int userGuess = 0;
        boolean validGuess = false;

        while (!validGuess) {
            System.out.print(
                "Guess a 4-digit number from 1000 to 9999 with no duplicate digits: "
            );
            userGuess = sc.nextInt();
            if (!(hasDupes(userGuess) || (userGuess < 1000))) validGuess = true;
        }
        return userGuess;
    }
}
