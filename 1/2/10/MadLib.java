/*
 * 
 * 
 * 
 */

import java.util.Scanner;

public class MadLib{
    static class Template{
        String inner;

        public Template(String inner) {
            this.inner = inner;
        }
        
        // actually not a lot of boilerplate
        static record Tuple<A,B>(A a,B b){
            public A a(){
                return this.a;
            }
            public B b(){
                return this.b;
            }
        }
        // parses a tag from the stream at idx, and return the user defined value.
        // BUT i also need to return how long the tag was, which is difficult because there are no tuples. 
        // and i dont wanna include any ext. libraries. i dont wanna implement a whole class for this. 
        // might be easier just to inline this logic
        // idx when passed in refers to the start of the tag, < 
        Tuple<String,Integer> resolveTag(Scanner stdin,int idx){
            // let
                StringBuilder buf = new StringBuilder(); // return 
                boolean slashSwitch = false;
            // in
            outer: while (true){
                idx++; // moves head forward.
                if (idx>=this.inner.length()){
                    System.err.printf("improperly closed tag: %s\n",buf);
                    break; 
                }
                char currentChar = this.inner.charAt(idx);
                if (slashSwitch){ // if slash ignore and append
                    buf.append(currentChar);
                    slashSwitch = false; // disable switch.
                    continue;
                }
                switch (currentChar){
                    case '\\' -> {
                        slashSwitch = true; // enable switch.
                        continue outer; // i put these here initially because C needs them iirc.
                        }
                    case '>' -> {
                        // System.out.println("found >");
                        break outer;
                    }
                    default -> {
                        buf.append(currentChar);
                        // continue outer;
                        }
                }
                    // case boolean v when (slashSwitch) -> {buf.append(currentChar);continue;}; // idk
                                }
            // buf now contains a full tag.
            System.out.printf("needs a(n) %s: ",buf);
            System.out.flush();
            String userInput = stdin.nextLine();
            return new Tuple<>(userInput,idx);
        }
        // evalutes the template interactively
        String evaluate(Scanner stdin){
            // initializes a new StringBuilder buffer
            // crawls over this.inner, copying each character to the buffer until it reaches a < , it will inore a < if there is a \ before it. \<
            // once it locates the <, it will pass this.inner + ptr idx to '<'. to the enum which will parse the <tag> into a valid enum. returning the Enum
            // then it prompts the user for the value corresponding to the enum. it then appends their response to the builder and continues building the string.
            // at the end return stringbuilder as a string. 
            // or could use a Stream<Char>
            // actually enum seems a bit pointless.
            

            // let
                StringBuilder buf = new StringBuilder();
                int idx = -1; // -1 because the loop is increment first.
                boolean slashSwitch = false;
            // in
            while (true){
                idx++;
                // System.out.println(buf);
                if (idx>=this.inner.length()){
                    break; 
                }
                char currentChar = this.inner.charAt(idx);
                // System.out.println(currentChar);
                if (slashSwitch){
                    buf.append(currentChar);
                    slashSwitch = false;
                    continue;
                }
                switch (currentChar){
                    case '\\' -> {
                        slashSwitch = true;
                        // continue;
                        }
                    case '<' -> {
                        var resolveTagResult = resolveTag(stdin, idx);
                        // String userInput = parseTagResult.a();
                        idx=resolveTagResult.b();
                        buf.append(resolveTagResult.a());
                        }
                    default -> {
                        buf.append(currentChar);
                    }
                }
            }
            return buf.toString();
        }
    }

        public static void main(String[] args) {
            Template template1 = new Template("This weekend I am going camping with <Proper Noun (Person’s Name)>. I packed my lantern, sleeping bag, and\n" + //
                                "<Noun>. I am so <Adjective (Feeling)> to <Verb> in a tent. I am <Adjective (Feeling)> we\n" + //
                                "might see a <Animal>, they are kind of dangerous. We are going to hike, fish, and <Verb>.\n" + //
                                "I have heard that the <Color> lake is great for <Verb (ending in ing)>. Then we will\n" + //
                                "<Adverb (ending in ly)> hike through the forest for <Number> <Measure of Time>. If I see a\n" + //
                                "<Color> <Animal> while hiking, I am going to bring it home as a pet! At night we will tell\n" + //
                                "<Number> <Silly Word> stories and roast <Noun> around the campfire!!");

            System.out.println(template1.evaluate(new Scanner(System.in)));
        }
}

