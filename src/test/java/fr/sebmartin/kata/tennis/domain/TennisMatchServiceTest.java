package fr.sebmartin.kata.tennis.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TennisMatchServiceTest {

    private TennisMatchService matchService;

    @Before
    public void setUp() {
        matchService = new TennisMatchService();
    }

    @Test
    public void sprint1() throws GameOverException {

        // Start the game: 0 - 0
        assertEquals(GameScore.LOVE, matchService.game().scores().get(Player.PLAYER_ONE));
        assertEquals(GameScore.LOVE, matchService.game().scores().get(Player.PLAYER_TWO));
        assertFalse(matchService.game().winner().isPresent());

        // Player 1 wins 1 point : 15 - 0
        matchService.mark(Player.PLAYER_ONE);
        assertEquals(GameScore.FIFTEEN, matchService.game().scores().get(Player.PLAYER_ONE));
        assertEquals(GameScore.LOVE, matchService.game().scores().get(Player.PLAYER_TWO));
        assertFalse(matchService.game().winner().isPresent());

        // Player 1 wins 1 point : 30 - 0
        matchService.mark(Player.PLAYER_ONE);
        assertEquals(GameScore.THIRTY, matchService.game().scores().get(Player.PLAYER_ONE));
        assertEquals(GameScore.LOVE, matchService.game().scores().get(Player.PLAYER_TWO));
        assertFalse(matchService.game().winner().isPresent());

        // Player 2 wins 1 point : 30 - 15
        matchService.mark(Player.PLAYER_TWO);
        assertEquals(GameScore.THIRTY, matchService.game().scores().get(Player.PLAYER_ONE));
        assertEquals(GameScore.FIFTEEN, matchService.game().scores().get(Player.PLAYER_TWO));
        assertFalse(matchService.game().winner().isPresent());

        // Player 1 wins 1 point : 40 - 15
        matchService.mark(Player.PLAYER_ONE);
        assertEquals(GameScore.FORTY, matchService.game().scores().get(Player.PLAYER_ONE));
        assertEquals(GameScore.FIFTEEN, matchService.game().scores().get(Player.PLAYER_TWO));
        assertFalse(matchService.game().winner().isPresent());

        // Player 2 wins 1 point : 40 - 30
        matchService.mark(Player.PLAYER_TWO);
        assertEquals(GameScore.FORTY, matchService.game().scores().get(Player.PLAYER_ONE));
        assertEquals(GameScore.THIRTY, matchService.game().scores().get(Player.PLAYER_TWO));
        assertFalse(matchService.game().winner().isPresent());

        // Player 2 wins 1 point : Deuce - Deuce
        matchService.mark(Player.PLAYER_TWO);
        assertEquals(GameScore.DEUCE, matchService.game().scores().get(Player.PLAYER_ONE));
        assertEquals(GameScore.DEUCE, matchService.game().scores().get(Player.PLAYER_TWO));
        assertFalse(matchService.game().winner().isPresent());

        // Player 2 wins 1 point : 40 - Advantage
        matchService.mark(Player.PLAYER_TWO);
        assertEquals(GameScore.FORTY, matchService.game().scores().get(Player.PLAYER_ONE));
        assertEquals(GameScore.ADVANTAGE, matchService.game().scores().get(Player.PLAYER_TWO));
        assertFalse(matchService.game().winner().isPresent());

        // Player 1 wins 1 point : Deuce - Deuce
        matchService.mark(Player.PLAYER_ONE);
        assertEquals(GameScore.DEUCE, matchService.game().scores().get(Player.PLAYER_ONE));
        assertEquals(GameScore.DEUCE, matchService.game().scores().get(Player.PLAYER_TWO));
        assertFalse(matchService.game().winner().isPresent());

        // Player 1 wins 1 point : Advantage - 40
        matchService.mark(Player.PLAYER_ONE);
        assertEquals(GameScore.ADVANTAGE, matchService.game().scores().get(Player.PLAYER_ONE));
        assertEquals(GameScore.FORTY, matchService.game().scores().get(Player.PLAYER_TWO));
        assertFalse(matchService.game().winner().isPresent());

        // Player 1 wins 1 point : 0 - 0 ; Player 1 win the game
        matchService.mark(Player.PLAYER_ONE);
        assertEquals(GameScore.LOVE, matchService.game().scores().get(Player.PLAYER_ONE));
        assertEquals(GameScore.LOVE, matchService.game().scores().get(Player.PLAYER_TWO));
        assertTrue(matchService.game().winner().isPresent());
        assertEquals(Player.PLAYER_ONE, matchService.game().winner().get());

    }
}