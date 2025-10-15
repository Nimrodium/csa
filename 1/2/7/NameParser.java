import java.util.Arrays;
public class NameParser {
    public static void main(String[] args) {
        String fullName = "David Daniel Debunki";
        String[] parts = new String[3]; 
        // int lastIdx = 0;
        char space = ' ';
        // int idx = fullName.indexOf(space); // finds next instance of ' '
        int idx = 0;
        int strIdx = 0;
        // kinda reminds me of a linked list. also loop could be restructred as recursive.
        while (true){
            int nextIdx = fullName.indexOf(space,idx+1);  // finds next instance of ' ' after the current.
            System.out.println(Arrays.toString(parts));
            System.out.printf("-- new iter --\ncurrent: %d\nnext: %d\nparts: %s\n",idx,nextIdx,Arrays.toString(parts));
            if (nextIdx == -1) {
                parts[strIdx] = fullName.substring(idx); // gets string from last occurance of ' ' to EOS. exit branch.
                break;
            }else{
               parts[strIdx] = fullName.substring(idx,nextIdx); // gets string between two ' '
                // System.out.println(fullName.substring(idx,nextIdx));
                strIdx++;
                idx=nextIdx; // sets next as current
            }
        }
        // not sure if it wants from me when it says "labels", variables? or just printing, ill do printing,
        // but i know how to do the other ofc.
        // will explode if not a full name assignment doesnt care though!.
        int sum = parts[0].length()+parts[1].length()+parts[2].length();
        String middleStr;
        if (parts.length()<3){
            middle = String.format("middle: %s",parts[1]);
        }else{
            middle = "";
        }
        System.out.printf("first: %s\nmiddle: %s\nlast: %s\ntotal chars: %d\n",parts[0],parts[1],parts[2],sum);
    }
}
