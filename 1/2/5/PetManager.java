/*

    1/2/5/PetManager.java
    this program demonstrates writing a class,
    instantiating an object, storing variables in the object,
    and displaying them through the use of getters

*/

class PetManager {

    static class Pet {

        String name;
        int age;
        double weight;
        boolean isVaccinated;

        Pet(String name, int age, double weight, boolean isVaccinated) {
            this.name = name;
            this.age = age;
            this.weight = weight;
            this.isVaccinated = isVaccinated;
        }

        // returns the pets name
        String getName() {
            return this.name;
        }

        // returns the pets age
        int getAge() {
            return this.age;
        }

        // returns the weight of the pet
        double getWeight() {
            return this.weight;
        }

        // returns a boolean of if the pet is vaccinated or not
        boolean isVaccinated() {
            return this.isVaccinated;
        }

        // displays this info (toml cuz why not)
        void displayPetProfile() {
            System.out.printf(
                "[%s]\nage=%d\nweight=%.2f\nisVaccinated=%b\n",
                this.name,
                this.age,
                this.weight,
                this.isVaccinated
            );
        }
    }

    public static void main(String[] args) {
        Pet pet = new Pet("louis", 2, 20.0, true);
        pet.displayPetProfile();
    }
}
