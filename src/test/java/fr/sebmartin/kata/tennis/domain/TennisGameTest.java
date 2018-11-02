package fr.sebmartin.kata.tennis.domain;

import org.junit.Test;

import java.util.EnumMap;
import java.util.Map;

import static fr.sebmartin.kata.tennis.domain.GameScore.*;
import static fr.sebmartin.kata.tennis.domain.Player.PLAYER_ONE;
import static fr.sebmartin.kata.tennis.domain.Player.PLAYER_TWO;
import static org.junit.Assert.*;

public class TennisGameTest {

    @Test
    public void gameStartsWithScoreOfLoveForEachPlayer() {

        TennisGame game = new TennisGame();

        assertScores(game, LOVE, LOVE);
        assertGameNotWon(game);
    }

    @Test
    public void gameScoreChangesFromLoveToFifteen() throws GameOverException {

        TennisGame game = buildGame(LOVE, LOVE,null);

        game.mark(PLAYER_ONE);

        assertScores(game, FIFTEEN, LOVE);
        assertGameNotWon(game);
    }

    @Test
    public void gameScoreChangesFromFifteenToThirty() throws GameOverException {

        TennisGame game = buildGame(FIFTEEN, LOVE,null);

        game.mark(PLAYER_ONE);

        assertScores(game, THIRTY, LOVE);
        assertGameNotWon(game);
    }

    @Test
    public void gameScoreChangesFromThirtyToForty() throws GameOverException {

        TennisGame game = buildGame(GameScore.THIRTY, LOVE,null);

        game.mark(PLAYER_ONE);

        assertScores(game, FORTY, LOVE);
        assertGameNotWon(game);
    }

    @Test
    public void gameScoreChangesFromFortyToWinGame() throws GameOverException {

        TennisGame game = buildGame(GameScore.FORTY, LOVE,null);

        game.mark(PLAYER_ONE);

        assertScores(game, LOVE, LOVE);
        assertGameWon(game, PLAYER_ONE);
    }

    @Test(expected = GameOverException.class)
    public void shouldThrowsAnExceptionWhenGameIsOver() throws GameOverException {

        TennisGame game = buildGame(LOVE, LOVE, PLAYER_ONE);

        game.mark(PLAYER_ONE);
    }

    @Test
    public void ifTheTwoPlayersReachTheScoreOfFortyTheDeuceRuleIsActivated() throws GameOverException {

        TennisGame game = buildGame(FORTY, THIRTY, null);

        game.mark(PLAYER_TWO);

        assertScores(game, DEUCE, DEUCE);
        assertGameNotWon(game);
    }

    @Test
    public void ifTheScoreIsDeuceThePlayerWhoWinThePointTakeTheAdvantage() throws GameOverException {

        TennisGame game = buildGame(DEUCE, DEUCE, null);

        game.mark(PLAYER_ONE);

        assertScores(game, ADVANTAGE, FORTY);
        assertGameNotWon(game);
    }

    @Test
    public void ifThePlayerWhoHasTheAdvantageWinThePointHeWinTheGame() throws GameOverException {

        TennisGame game = buildGame(ADVANTAGE, FORTY, null);

        game.mark(PLAYER_ONE);

        assertScores(game, LOVE, LOVE);
        assertGameWon(game, PLAYER_ONE);
    }

    @Test
    public void ifThePlayerWhoHasTheAdvantageLoosesThePointTheScoreIsDeuce() throws GameOverException {

        TennisGame game = buildGame(ADVANTAGE, FORTY, null);

        game.mark(PLAYER_TWO);

        assertScores(game, DEUCE, DEUCE);
        assertGameNotWon(game);
    }

    private void assertScores(TennisGame game, GameScore player1Score, GameScore player2Score) {
        assertEquals(player1Score, game.scores().get(PLAYER_ONE));
        assertEquals(player2Score, game.scores().get(PLAYER_TWO));
    }

    private void assertGameNotWon(TennisGame game) {
        assertFalse(game.winner().isPresent());
    }

    private void assertGameWon(TennisGame game, Player winner) {
        assertTrue(game.winner().isPresent());
        assertEquals(winner, game.winner().get());
    }

    private TennisGame buildGame(GameScore playerOneScore, GameScore playerTwoScore, Player winner) {
        return new TennisGame(buildScores(playerOneScore, playerTwoScore), winner);
    }

    private Map<Player, GameScore> buildScores(GameScore playerOneScore, GameScore playerTwoScore) {
        Map<Player, GameScore> scores = new EnumMap<>(Player.class);
        scores.put(PLAYER_ONE, playerOneScore);
        scores.put(PLAYER_TWO, playerTwoScore);
        return scores;
    }
}