package fr.sebmartin.kata.tennis.domain;

public class TennisMatchService {

    private TennisGame currentGame = new TennisGame();

    public void mark(Player player) throws GameOverException {
        this.currentGame.mark(player);
    }

    public TennisGame game() {
        return this.currentGame;
    }

}
