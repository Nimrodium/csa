import java.util.ArrayList;
public class NameParser {
    static void parse(String fullName){
        ArrayList<String> parts = new ArrayList<>(); 
        char space = ' ';
        int idx = 0;
        // kinda reminds me of a linked list. also loop could be restructred as recursive function very easily.
        while (true){ // ArrayList<String> recursive(ArrayList<String> parts,int idx);
            int nextIdx = fullName.indexOf(space,idx+1);  // finds next instance of ' ' after the current.

            if (nextIdx == -1) {
                parts.add(fullName.substring(idx+1)); // gets string from last occurance of ' ' to EOS. exit branch.
                break; // return parts;
            }else{
                 parts.add(fullName.substring(idx+1,nextIdx)); // gets string between two ' '
                 idx=nextIdx; // sets next as current  // recursive(parts,nextIdx)
            }
        }
        // not sure if it wants from me when it says "labels", variables? or just printing, ill do printing,
        // but i know how to do the other ofc.
        // int sum = parts[0].length()+parts[1].length()+parts[2].length();

        
        // [String] -> [Integer] -> [int] -> int
        int sum = parts.stream().map( (a) -> a.length() ).mapToInt( (a) -> a ).sum();
         // if i had to make this super extensible i wouldve done it differently. but this is fine.
        if (parts.size() == 2 ){ // if no middle
       
           System.out.printf("first: %s\nlast: %s\ntotal chars: %d\n",parts.get(0),parts.get(1),sum);
        }else{
            
             System.out.printf("first: %s\nmiddle: %s\nlast: %s\ntotal chars: %d\n",parts.get(0),parts.get(1),parts.get(2),sum);
            
        }
    }
    public static void main(String[] args) {
        // with middle
        parse("David Daniel Debunki");
        System.out.println();
        // without middle
        parse("Emmanuel dubois");
    }
}

