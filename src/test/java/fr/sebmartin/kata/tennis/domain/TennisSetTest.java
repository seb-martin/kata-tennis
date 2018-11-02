package fr.sebmartin.kata.tennis.domain;

import org.junit.Test;

import java.util.EnumMap;
import java.util.Map;

import static fr.sebmartin.kata.tennis.domain.GameScore.FORTY;
import static fr.sebmartin.kata.tennis.domain.GameScore.LOVE;
import static fr.sebmartin.kata.tennis.domain.Player.PLAYER_ONE;
import static fr.sebmartin.kata.tennis.domain.Player.PLAYER_TWO;
import static org.junit.Assert.*;

public class TennisSetTest {

    @Test
    public void theSetStartsWithAScoreOf0GameForEachPlayer() {
        TennisSet set = new TennisSet();

        assertSetScores(set, 0, 0);
        assertSetNotWon(set);
    }

    @Test
    public void ifPlayerWin1GameHisScoreIs1() throws GameOverException {
        TennisSet set = buildSet(0, 0, null, FORTY, LOVE,null);

        set.mark(PLAYER_ONE);

        assertSetScores(set, 1, 0);
        assertSetNotWon(set);
    }


    @Test
    public void ifPlayerWin2GamesHisScoreIs2() throws GameOverException {
        TennisSet set = buildSet(1, 0, null, FORTY, LOVE,null);

        set.mark(PLAYER_ONE);

        assertSetScores(set, 2, 0);
        assertSetNotWon(set);
    }

    @Test
    public void ifPlayerWin3GamesHisScoreIs3() throws GameOverException {
        TennisSet set = buildSet(2, 0, null, FORTY, LOVE,null);

        set.mark(PLAYER_ONE);

        assertSetScores(set, 3, 0);
        assertSetNotWon(set);
    }

    @Test
    public void ifPlayerWin4GamesHisScoreIs4() throws GameOverException {
        TennisSet set = buildSet(3, 0, null, FORTY, LOVE,null);

        set.mark(PLAYER_ONE);

        assertSetScores(set, 4, 0);
        assertSetNotWon(set);
    }

    @Test
    public void ifPlayerWin5GamesHisScoreIs5() throws GameOverException {

        TennisSet set = buildSet(4, 0, null, FORTY, LOVE,null);

        set.mark(PLAYER_ONE);

        assertSetScores(set, 5, 0);
        assertSetNotWon(set);
    }

    @Test
    public void ifPlayerReachTheSetScoreOf6AndTheOtherPlayerHasASetScoreOf4OrLowerThePlayerWinTheSet() throws GameOverException {

        final int expectedPlayerOneSetScore = 6;

        for (int playerTwoSetScore = 1; playerTwoSetScore <= 4; playerTwoSetScore++) {

            TennisSet set = buildSet(expectedPlayerOneSetScore - 1, playerTwoSetScore, null, FORTY, LOVE,null);

            set.mark(PLAYER_ONE);

            assertSetScores(set, expectedPlayerOneSetScore, playerTwoSetScore);
            assertSetWon(set, PLAYER_ONE);
        }

    }

    @Test
    public void ifPlayerReachTheSetScoreOf6AndTheOtherPlayerHasASetScoreOf5ANewGameMustBePlayed() throws GameOverException {

        final int expectedPlayerOneSetScore = 6;
        final int playerTwoSetScore = 5;

        TennisSet set = buildSet(expectedPlayerOneSetScore - 1, playerTwoSetScore, null, FORTY, LOVE,null);

        set.mark(PLAYER_ONE);

        assertSetScores(set, expectedPlayerOneSetScore, playerTwoSetScore);
        assertSetNotWon(set);
    }

    @Test
    public void ifPlayerReachTheSetScoreOf7ThePlayerWinTheSet() throws GameOverException {

        final int expectedPlayerOneSetScore = 7;
        final int playerTwoSetScore = 5;

        TennisSet set = buildSet(expectedPlayerOneSetScore - 1, playerTwoSetScore, null, FORTY, LOVE,null);

        set.mark(PLAYER_ONE);

        assertSetScores(set, expectedPlayerOneSetScore, playerTwoSetScore);
        assertSetWon(set, PLAYER_ONE);
    }

    @Test(expected = GameOverException.class)
    public void shouldThrowAnExceptionWhenGameIsOver() throws GameOverException {

        TennisSet set = buildSet(6, 0, PLAYER_ONE, LOVE, LOVE,PLAYER_ONE);

        set.mark(PLAYER_ONE);
    }

    private void assertSetScores(TennisSet set, int player1Score, int player2Score) {
        assertEquals(player1Score, set.scores().get(PLAYER_ONE).intValue());
        assertEquals(player2Score, set.scores().get(PLAYER_TWO).intValue());
    }

    private void assertSetNotWon(TennisSet set) {
        assertFalse(set.winner().isPresent());
    }

    private void assertSetWon(TennisSet set, Player winner) {
        assertTrue(set.winner().isPresent());
        assertEquals(winner, set.winner().get());
    }

    private TennisSet buildSet(int playerOneSetScore, int playerTwoSetScore, Player setWinner, GameScore playerOneGameScore, GameScore playerTwoGameScore, Player gameWinner) {
        return new TennisSet(
                buildSetScores(playerOneSetScore, playerTwoSetScore),
                buildGame(playerOneGameScore, playerTwoGameScore, gameWinner),
                setWinner
        );
    }

    private Map<Player, Integer> buildSetScores(int playerOneScore, int playerTwoScore) {
        Map<Player, Integer> scores = new EnumMap<>(Player.class);
        scores.put(PLAYER_ONE, playerOneScore);
        scores.put(PLAYER_TWO, playerTwoScore);
        return scores;
    }


    private TennisGame buildGame(GameScore playerOneGameScore, GameScore playerTwoGameScore, Player gameWinnerinner) {
        return new TennisGame(buildGameScores(playerOneGameScore, playerTwoGameScore), gameWinnerinner);
    }

    private Map<Player, GameScore> buildGameScores(GameScore playerOneScore, GameScore playerTwoScore) {
        Map<Player, GameScore> scores = new EnumMap<>(Player.class);
        scores.put(PLAYER_ONE, playerOneScore);
        scores.put(PLAYER_TWO, playerTwoScore);
        return scores;
    }

}