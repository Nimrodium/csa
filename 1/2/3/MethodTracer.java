public class MethodTracer{
static class Calculator {
        /*
         * thanks to procedural abstraction the programmer need only call startCalculation, and the call chain will execute from there. 
         * this reduces verbosity and complexity of the main function by delegating implementation into deeper parts of the program. 
         * a program should look like a tree of increasing complexity and less abstraction. with the highest functions being most abstract.
         */
        // entry point, only function that needs to be called.
        void startCalculation(){
            System.out.println("Entering [startCalculation]");
            System.out.println("Starting calcuation...");
            this.performAddition();
        }
        // the programmer never needs to call this 
        void performAddition(){
            System.out.println("Entering [performAddition]");
            System.out.println("Adding 25 + 15 = 40");
            this.displayResult();
        }
        // or this due to procedural abstraction,
        void displayResult(){
            System.out.println("Entering [displayResult]");
            System.out.println("Calculation complete!");
        }
        @Override
        public String toString(){
            return "Calculator[ready]";
        }
    }
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        System.out.println(calc);
        // starts the call chain
        calc.startCalculation();
    }
}
