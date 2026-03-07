package game;

import java.util.ArrayList;

/*
class Game:
	- Phrase phrase;
	- [String] phrases;
	- [Player] players;

	+ new() -> Game;
	+ loop() -> ();
	- step() -> ();
	- prompt() -> Guess;
	- newPhrase() -> Phrase;
	- topPlayer() -> Player;
*/
// public class PhraseSolver{
// 	private Player player1;
// 	private Player player2;
// 	private Board board;
// 	private boolean solved;
// 	public PhraseSolver(){
// 		player1=new Player();
// 		player2=new Player();
// 		board=new Board();
// 		solved=false;
// 	}
// }
public class Game {

    Phrase phrase;
    ArrayList<Player> players;

    public Game() {
        // random phrase has not yet been instructed to be implemented
        this.phrase = new Phrase("little louis");
        this.players = new ArrayList<>();
        var playerN = GetInput.getInt("how many players are playing?");
        for (int i = 0; i < playerN; i++) {
            this.players.add(
                new Player(
                    GetInput.get(
                        String.format("enter name for player %d", i + 1)
                    )
                )
            );
        }
    }

    enum StepResult {
        GAME_OVER_LOSE,
        GAME_OVER_WIN_CHAR,
        GAME_OVER_WIN_PHRASE,
        TURN_OVER,
    }

    private String showTopPlayer() {
        var top = this.topPlayer();
        if (top.getScore() > 0) {
            return String.format(
                "the player with the most points is %s with %s points",
                top.getName(),
                top.getScore()
            );
        } else return "No one won.";
    }

    public void loop() {
        int playerIdx = 0;
        gameLoop: while (true) {
            var player = this.players.get(playerIdx % this.players.size());
            var stepResult = this.step(player);
            System.out.println();
            switch (stepResult) {
                case TURN_OVER -> {
                }
                case GAME_OVER_LOSE -> {
                    System.out.printf(
                        "Sorry! thats not the phrase!! the correct phrase was \"%s\"\n %s",
                        this.phrase.showFullPhrase(),
                        this.showTopPlayer()
                    );
                    break gameLoop;
                }
                case GAME_OVER_WIN_CHAR -> {
                    System.out.printf(
                        "congrats! you have guessed the phrase! \"%s\"",
                        this.showTopPlayer()
                    );
                    break gameLoop;
                }
                case GAME_OVER_WIN_PHRASE -> {
                    System.out.printf(
                        "congrats! you have guessed the full phrase of \"%s\", you (%s) win!\n",
                        this.phrase.showFullPhrase(),
                        player.getName()
                    );
                    break gameLoop;
                }
            }
            playerIdx++;
        }
    }

    // true on game-over
    private StepResult step(Player player) {
        var guess = this.prompt(player);
        return switch (this.phrase.guess(guess)) {
            case YES_CHAR -> {
                player.incrementScore();

                if (this.phrase.isFullyGuessed()) {
                    yield StepResult.GAME_OVER_WIN_CHAR;
                } else {
                    yield StepResult.TURN_OVER;
                }
            }
            case NO_CHAR -> {
                yield StepResult.TURN_OVER;
            }
            case YES_PHRASE -> {
                player.addScore(10);
                yield StepResult.GAME_OVER_WIN_PHRASE;
            }
            case NO_PHRASE -> {
                yield StepResult.GAME_OVER_LOSE;
            }
        };
    }

    private Guess prompt(Player player) {
        System.out.println(
            String.format(
                "player: %s\nphrase: %s",
                player.getName(),
                this.phrase.render()
            )
        );
        return Guess.ask();
    }

    // private Phrase newPhrase() {
    //     return null;
    // }

    private Player topPlayer() {
        ArrayList<Player> sorted = new ArrayList<>(this.players);
        sorted.sort(null);
        return sorted.get(sorted.size() - 1);
    }
}
