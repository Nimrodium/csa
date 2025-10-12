public class MessageBuilder {

    // thought this was gonna be like, wrap an inner string and expose a .push() or smth but no. its literally just concat.

    public static void main(String[] args) {
        // init str
        String message = "Welcome";
        // mutable str concat
        message += " to Computer Science";
        // def new str literal
        String course = " AP CSA";
        // concat str and store result in new variable
        String finalMessage = message + course;
        // print final message to stdout
        System.out.println(finalMessage);
    }
}
