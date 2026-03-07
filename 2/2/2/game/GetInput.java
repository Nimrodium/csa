package game;

import java.util.Scanner;

public class GetInput {

    private static Scanner sc = new Scanner(System.in);

    public static String get(String prompt) {
        System.out.print(prompt);
        System.out.print(": ");
        System.out.flush();
        var resp = sc.nextLine();
        return resp;
    }

    public static int getInt(String prompt) {
        var s = get(prompt);
        int i;
        while (true) {
            try {
                i = Integer.parseInt(s);
                break;
            } catch (NumberFormatException e) {
                System.out.printf(
                    "a number is required, %s is not a number \n",
                    s
                );
                s = get("try again");
            }
        }
        return i;
    }
}
