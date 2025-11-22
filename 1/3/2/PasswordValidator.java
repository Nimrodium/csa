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

    static Stream<Character> stringToCharIter(String s) {
        return s.codePoints().mapToObj(i -> (char) i);
    }

    static int validatePassword(String passwd) {
        int strength = 0;
        if (passwd.length() < 8) {
            System.err.printf("%s less than 8 characters\n", passwd);
        } else strength += 1;
        // using 4 seperate if statements to test each criteria. using streamapi to generate the boolean efficiently
        if (!stringToCharIter(passwd).anyMatch(Character::isUpperCase)) {
            System.err.printf(
                "%s does not contain an upper case character\n",
                passwd
            );
        } else strength += 1;
        if (!stringToCharIter(passwd).anyMatch(Character::isLowerCase)) {
            System.err.printf(
                "%s does not contain a lower case character\n",
                passwd
            );
        } else strength += 1;
        if (!stringToCharIter(passwd).anyMatch(Character::isDigit)) {
            System.err.printf("%s does not contain a digit\n", passwd);
        } else strength += 1;

        String strengthStr;
        if (strength < 3) {
            strengthStr = "weak";
        } else {
            strengthStr = "strong";
        }
        System.out.printf("%s password strength: %s\n", passwd, strengthStr);
        return strength;
    }

    public static void main(String[] args) {
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
