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

// public class Board{
//     private String solvedPhrase;
//     private String phrase;
//     private int currentLetterValue;
//     public Board{
//         setCurrentLetterValue(0);
//         System.out.println("Phrase: " + phrase);
//     }
//     public void loadPhrase(String phrase){
//         this.solvedPhrase = phrase;
//     }
//     public void setCurrentLetterValue(int i){
//         this.currentLetterValue = i;
//     }
// }
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Phrase {

    private final String phrase;
    private final ArrayList<Character> guessedCharacters;
    private final Character subChar = '_';

    public Phrase(String phrase) {
        this.phrase = phrase;
        this.guessedCharacters = new ArrayList<>();
    }

    // uses a form of differed monadic-like enum here to inform the outerscope about the specific case.
    // IF fielded enums existed this would be
    // restructured to store that info in the field.
    public enum GuessResult {
        YES_PHRASE,
        NO_PHRASE,
        YES_CHAR,
        NO_CHAR,
    }

    public String render() {
        return this.phraseChars()
            .map(c -> this.isGuessed(c) ? c : this.subChar)
            .map(c -> c.toString())
            .collect(Collectors.joining());
    }

    private Stream<Character> phraseChars() {
        return this.phrase.chars().mapToObj(i ->
                (char) (int) i
        );
    }

    public String showFullPhrase() {
        return this.phrase;
    }

    private boolean isGuessed(Character c) {
        return this.guessedCharacters.contains(c);
        // && this.phrase.contains(String.valueOf(c));
    }

    public boolean isFullyGuessed() {
        // tests if all characters are guessed
        return this.phraseChars().allMatch(this::isGuessed);
    }

    public GuessResult guess(Guess g) {
        return switch (g.variant) {
            case CHARACTER -> {
                Character guessedCharacter = g.getCharacter();
                boolean isNotGuessed = !this.isGuessed(guessedCharacter);
                if (isNotGuessed) {
                    this.guessedCharacters.add(guessedCharacter);
                    yield GuessResult.YES_CHAR;
                } else {
                    System.out.printf(
                        "%s is not in the phrase!\n",
                        guessedCharacter
                    );
                    yield GuessResult.NO_CHAR;
                }
            }
            case PHRASE -> {
                String guessedPhrase = g.getCompletePhrase();
                boolean isPhrase = this.phrase.equals(guessedPhrase);
                if (!isPhrase) {
                    yield GuessResult.NO_PHRASE;
                } else {
                    yield GuessResult.YES_PHRASE;
                }
                // yield isPhrase;
            }
        };
    }
}
