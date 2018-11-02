package fr.sebmartin.kata.tennis.domain;

import java.util.*;

public class TennisGame {

    private Map<Player, GameScore> scores = new EnumMap<>(Player.class);
    private Player winner;

    TennisGame() {
        Arrays.stream(Player.values()).forEach(p -> scores.put(p, GameScore.LOVE));
    }

    TennisGame(Map<Player, GameScore> scores, Player winner) {
        this.scores = Objects.requireNonNull(scores);
        this.winner = winner;
    }

    public Map<Player, GameScore> scores() {
        return Collections.unmodifiableMap(this.scores);
    }

    public Optional<Player> winner() {
        return Optional.ofNullable(winner);
    }

    void mark(Player player) throws GameOverException {

        checkGameNotOver();

        computeScoresAndWinnerStates(player);

    }

    private void computeScoresAndWinnerStates(Player player) {
        GameScore lastScore = scores.get(player);

        switch (lastScore) {
            case LOVE:
                playerScore(player, GameScore.FIFTEEN);
                break;
            case FIFTEEN:
                playerScore(player, GameScore.THIRTY);
                break;
            case THIRTY:
                if (testOponentScoreIsForty(player)) {
                    deuce();
                } else {
                    playerScore(player, GameScore.FORTY);
                }

                break;
            case FORTY:
                if (testOponentScoreIsAdvantage(player)) {
                    deuce();
                } else {
                    playerWin(player);
                }

                break;
            case DEUCE:
                playerAdvantage(player);
                break;
            case ADVANTAGE:
                playerWin(player);
                break;
        }
    }

    private boolean testOponentScoreIsForty(Player player) {
        return testCompetingScore(player, GameScore.FORTY);
    }

    private boolean testOponentScoreIsAdvantage(Player player) {
        return testCompetingScore(player, GameScore.ADVANTAGE);
    }

    private boolean testCompetingScore(Player player, GameScore advantage) {
        return scores.get(player.opponent()).equals(advantage);
    }

    private void deuce() {
        Arrays.stream(Player.values()).forEach(p -> playerScore(p, GameScore.DEUCE));
    }

    private void playerAdvantage(Player player) {
        playerScore(player, GameScore.ADVANTAGE);
        playerScore(player.opponent(), GameScore.FORTY);
    }

    private void playerWin(Player player) {
        Arrays.stream(Player.values()).forEach(p -> playerScore(p, GameScore.LOVE));
        winner = player;
    }

    private void playerScore(Player player, GameScore forty) {
        scores.put(player, forty);
    }

    private void checkGameNotOver() throws GameOverException {
        if (winner().isPresent()) {
            throw new GameOverException();
        }
    }
}
