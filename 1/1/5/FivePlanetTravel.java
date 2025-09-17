public class FivePlanetTravel {

    // theplanets.org average distance from earth to the planets
    static int mercury = 56974146;
    static int venus = 25724767;
    static int mars = 48678219;
    static int jupiter = 390674710;
    static int saturn = 792248270;

    // speed of light and our speed
    static int c = 670616629;
    static int speed = c / 10;

    public static void main(String[] args) {
        /* your code here */
        int numPlanets = 0;
        double total = 0;
        for (Planet planet : new Planet[] {
            new Planet("mercury", mercury),
            new Planet("venus", venus),
            new Planet("mars", mars),
            new Planet("jupiter", jupiter),
            new Planet("saturn", saturn),
        }) {
            numPlanets++;
            double travelTime = planet.travelTime();
            total += travelTime;
            System.out.println(
                planet.name + " travel time: " + travelTime + " hours"
            );
        }
        int average = (int) ((total / numPlanets) + .5);
        System.out.println("average travel time: " + average + " hours");
    }

    static class Planet {

        String name;
        int distanceFromEarth;

        Planet(String name, int distanceFromEarth) {
            this.name = name;
            this.distanceFromEarth = distanceFromEarth;
        }

        double travelTime() {
            return (double) this.distanceFromEarth / FivePlanetTravel.speed;
        }

        int travelTimeInt() {
            return this.distanceFromEarth / FivePlanetTravel.speed;
        }
    }
}
