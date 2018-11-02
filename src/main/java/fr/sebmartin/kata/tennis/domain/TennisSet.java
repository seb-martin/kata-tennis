package fr.sebmartin.kata.tennis.domain;

import java.util.*;

public class TennisSet {

    private Map<Player, Integer> scores = new EnumMap<>(Player.class);
    private TennisGame currentGame;
    private TieBreak tieBreak;
    private Player winner;

    TennisSet() {
        Arrays.stream(Player.values()).forEach(p -> scores.put(p, 0));
        currentGame = new TennisGame();
    }

    TennisSet(Map<Player, Integer> scores, TennisGame currentGame, TieBreak tieBreak, Player winner) {
        this.scores = scores;
        this.currentGame = currentGame;
        this.tieBreak = tieBreak;
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

    public Optional<TieBreak> tieBreak() {
        return Optional.ofNullable(tieBreak);
    }

    void mark(Player marker) throws GameOverException {

        checkSetNotOver();

        if (tieBreak().isPresent()) {

            markTieBreak(marker);

            computeScoresStateWhenPlayerMarkTieBreak(marker);

            computeWinnerStateWhenPlayerMarkTieBreak();

        } else {

            markGame(marker);

            computeScoresStateWhenPlayerMarkGame(marker);

            computeTieBreakState(marker);

            computeWinnerStateWhenPlayerMarkGame(marker);
        }

    }

    private void checkSetNotOver() throws GameOverException {
        if (winner().isPresent()) {
            throw new GameOverException();
        }
    }

    private void markGame(Player marker) throws GameOverException {
        if (currentGame.winner().isPresent()) {
            currentGame = new TennisGame();
        }

        currentGame.mark(marker);
    }

    private void markTieBreak(Player marker) throws GameOverException {
        tieBreak.mark(marker);
    }

    private void computeScoresStateWhenPlayerMarkGame(Player marker) {
        if (currentGame.winner().isPresent()) {
            scores.put(marker, scores.get(marker) + 1);
        }
    }

    private void computeScoresStateWhenPlayerMarkTieBreak(Player marker) {
        if (tieBreak.winner().isPresent()) {
            scores.put(marker, scores.get(marker) + 1);
        }
    }

    private void computeTieBreakState(Player player) {
        if (testPlayerReach6AndOtherPlayerHas6(player)) {
            tieBreak = new TieBreak();
        }
    }

    private void computeWinnerStateWhenPlayerMarkGame(Player player) {
        if (testPlayerReach6AndOtherPlayerHas4OrLower(player) || testPlayerReach7(player)) {
            winner = player;
        }
    }

    private void computeWinnerStateWhenPlayerMarkTieBreak() {
        if (tieBreak.winner().isPresent()) {
            winner = tieBreak.winner().get();
        }
    }

    private boolean testPlayerReach7(Player player) {
        return scores.get(player) == 7;
    }

    private boolean testPlayerReach6AndOtherPlayerHas4OrLower(Player player) {
        return scores.get(player) == 6 && scores.get(player.opponent()) <= 4;
    }

    private boolean testPlayerReach6AndOtherPlayerHas6(Player player) {
        return scores.get(player) == 6 && scores.get(player.opponent()) == 6;
    }

}
