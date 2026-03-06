package game;

import java.util.Scanner;

public class GetInput {
    private static Scanner sc = new Scanner(System.in);
    public static String get(String prompt){
        System.out.print(prompt);
        System.out.print(": ");
        System.out.flush();
        var resp = sc.nextLine();
        return resp;
    }
}
