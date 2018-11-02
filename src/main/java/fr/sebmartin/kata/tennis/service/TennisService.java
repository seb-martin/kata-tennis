package fr.sebmartin.kata.tennis.service;

import fr.sebmartin.kata.tennis.domain.GameOverException;
import fr.sebmartin.kata.tennis.domain.Player;
import fr.sebmartin.kata.tennis.domain.TennisMatch;

public class TennisService {

    private Display display;
    private TennisMatch match;

    public TennisService(Display display) {
        this.display = display;
        this.match = new TennisMatch();
    }

    public void mark(Player winner) throws GameOverException {

        this.display.playerWonPoint(winner);

        this.match.mark(winner);

        this.display.matchUpdated(match);
    }

    public TennisMatch match() {
        return match;
    }
}
