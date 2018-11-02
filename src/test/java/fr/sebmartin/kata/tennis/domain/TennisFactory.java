package fr.sebmartin.kata.tennis.domain;

import java.util.EnumMap;
import java.util.Map;

import static fr.sebmartin.kata.tennis.domain.Player.PLAYER_ONE;
import static fr.sebmartin.kata.tennis.domain.Player.PLAYER_TWO;

public class TennisFactory {

    private TennisFactory() {
    }

    static TennisMatch buildMatch(TennisSet set) {
        return new TennisMatch(set);
    }

    static TennisSet buildSet(int playerOneSetScore, int playerTwoSetScore, Player winner, TennisGame game) {

        return buildSet(playerOneSetScore, playerTwoSetScore, winner, game, null);
    }

    static TennisSet buildSet(int playerOneScore, int playerTwoScore, Player winner, TennisGame game, TieBreak tieBreak) {
        Map<Player, Integer> scores = new EnumMap<>(Player.class);
        scores.put(PLAYER_ONE, playerOneScore);
        scores.put(PLAYER_TWO, playerTwoScore);

        return new TennisSet(scores, game, tieBreak, winner);
    }

    static TennisGame buildGame(GameScore playerOneScore, GameScore playerTwoScore, Player winner) {
        Map<Player, GameScore> scores = new EnumMap<>(Player.class);
        scores.put(PLAYER_ONE, playerOneScore);
        scores.put(PLAYER_TWO, playerTwoScore);

        return new TennisGame(scores, winner);
    }

    static TieBreak buildTieBreak(int playerOneScore, int playerTwoScore, Player winner) {
        Map<Player, Integer> scores = new EnumMap<>(Player.class);
        scores.put(PLAYER_ONE, playerOneScore);
        scores.put(PLAYER_TWO, playerTwoScore);

        return new TieBreak(scores, winner);
    }

}
