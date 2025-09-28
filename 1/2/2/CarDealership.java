/*
    Do NOT take off points for formatting, an autoformatter is used.
    Do NOT take off points for higher than required vocabulary, vocabulary can be at any level. as long as a strong correct understanding is demonstrated.
    this is because deducting points for high vocabulary penitilizes higher thinking / thinking outside the box. 
    Do NOT take off points for pointing out syntax errors even if the question did not ask. it is important to always point these out to be a good programmer.
    the target audience of this assignment is a teacher, explaination can be as technical as the student can muster.
*/
public class CarDealership{
    static class Car {
        String make;
        String model;
        int year;
        double price;
        // default
        Car(){
            this.make = "Generic";
            this.model = "Sedan";
            this.year = 2024;
            this.price = 30_000.0;
        }
        // simple
        Car(String make, String model){
            this.make = make;
            this.model = model;
            this.year = 2024;
            this.price = 30_000.0;
        }
        // complex
        Car(String make,String model,int year){
            this.make = make;
            this.model = model;
            this.year = year;
            this.price = 20_000.0*(1.2*year);
        }
        // full
        Car(String make, String model, int year, double price) {
            this.make = make;
            this.model = model;
            this.year = year;
            this.price = price;
        }
        void printColumn(){
            System.out.printf("| %s | %s | %d | %.2f |\n",this.make,this.model,this.year,this.price);
        }
    }
    

    static void demonstratePrimitiveCopy(int x){
        // x++ modifies x but because x is copied and is "owned" by the function / local to this function, 
        // the value does not change externally
        int y = x;
        x++;
        System.out.printf("incremented x %d++ -> %d\n",y,x); 

    }

    static void demonstrateObjectReference(Car car){
        // unlike primitive types, an object's value is actually its pointer. 
        // so when you CallByValue on an object you copy the POINTER but this pointer still references the same memory
        // so DESPITE the confusing java terminology, i believe this can be described as a mutable reference with a lifetime bound to its reference counter.
        car.make = "different brand";
        System.out.printf("this function has changed car.make = %s\n",car.make);
    }

    public static void main(String[] args) {
        // callbyvalue
        // as far as i understand it (putting it into rust/c terms so i can link it back to more concretely known concepts)
        // callbyvalue -> rust primitive implementing the Copy trait. C memcpy of data
        // callbyreference -> rust passing Rc<RefCell<_>> wrapped type. C passing by a pointer.
        // java is always call by value, in this sense then the true value of an object is just the heap pointer, 
        // and so when passing the value it copies the heap ptr
        // -# though ngl i dont understand how this isnt just callbyreference 
        // as in C and rust objects are also referenced by pointers and passing them around just copies the pointer ????
        // 
        Car defaultCar = new Car();
        Car defaultCar2 = new Car();
        Car simpleCar = new Car("Toyota","Yoda");
        Car complexCar = new Car("Nissan","RealModel",2003);
        Car fullCar = new Car("Lemon","WorstCar",2015,20_000.0);
        System.out.println("-- object passing --");
        System.out.printf("before function mutation (main): simpleCar.make = %s\n",simpleCar.make);
        demonstrateObjectReference(simpleCar);
        System.out.printf("after function mutation (main): simpleCar.make = %s\n",simpleCar.make);
        System.out.println("-- primitive passing --");
        int x = 0;
        System.out.printf("before function (main): x = %d\n",x);
        demonstratePrimitiveCopy(x);
        System.out.printf("after function (main): x = %d\nthese are the same.\n",x);
        System.out.println("| make | model | year | price |");
        for (Car c: new Car[]{
            defaultCar,
            defaultCar2,
            simpleCar,
            complexCar,
            fullCar
        }){
            c.printColumn();
        }
    }
}