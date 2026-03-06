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
public class Player {
    private int score;
    private final String name;

    public String prompt(){
        return String.format("Player: %s",this.name);
    }

    public Player(String name){
        this.name = name;
    }

    public Player(){
        this.name = GetInput.get("enter name");
    }
    
    public void incrementScore(){
        this.score++;
    }
    public int getScore(){
        return this.score;
    }
    public String getName(){
        return this.name;
    }
}
