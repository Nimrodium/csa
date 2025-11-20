/*

Create a program called PasswordValidator that checks if a password meets minimum security requirements. Your program should check if a password meets at least three of the following criteria:
Contains at least 8 characters
Contains at least one uppercase letter
Contains at least one lowercase letter
Contains at least one digit
Use if statements to check each criterion separately. Create a counter variable that increments by 1 for each criterion the password meets. Display a message indicating whether the password is "Strong" (meets 3+ criteria) or "Weak" (meets fewer than 3 criteria).

*/
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

class PasswordValidator {

    static final int totalCriteria = 4;

    static Stream<Character> stringToCharIter(String s) {
        return s.codePoints().mapToObj(i -> (char) i);
    }

    // print checkpoint and inc counter in a functional style because im like that
    static int passedCheck(String checkName, int n) {
        n++;
        System.out.printf(
            "passed %s check [%d/%d]\n",
            checkName,
            n,
            totalCriteria
        );
        return n;
    }

    static boolean validatePassword(String passwd) {
        System.out.printf("testing %s\n", passwd);
        int criteriaCount = 0;
        if (passwd.length() < 8) {
            System.err.println("password less than 8 characters");
            return false;
        }
        criteriaCount = passedCheck("length", criteriaCount);
        if (!stringToCharIter(passwd).anyMatch(Character::isUpperCase)) {
            System.err.println(
                "password does not contain an upper case character"
            );
            return false;
        }
        criteriaCount = passedCheck("uppercase", criteriaCount);
        if (!stringToCharIter(passwd).anyMatch(Character::isLowerCase)) {
            System.err.println(
                "password does not contain a lower case character"
            );
            return false;
        }
        criteriaCount = passedCheck("lowercase", criteriaCount);
        if (!stringToCharIter(passwd).anyMatch(Character::isDigit)) {
            System.err.println("password does not contain a digit");
            return false;
        }
        criteriaCount = passedCheck("digit", criteriaCount);
        System.out.printf("%s passes all password requirements\n", passwd);
        return true;
    }

    public static void main(String[] args) {
        // String password = "MyP@ss123"; // test with different passwords

        new ArrayList<>(
            Arrays.asList(
                "MyP@ss123",
                "password",
                "myawesomepassword5652",
                "ActuallyGoodP4ssword",
                "14534523411",
                "Psswd1313555",
                "NEUg0",
                "fjsdkfh1!@#%9849KJFADKF"
            )
        ).forEach(p -> validatePassword(p));
    }
}
