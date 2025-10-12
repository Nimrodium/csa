// Removed incorrect import: Dog is a static nested class inside PetManager in this workspace.
/*
    Create a Dog object with the name "Luna"
    Include the following intentionally incorrect method calls in comments, then provide the corrected versions:
    myDog.walk("park", 20) - Fix the argument order
    myDog.setAge(2.5, 6) - Fix the data type mismatch
    myDog.eat(3, "treats", 1.5) - Fix the argument order to match method signature
    myDog.play(toy) - Fix the undefined variable reference
    For each correction, add a single-line comment explaining what was wrong and why your fix works
    Demonstrate the corrected method calls work by running them successfully

 */
public class MethodValidator {
    public static void main(String[] args) {
    PetManager.Dog myDog = new PetManager.Dog("Luna",0);
        // Incorrect: myDog.walk("park", 20);
        // fix: should be minutes then location.
        myDog.walk(20, "park");
        // Incorrect: myDog.setAge(2.5, 6);
        // fix: should be int values for years and months, not float.
        myDog.setAge(2, 6);
        // Incorrect: myDog.eat(3, "treats", 1.5);
        // fix: should be food, amount, timesPerDay in that order.
        myDog.eat("treats", 1.5, 3);
    }
}
