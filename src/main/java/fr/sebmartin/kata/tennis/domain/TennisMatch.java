package fr.sebmartin.kata.tennis.domain;

import java.util.Objects;
import java.util.Optional;

public class TennisMatch {

    private TennisSet currentSet = new TennisSet();

    public TennisMatch() {
    }

    TennisMatch(TennisSet set) {
        this.currentSet = Objects.requireNonNull(set);
    }

    public void mark(Player player) throws GameOverException {
        this.currentSet.mark(player);
    }

    public TennisSet set() {
        return this.currentSet;
    }

    public TennisGame game() {
        return this.currentSet.game();
    }

    public Optional<TieBreak> tieBreak() {
        return currentSet.tieBreak();
    }

    public Optional<Player> winner() {
        return currentSet.winner();
    }

}
