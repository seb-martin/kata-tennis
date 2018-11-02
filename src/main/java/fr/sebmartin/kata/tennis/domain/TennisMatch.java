package fr.sebmartin.kata.tennis.domain;

public class TennisMatch {

    private TennisSet currentSet = new TennisSet();

    public void mark(Player player) throws GameOverException {
        this.currentSet.mark(player);
    }

    public TennisSet set() {
        return this.currentSet;
    }

    public TennisGame game() {
        return this.currentSet.game();
    }

}
