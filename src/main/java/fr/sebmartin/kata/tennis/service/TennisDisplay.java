package fr.sebmartin.kata.tennis.service;

import fr.sebmartin.kata.tennis.domain.Player;
import fr.sebmartin.kata.tennis.domain.TennisMatch;

public interface TennisDisplay {
    void playerWonPoint(Player pointWinner, TennisMatch matchState);
}
