package game;
/*
class Phrase:
	- String phrase;
	- [char] guessedCharacters;
	
	+ new(String phrase) -> Phrase;
	+ render() -> String;
	+ guess(Guess g) -> bool;
	+ isSolved() -> bool;
*/

import java.util.ArrayList;

public class Phrase {
    private String phrase;
    private ArrayList<Character> guessedCharacters;

    public Phrase(String phrase){
        this.phrase = phrase;
    }
}
