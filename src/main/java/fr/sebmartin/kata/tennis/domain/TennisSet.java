package fr.sebmartin.kata.tennis.domain;

import java.util.*;

public class TennisSet {

    private Map<Player, Integer> scores = new EnumMap<>(Player.class);
    private TennisGame currentGame;
    private Player winner;

    TennisSet() {
        Arrays.stream(Player.values()).forEach(p -> scores.put(p, 0));
        currentGame = new TennisGame();
    }

    TennisSet(Map<Player, Integer> scores, TennisGame currentGame, Player winner) {
        this.scores = scores;
        this.currentGame = currentGame;
        this.winner = winner;
    }

    public Map<Player, Integer> scores() {
        return Collections.unmodifiableMap(scores);
    }

    public Optional<Player> winner() {
        return Optional.ofNullable(winner);
    }

    public TennisGame game() {
        return currentGame;
    }

    void mark(Player player) throws GameOverException {

        checkSetNotOver();

        markGame(player);

        computeScoresState(player);

        computeWinnerState(player);
    }

    private void checkSetNotOver() throws GameOverException {
        if (winner().isPresent()) {
            throw new GameOverException();
        }
    }

    private void markGame(Player player) throws GameOverException {
        if (currentGame.winner().isPresent()) {
            currentGame = new TennisGame();
        }

        currentGame.mark(player);
    }

    private void computeScoresState(Player player) {
        if (currentGame.winner().isPresent()) {
            scores.put(player, scores.get(player) + 1);
        }
    }

    private void computeWinnerState(Player player) {
        if (testPlayerReach6AndOtherPlayerHas4OrLower(player) || testPlayerReach7(player)) {
            winner = player;
        }
    }

    private boolean testPlayerReach7(Player player) {
        return scores.get(player) == 7;
    }

    private boolean testPlayerReach6AndOtherPlayerHas4OrLower(Player player) {
        return scores.get(player) == 6 && scores.get(player.competing()) <= 4;
    }

}
