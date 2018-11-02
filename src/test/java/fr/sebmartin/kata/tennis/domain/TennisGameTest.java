package fr.sebmartin.kata.tennis.domain;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class TennisGameTest {

    @Test
    public void gameStartsWithScoreOfLoveForEachPlayer() {

        TennisGame game = new TennisGame();

        assertTrue(
                Arrays.stream(Player.values())
                        .map(player -> game.scores().get(player))
                        .allMatch(gameScore -> gameScore.equals(GameScore.LOVE))
        );
    }

    @Test
    public void gameScoreChangesFromLoveToFifteen() throws GameOverException {

        TennisGame game = new TennisGame();

        game.mark(Player.PLAYER_ONE);

        assertEquals(GameScore.FIFTEEN, game.scores().get(Player.PLAYER_ONE));
    }

    @Test
    public void gameScoreChangesFromFifteenToThirty() throws GameOverException {

        TennisGame game = new TennisGame();
        game.mark(Player.PLAYER_ONE);

        game.mark(Player.PLAYER_ONE);

        assertEquals(GameScore.THIRTY, game.scores().get(Player.PLAYER_ONE));
    }

    @Test
    public void gameScoreChangesFromThirtyToForty() throws GameOverException {

        TennisGame game = new TennisGame();
        game.mark(Player.PLAYER_ONE);
        game.mark(Player.PLAYER_ONE);

        game.mark(Player.PLAYER_ONE);

        assertEquals(GameScore.FORTY, game.scores().get(Player.PLAYER_ONE));
    }

    @Test
    public void gameScoreChangesFromFortyToWinGame() throws GameOverException {

        TennisGame game = new TennisGame();
        game.mark(Player.PLAYER_ONE);
        game.mark(Player.PLAYER_ONE);
        game.mark(Player.PLAYER_ONE);

        game.mark(Player.PLAYER_ONE);

        assertTrue(game.winner().isPresent());
        assertEquals(Player.PLAYER_ONE, game.winner().get());
    }

    @Test(expected = GameOverException.class)
    public void shouldThrowsAnExceptionWhenGameIsOver() throws GameOverException {

        TennisGame game = new TennisGame();
        game.mark(Player.PLAYER_ONE);
        game.mark(Player.PLAYER_ONE);
        game.mark(Player.PLAYER_ONE);
        game.mark(Player.PLAYER_ONE);

        game.mark(Player.PLAYER_ONE);
    }
}