package fr.sebmartin.kata.tennis.service;

import fr.sebmartin.kata.tennis.domain.GameOverException;
import fr.sebmartin.kata.tennis.domain.Player;
import fr.sebmartin.kata.tennis.domain.TennisMatch;

import java.util.Objects;

public class TennisService {

    private TennisDisplay display;
    private TennisMatch match;

    public TennisService(TennisMatch match) {
        this.match = Objects.requireNonNull(match);

        // Display stub
        this.display = (w, m) -> { };
    }

    public void register(TennisDisplay display) {
        this.display = Objects.requireNonNull(display);
    }

    public void mark(Player winner) throws GameOverException {

        this.match.mark(winner);

        this.display.playerWonPoint(winner, match);
    }
}
