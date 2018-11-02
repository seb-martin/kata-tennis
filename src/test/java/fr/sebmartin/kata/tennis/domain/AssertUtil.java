package fr.sebmartin.kata.tennis.domain;

import fr.sebmartin.kata.tennis.domain.*;

import static fr.sebmartin.kata.tennis.domain.Player.PLAYER_ONE;
import static fr.sebmartin.kata.tennis.domain.Player.PLAYER_TWO;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AssertUtil {

    private AssertUtil() {
    }

    // Game Assertions

    public static void assertGameScores(TennisMatch match, GameScore playerOneScore, GameScore playerTwoScore) {
        assertGameScores(match.game(), playerOneScore, playerTwoScore);
    }

    public static void assertGameNotWon(TennisMatch match) {
        assertGameNotWon(match.game());
    }

    public static void assertGameWon(TennisMatch match, Player winner) {
        assertGameWon(match.game(), winner);
    }

    public static void assertGameScores(TennisSet set, GameScore playerOneScore, GameScore playerTwoScore) {
        assertGameScores(set.game(), playerOneScore, playerTwoScore);
    }

    public static void assertGameNotWon(TennisSet set) {
        assertGameNotWon(set.game());
    }

    public static void assertGameWon(TennisSet set, Player winner) {
        assertGameWon(set.game(), winner);
    }

    public static void assertGameScores(TennisGame game, GameScore playerOneScore, GameScore playerTwoScore) {
        assertEquals(playerOneScore, game.scores().get(PLAYER_ONE));
        assertEquals(playerTwoScore, game.scores().get(PLAYER_TWO));
    }

    public static void assertGameNotWon(TennisGame game) {
        assertFalse(game.winner().isPresent());
    }

    public static void assertGameWon(TennisGame game, Player winner) {
        assertTrue(game.winner().isPresent());
        assertEquals(winner, game.winner().get());
    }

    // Set Assertions

    public static void assertSetScores(TennisMatch match, int playerOneScore, int playerTwoScore) {
        assertSetScores(match.set(), playerOneScore, playerTwoScore);
    }

    public static void assertSetNotWon(TennisMatch match) {
        assertSetNotWon(match.set());
    }

    public static void assertSetWon(TennisMatch match, Player winner) {
        assertSetWon(match.set(), winner);
    }

    public static void assertSetScores(TennisSet set, int playerOneScore, int playerTwoScore) {
        assertEquals(playerOneScore, set.scores().get(PLAYER_ONE).intValue());
        assertEquals(playerTwoScore, set.scores().get(PLAYER_TWO).intValue());
    }

    public static void assertSetNotWon(TennisSet set) {
        assertFalse(set.winner().isPresent());
    }

    public static void assertSetWon(TennisSet set, Player winner) {
        assertTrue(set.winner().isPresent());
        assertEquals(winner, set.winner().get());
    }

    public static void assertNoTieBreak(TennisMatch match) {
        assertFalse(match.tieBreak().isPresent());
    }

    public static void assertTieBreak(TennisMatch match) {
        assertTrue(match.tieBreak().isPresent());
    }

    // Tie Break Assertions

    public static void assertTieBreakScores(TennisMatch match, int playerOneScore, int playerTwoScore) {
        assertTrue(match.set().tieBreak().isPresent());
        assertTieBreakScores(match.set().tieBreak().get(), playerOneScore, playerTwoScore);
    }

    public static void assertTieBreakNotWon(TennisMatch match) {
        assertTrue(match.set().tieBreak().isPresent());
        assertTieBreakNotWon(match.set().tieBreak().get());
    }

    public static void assertTieBreakWon(TennisMatch match, Player winner) {
        assertTrue(match.set().tieBreak().isPresent());
        assertTieBreakWon(match.set().tieBreak().get(), winner);
    }

    public static void assertTieBreakScores(TennisSet set, int playerOneScore, int playerTwoScore) {
        assertTrue(set.tieBreak().isPresent());
        assertTieBreakScores(set.tieBreak().get(), playerOneScore, playerTwoScore);
    }

    public static void assertTieBreakNotWon(TennisSet set) {
        assertTrue(set.tieBreak().isPresent());
        assertTieBreakNotWon(set.tieBreak().get());
    }

    public static void assertTieBreakWon(TennisSet set, Player winner) {
        assertTrue(set.tieBreak().isPresent());
        assertTieBreakWon(set.tieBreak().get(), winner);
    }

    public static void assertNoTieBreak(TennisSet set) {
        assertFalse(set.tieBreak().isPresent());
    }

    public static void assertTieBreak(TennisSet set) {
        assertTrue(set.tieBreak().isPresent());
    }

    public static void assertTieBreakScores(TieBreak tieBreak, int playerOneScore, int playerTwoScore) {
        assertEquals(playerOneScore, tieBreak.scores().get(PLAYER_ONE).intValue());
        assertEquals(playerTwoScore, tieBreak.scores().get(PLAYER_TWO).intValue());
    }

    public static void assertTieBreakNotWon(TieBreak tieBreak) {
        assertFalse(tieBreak.winner().isPresent());
    }

    public static void assertTieBreakWon(TieBreak tieBreak, Player winner) {
        assertTrue(tieBreak.winner().isPresent());
        assertEquals(winner, tieBreak.winner().get());
    }

    // Match Assertions

    public static void assertMatchNotWon(TennisMatch match) {
        assertFalse(match.winner().isPresent());
    }

    public static void assertMatchWon(TennisMatch match, Player winner) {
        assertTrue(match.winner().isPresent());
        assertEquals(winner, match.winner().get());
    }
}
