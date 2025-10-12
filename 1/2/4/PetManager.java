public class PetManager {

    static class Dog{
        /*
            Create a Dog object with the name "Buddy"
            Call the following methods to simulate a complete day with your dog:
            Feed your dog "chicken" using the single-parameter eat method
            Feed your dog "kibble", 2.5 cups, 3 times per day using the three-parameter eat method
            Set your dog's age to 4 years using the single-parameter setAge method
            Set your dog's age to 3 years and 8 months using the two-parameter setAge method
            Take your dog for a 45-minute walk using the single-parameter walk method
            Take your dog for a 30-minute walk to "Central Park" using the two-parameter walk method
            Call the sit() and speak() methods to show basic commands
            Add meaningful comments explaining which version of each overloaded method you're calling
            Use variables instead of literals for at least two of your method calls
         */

        String name;
        int ageYears;

        boolean isAlive;
        // Constructor
        Dog(String name,int age){
            this.name = name;
            
            this.isAlive = true;
        }
        // single parameter eat method
        void eat(String food){
            System.out.println(this.name + " is eating " + food + ".");
        }
        // three parameter eat method
        void eat(String food, double amount, int timesPerDay){
                System.out.println(this.name + " is eating " + amount + " cups of " + food + ", " + timesPerDay + " times per day.");
        }
        // sets age
        void setAge(int years, int months){
                this.ageYears = years;
                System.out.println(this.name + " is now " + years + " years and " + months + " months old.");
        }
        // walks dog
        void walk(int minutes, String location){
            System.out.println(this.name + " is going for a " + minutes + "-minute walk to " + location + ".");
        }
        // tells dog to sit
        void sit(){
            System.out.println(this.name + " is sitting.");
        }
        // tells dog to speak
        void speak(){
            System.out.println(this.name + " says: Woof!");
        }
    }
    public static void main(String[] args) {
        // Create a Dog object with the name "Buddy"
        Dog buddy = new Dog("Buddy", 0);

        // Feed your dog "chicken" using the single-parameter eat method
        buddy.eat("chicken");

        // Feed your dog "kibble", 2.5 cups, 3 times per day using the three-parameter eat method
        double amount = 2.5;
        int timesPerDay = 3;
        buddy.eat("kibble", amount, timesPerDay);

        // Set your dog's age to 4 years using the single-parameter setAge method
        buddy.setAge(4, 0);

        // Set your dog's age to 3 years and 8 months using the two-parameter setAge method
        buddy.setAge(3, 8);

        // Take your dog for a 45-minute walk using the single-parameter walk method
        int walkDuration = 45;
        buddy.walk(walkDuration, "the neighborhood");

        // Take your dog for a 30-minute walk to "Central Park" using the two-parameter walk method
        buddy.walk(30, "Central Park");

        // Call the sit() and speak() methods to show basic commands
        buddy.sit();
        buddy.speak();
    }
}