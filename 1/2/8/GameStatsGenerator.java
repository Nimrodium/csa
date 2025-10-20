/*
    generates a random character and assesses its balance
*/
public class GameStatsGenerator {

    static int randFrom(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1)); //https://stackoverflow.com/questions/363681/how-do-i-generate-random-integers-within-a-specific-range-in-java
    }

    static double randFrom(double min, double max) {
        return min + (max - min) * Math.random(); // https://stackoverflow.com/questions/3680637/generate-a-random-double-in-a-range
    }

    static class RandCharacter {

        int hp;
        int mana;
        double critHitP;
        double movementSpeed;

        RandCharacter() {
            this.hp = randFrom(85, 120);
            this.mana = randFrom(50, 100);
            this.critHitP = randFrom(5.0, 15.0);
            this.movementSpeed = randFrom(2.5, 8.75);
        }

        void assess() {
            double assessment =
                this.hp +
                this.mana +
                (this.critHitP * 10) +
                (movementSpeed * 5);
            // switch (assessment) {
            //     case Double x when x < 250 -> {};
            // }
            String s; // imagine String s = if ...;
            // i wanted to use switch case ... when ... -> ... but thats apparently preview.
            if (assessment > 250) {
                s = "Overpowered";
            } else if (assessment > 200) {
                s = "Balanced";
            } else {
                s = "Underpowered";
            }
            System.out.printf(
                "New Random Character is %s\nhp:%d\nmana:%d\ncritHit%%:%.2f\nmovementSpeed:%.2f\nassessment:%.2f",
                s,
                this.hp,
                this.mana,
                this.critHitP,
                this.movementSpeed,
                assessment
            );
        }
    }

    public static void main(String[] args) {
        new RandCharacter().assess();
    }
}
