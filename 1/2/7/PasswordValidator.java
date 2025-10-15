public class PasswordValidator{
    static void validate(String pswd){
        final String password123 = "password123"; // declares static BAD PASSWORD!
        boolean isLT6 = pswd.length()<6; // checks if the password is above the minimum length, too short passwords are easy to brute-force.
        boolean contains123 = pswd.indexOf("123") != -1; // can just be String.contains, why must you overcomplicate it.  // checks if the password contains the substring 123
        boolean isPassword123 = pswd.equals(password123); // checks if the password is literally equal to password123
        boolean isPassword123Again = pswd.compareTo(password123) == 0; // this is completely pointless. equals already does it and better.
        if (isLT6 || isPassword123 || contains123){ // if any declare unsafe
            System.out.printf("your password [%s] is insecure :( --\nis-less-than-6-characters: %b\nis-password123: %b\ncontains-123: %b\n",pswd,isLT6,isPassword123,contains123); 
        }else{
            System.out.printf("your password [%s] is secure!\n",pswd);
        }
    }
    public static void main(String[] args) {
        String weak = "bad";
        String medium = "password123";
        String strong = "4567890abcdefverystrongpassword";
        validate(weak);
        validate(medium);
        validate(strong);
    }
}