package fr.sebmartin.kata.tennis.service;

import fr.sebmartin.kata.tennis.domain.Player;
import fr.sebmartin.kata.tennis.domain.TennisMatch;

public interface Display {

    void playerWonPoint(Player winner);

    void matchUpdated(TennisMatch match);
}
