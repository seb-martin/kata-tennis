package fr.sebmartin.kata.tennis.domain;

import java.util.*;

public class TieBreak {

    private Map<Player, Integer> scores = new EnumMap<>(Player.class);
    private Player winner;

    TieBreak() {
        Arrays.stream(Player.values()).forEach(player -> scores.put(player, 0));
    }

    TieBreak(Map<Player, Integer> scores, Player winner) {
        this.scores = scores;
        this.winner = winner;
    }

    public Map<Player, Integer> scores() {
        return Collections.unmodifiableMap(scores);
    }

    public Optional<Player> winner() {
        return Optional.ofNullable(winner);
    }

    void mark(Player marker) throws GameOverException {
        checkTieBreakNotOver();

        computeScoresState(marker);

        computeWinnerState(marker);
    }

    private void checkTieBreakNotOver() throws GameOverException {
        if (winner().isPresent()) {
            throw new GameOverException();
        }
    }

    private void computeScoresState(Player marker) {
        scores.put(marker, scores.get(marker) + 1);
    }

    private void computeWinnerState(Player marker) {
        if (testMarkerHasAtLeast7PointsAnd2PointsMoreThanOpponent(marker)) {
            Arrays.stream(Player.values()).forEach(player -> scores.put(player, 0));
            winner = marker;
        }
    }

    private boolean testMarkerHasAtLeast7PointsAnd2PointsMoreThanOpponent(Player marker) {
        int markerPoints = scores.get(marker);
        int opponentPoints = scores.get(marker.opponent());

        return markerPoints >= 7 && markerPoints - opponentPoints >= 2;
    }

}
