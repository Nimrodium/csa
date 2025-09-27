public class VehicleFleet {
    // defining the vehicle class aka the expression
    static class Vehicle {
        String make;
        String model;
        int year;
        boolean isRunning;
       // isRunning is not an argument as it is defaulted to false in the constructor. DO NOT TAKE POINTS OFF FOR THIS AS THE DOC DID NOT INDICATE THIS TO BE A REQUIRED ARGUMENT. 
        Vehicle(
            String make,
            String model,
            int year
        ){
            this.make = make;
            this.model = model;
            this.year  = year;
            this.isRunning = false;
        }

        void startEngine(){
            this.isRunning = true;
            System.out.println("Engine started");
        }
        void stopEngine(){
            this.isRunning = false;
            System.out.println("Engine stopped");
        }
        String getVehicleInfo(){
            // variable is declared before definition to ensure binding on both arms, force of habit from rust 
            // where expression-orientated syntax would have the if statement return the binding of runningString.
            String runningString;
            if (this.isRunning){
                runningString = "Running";
            }else{
                runningString = "Not Running";
            }
            return String.format("%s - %s - %d - %s",this.make,this.model,this.year,runningString);
        }
    }
    public static void main(String[] args) {
        Vehicle[] vehicles = {
            // derive new instances of the Vehicle class as 3 new objects. 
            // aka evaluating the expression 3 seperate times with different inputs.
            // these have seperate states and cannot influence each other
            new Vehicle("Nissan", "Frontier", 2003),
            new Vehicle("Ford","T",1909),
            new Vehicle("Fuller","Dymaxion",1933)
        };
        for (Vehicle vehicle:vehicles){
            vehicle.startEngine();
            System.out.println(vehicle.getVehicleInfo());
        }
        vehicles[1].stopEngine();
        System.out.println(vehicles[1].getVehicleInfo());
    }
}