import java.util.List;
import java.util.stream.IntStream;

class Main {
    // this function checks if a word is a palindrome by checking 
    // if the two ends of the string are equal to each other until it reaches the middle,
    // if they are equal, then it is a palindrome.
    // this implementation does not use a nested structure as it is actually much more efficient
    // to simulate nested behavior by iterating forwards and backwards at the same time via front,end ptrs. 
    // i was going to demonstrate how to do this with a nested while+for, but i have come to the conclusion that,
    // there is no reasonable implementation which uses a nested loop to find palindromes, 
    // at most, the 2ptr design would be seperated into two seperate sequential for loops, 
    // which then compare results after both complete. but without making a complicated and contrived system 
    // with wasted iteration cycles there is no proper way. palindrome detection is not exactly a nested operation, 
    // as there is only one cycle of operations required for each character/
    // in any way the function has O(n) time instead of O(n²) which would have appeared if i forced a nested structure
    static void isPalindrome(String str){
        int front = 0;
        int end = str.length()-1;
        while (true){
            if (str.charAt(front) != str.charAt(end)){
                System.err.printf("%s is not a palindrome!\n",str);
                break;
            }else if (end <= front){
                // reached middle for even, or inverted now for odd.
                System.out.printf("%s is a palindrome!\n",str);
                break;
            }else {
                front++;
                end--;
            }
        }
    }

    static void simpleNested(){
        int i = 1;
        while (i<=7){
            var buf = new StringBuilder();
            for (int n=1;n<i;n++){
                buf.append(n);
            }
            System.out.println(buf.toString());
            i++;
        }
    }

    static void simpleNestedBackwards(){
        int i = 7;
        while (i>0){
            var buf = new StringBuilder();
            for (int n=1;n<i;n++){
                buf.append(n);
            }
            System.out.println(buf.toString());
            i--;
        }
    }
    // this function indexes each character's frequency in a string and prints the info to stdout, 
    // this can be useful in cryptography to determine equality between ciphers, if there is a similar distribution, 
    // but with different letters, a transformation can be deduced which will transform the cipher into the 
    // deciphered text, then, if another cipher with an unknown deciphered text is found, it may be possible 
    // to apply the same transformation and decipher it. 

    static void frequencyAnalysis(String str){
        str = str.toLowerCase();
        List<Character> alphabet = IntStream.iterate((int)'a', i -> i+1).boxed()
        .takeWhile(i -> i<=(int)'z').map(i -> (char)(int)i).toList();
        for (Character alpha:alphabet){
            int counter = 0;
            for (Character letter:str.toCharArray()){                
                if (letter.equals(alpha)){
                    counter++;
                }
            }
            if (counter>0){
            System.out.printf("there are %d instances of the letter \'%s\' in the str \"%s\"\n",counter,alpha,str);
            }
        }
    }
    public static void main(String[] args) {
        isPalindrome("bob");
        isPalindrome("racecar");
        isPalindrome("tacocat");
        isPalindrome("ollo");
        isPalindrome("glorp");
        isPalindrome("torch");
        frequencyAnalysis("this is a test phrase!");
    }
}