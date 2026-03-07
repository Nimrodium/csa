package game;

/*
class Player:
	- int score;
	- String name;

	+ prompt() -> String;
	+ new(String name) -> Player;
	+ incScore() -> ();
	+ getScore() -> int;
	+ getName() -> String;
*/
public class Player implements Comparable<Player> {

    private int score;
    private String name;

    public Player() {
        this.score = 0;
        this.name = GetInput.get("enter name");
        System.out.printf("welcome %s!\n", this.name);
    }

    public Player(String name) {
        this.score = 0;
        this.name = name;
        System.out.printf("welcome %s!\n", this.name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addScore(int n) {
        this.score += n;
    }

    public void setScore(int n) {
        this.score = n;
    }

    public int getScore() {
        return this.score;
    }

    public String getName() {
        return this.name;
    }

    public String prompt() {
        return String.format("Player: %s", this.name);
    }

    public void incrementScore() {
        this.score++;
    }

    @Override
    public int compareTo(Player other) {
        return Integer.compare(this.score, other.score);
    }
}
