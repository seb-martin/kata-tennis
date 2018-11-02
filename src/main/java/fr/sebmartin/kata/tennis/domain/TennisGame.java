package fr.sebmartin.kata.tennis.domain;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

public class TennisGame {

    private Map<Player, GameScore> scores = new EnumMap<>(Player.class);
    private Player winner;

    TennisGame() {
        Arrays.stream(Player.values()).forEach(p -> scores.put(p, GameScore.LOVE));
    }

    public Map<Player, GameScore> scores() {
        return this.scores;
    }

    public Optional<Player> winner() {
        return Optional.ofNullable(winner);
    }

    void mark(Player player) throws GameOverException {

        if (winner().isPresent()) {
            throw new GameOverException();
        }

        GameScore lastScore = scores.get(player);

        switch (lastScore) {
            case LOVE:
                scores.put(player, GameScore.FIFTEEN);
                break;
            case FIFTEEN:
                scores.put(player, GameScore.THIRTY);
                break;
            case THIRTY:
                scores.put(player, GameScore.FORTY);
                break;
            case FORTY:
                Arrays.stream(Player.values()).forEach(p -> scores.put(p, GameScore.LOVE));
                winner = player;
        }

    }
}
