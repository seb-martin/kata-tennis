package fr.sebmartin.kata.tennis.domain;

import org.junit.Before;
import org.junit.Test;

import static fr.sebmartin.kata.tennis.domain.GameScore.*;
import static fr.sebmartin.kata.tennis.domain.Player.PLAYER_ONE;
import static fr.sebmartin.kata.tennis.domain.Player.PLAYER_TWO;
import static org.junit.Assert.*;

public class TennisMatchTest {

    private TennisMatch matchService;

    @Before
    public void setUp() {
        matchService = new TennisMatch();
    }

    @Test
    public void sprint1() throws GameOverException {

        // Start the game: 0 - 0
        assertGameScore(LOVE, LOVE);
        assertGameNotWon();

        // Player 1 wins 1 point : 15 - 0
        matchService.mark(PLAYER_ONE);
        assertGameScore(FIFTEEN, LOVE);
        assertGameNotWon();

        // Player 1 wins 1 point : 30 - 0
        matchService.mark(PLAYER_ONE);
        assertGameScore(THIRTY, LOVE);
        assertGameNotWon();

        // Player 2 wins 1 point : 30 - 15
        matchService.mark(PLAYER_TWO);
        assertGameScore(THIRTY, FIFTEEN);
        assertGameNotWon();

        // Player 1 wins 1 point : 40 - 15
        matchService.mark(PLAYER_ONE);
        assertGameScore(FORTY, FIFTEEN);
        assertGameNotWon();

        // Player 2 wins 1 point : 40 - 30
        matchService.mark(PLAYER_TWO);
        assertGameScore(FORTY, THIRTY);
        assertGameNotWon();

        // Player 2 wins 1 point : Deuce - Deuce
        matchService.mark(PLAYER_TWO);
        assertGameScore(DEUCE, DEUCE);
        assertEquals(DEUCE, matchService.game().scores().get(PLAYER_ONE));
        assertEquals(DEUCE, matchService.game().scores().get(PLAYER_TWO));
        assertGameNotWon();

        // Player 2 wins 1 point : 40 - Advantage
        matchService.mark(PLAYER_TWO);
        assertGameScore(FORTY, ADVANTAGE);
        assertGameNotWon();

        // Player 1 wins 1 point : Deuce - Deuce
        matchService.mark(PLAYER_ONE);
        assertGameScore(DEUCE, DEUCE);
        assertGameNotWon();

        // Player 1 wins 1 point : Advantage - 40
        matchService.mark(PLAYER_ONE);
        assertGameScore(ADVANTAGE, FORTY);
        assertGameNotWon();

        // Player 1 wins 1 point : 0 - 0 ; Player 1 win the game
        matchService.mark(PLAYER_ONE);
        assertGameScore(LOVE, LOVE);
        assertGameWon(PLAYER_ONE);

    }

    @Test
    public void sprint2_us1() throws GameOverException {
        // Start the game & the set : 0 - 0 | 0 - 0
        assertGameScore(LOVE, LOVE);
        assertSetScores(0, 0);
        assertGameNotWon();
        assertSetNotWon();

        // Player 1 wins 1 point : 15 - 0 | 0 - 0
        matchService.mark(PLAYER_ONE);
        assertGameScore(FIFTEEN, LOVE);
        assertSetScores(0, 0);
        assertGameNotWon();
        assertSetNotWon();

        // Player 1 wins 1 point : 30 - 0 | 0 - 0
        matchService.mark(PLAYER_ONE);
        assertGameScore(THIRTY, LOVE);
        assertSetScores(0, 0);
        assertGameNotWon();
        assertSetNotWon();

        // Player 2 wins 1 point : 30 - 15 | 0 - 0
        matchService.mark(PLAYER_TWO);
        assertGameScore(THIRTY, FIFTEEN);
        assertSetScores(0, 0);
        assertGameNotWon();
        assertSetNotWon();

        // Player 1 wins 1 point : 40 - 15 | 0 - 0
        matchService.mark(PLAYER_ONE);
        assertGameScore(FORTY, FIFTEEN);
        assertSetScores(0, 0);
        assertGameNotWon();
        assertSetNotWon();

        // Player 1 wins 1 point : 0 - 0 | 1 - 0
        matchService.mark(PLAYER_ONE);
        assertGameScore(LOVE, LOVE);
        assertSetScores(1, 0);
        assertGameWon(PLAYER_ONE);
        assertSetNotWon();

        // Player 2 wins 1 game : 0 - 0 | 1 - 1
        winGame(PLAYER_TWO);
        assertGameScore(LOVE, LOVE);
        assertSetScores(1, 1);
        assertGameWon(PLAYER_TWO);
        assertSetNotWon();

        // Player 2 wins 1 game : 0 - 0 | 1 - 2
        winGame(PLAYER_TWO);
        assertGameScore(LOVE, LOVE);
        assertSetScores(1, 2);
        assertGameWon(PLAYER_TWO);
        assertSetNotWon();

        // Player 2 wins 1 game : 0 - 0 | 1 - 3
        winGame(PLAYER_TWO);
        assertGameScore(LOVE, LOVE);
        assertSetScores(1, 3);
        assertGameWon(PLAYER_TWO);
        assertSetNotWon();

        // Player 2 wins 1 game : 0 - 0 | 1 - 4
        winGame(PLAYER_TWO);
        assertGameScore(LOVE, LOVE);
        assertSetScores(1, 4);
        assertGameWon(PLAYER_TWO);
        assertSetNotWon();

        // Player 2 wins 1 game : 0 - 0 | 1 - 5
        winGame(PLAYER_TWO);
        assertGameScore(LOVE, LOVE);
        assertSetScores(1, 5);
        assertGameWon(PLAYER_TWO);
        assertSetNotWon();

        // Player 2 wins 1 game : 0 - 0 | 1 - 6
        winGame(PLAYER_TWO);
        assertGameScore(LOVE, LOVE);
        assertSetScores(1, 6);
        assertGameWon(PLAYER_TWO);
        assertSetWon(PLAYER_TWO);
    }

    private void winGame(Player player) throws GameOverException {
        matchService.mark(player);
        matchService.mark(player);
        matchService.mark(player);
        matchService.mark(player);
    }


    private void assertGameScore(GameScore playerOneGameScore, GameScore playerTwoGameScore) {
        assertEquals(playerOneGameScore, matchService.game().scores().get(PLAYER_ONE));
        assertEquals(playerTwoGameScore, matchService.game().scores().get(PLAYER_TWO));
    }

    private void assertSetScores(int playerOneSetScore, int playerTwoSetScore) {
        assertEquals(playerOneSetScore, matchService.set().scores().get(PLAYER_ONE).intValue());
        assertEquals(playerTwoSetScore, matchService.set().scores().get(PLAYER_TWO).intValue());
    }

    private void assertGameNotWon() {
        assertFalse(matchService.game().winner().isPresent());
    }

    private void assertGameWon(Player player) {
        assertTrue(matchService.game().winner().isPresent());
        assertEquals(player, matchService.game().winner().get());
    }

    private void assertSetNotWon() {
        assertFalse(matchService.set().winner().isPresent());
    }

    private void assertSetWon(Player player) {
        assertTrue(matchService.set().winner().isPresent());
        assertEquals(player, matchService.set().winner().get());
    }

}