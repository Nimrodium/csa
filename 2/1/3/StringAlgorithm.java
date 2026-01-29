import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StringAlgorithm {

    static Scanner sc = new Scanner(System.in);

    static String getLine() {
        return sc.nextLine();
    }

    static void removeEveryOther(String s) {
        var strBuf = new StringBuilder();
        for (int i = 0; i < s.length(); i += 2) {
            strBuf.append(s.charAt(i));
        }
        System.out.println("every other char: " + strBuf.toString());
    }

    static void removeVowels(String s) {
        List<Character> vowels = List.of('a', 'e', 'i', 'o', 'u');
        var strBuf = new StringBuilder();
        for (Character c : s.toCharArray()) {
            if (!vowels.contains(c)) {
                strBuf.append(c);
            }
        }
        System.out.println("no vowels: " + strBuf.toString());
    }

    static void dyslexiaSim(String s) {
        // demonstrating streamapi
        var targetChars = List.of('d', 'b', 'p', 'q');
        String dyslexic = s
            .chars()
            .mapToObj(c -> (char) c)
            .map(c ->
                targetChars.contains(Character.toLowerCase(c))
                    ? targetChars.get(
                          (int) (Math.random() * targetChars.size())
                      )
                    : c
            )
            .map(String::valueOf)
            .collect(Collectors.joining());
        System.out.println("dyslexic: " + dyslexic);
    }

    // using low level ascii manipulation because fun!
    // everything here is derived just from the ascii table, no prior knowledge
    static char toLowercase(char c){
        if ('A'<=c && c<='Z'){
            int ci = (int)c;
            int offset = (int)'a'-(int)'A';
            return (char)(ci+offset);
        }else{    
            return c;
        }
    }
    static List<Integer> findFrequency(String str){
        List<Integer> map = IntStream.generate(() -> 0).limit(26).boxed().collect(Collectors.toList());
        for (char c:str.toCharArray()){
            // get index of character in alphabet by casting to int and subtracting 'a' offset
            int index = ((int)toLowercase(c))-((int)'a'); 
            map.set(index, map.get(index)+1);
        }
        return map;
    }
    static void anagramChecker(String s1,String s2) {
        if (s1.length() != s2.length()){
            System.err.printf("%s and %s are not anagrams\n",s1,s2);
        }else if (findFrequency(s1).equals(findFrequency(s2))){
            System.out.printf("%s and %s are anagrams!",s1,s2);
        }else{
            System.err.printf("%s and %s are not anagrams\n",s1,s2);
        }
    }

    public static void main(String[] args) {
        removeEveryOther("computer");
        removeVowels("louis");
        dyslexiaSim("dumb dumb dumb");
        anagramChecker("listen", "silent");
    }
}
