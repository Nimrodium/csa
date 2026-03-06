package game;
/*
enum Guess:
	Character (char c);
	Phrase (String s); 
	+ enter() -> Guess;
	- new(String s) -> Guess;
	- new(char c) -> Guess;
*/

/* i designed the UML diagram thinking i could make a fielded enum, eg. 
enum Guess {
    Character(char c),
    Phrase(String s),
}
turns out thats only allowed for constant data. so now its this disgustingness.
its intended to be used in a switch block
switch (guess.variant){
    case Guess.Variant.CHARACTER -> ...;
    case Guess.Variant.PHRASE -> ...;
}
*/

public class Guess {
    enum Variant{
        CHARACTER,
        PHRASE,
    }
    private String guess;
    public Variant variant;
    public Guess ask(){
        String prompt = "guess a character or complete the phrase";
        return new Guess();
    }
    private Guess(String guess){
        this.guess = guess;
    }
    public char getCharacter() throws IllegalStateException{
        if (!this.variant.equals(Variant.CHARACTER)){
            throw new IllegalStateException(
                "Guess is not a character, variant must be matched before calling getCharacter"
            );
        }
        return this.guess.charAt(0);
    }
    public String getCompletePhrase() throws IllegalStateException {
        if (!this.variant.equals(Variant.PHRASE)){
            throw new IllegalStateException(
                "Guess is not a complete phrase, variant must be matched before calling getCompletePhrase"
            );
        }
        return this.guess;
    } 
}
