package fr.sebmartin.kata.tennis.domain;

import org.junit.Test;

import static fr.sebmartin.kata.tennis.domain.AssertUtil.*;
import static fr.sebmartin.kata.tennis.domain.GameScore.FORTY;
import static fr.sebmartin.kata.tennis.domain.GameScore.LOVE;
import static fr.sebmartin.kata.tennis.domain.Player.PLAYER_ONE;
import static fr.sebmartin.kata.tennis.domain.Player.PLAYER_TWO;
import static fr.sebmartin.kata.tennis.domain.TennisFactory.*;

public class TennisSetTest {

    @Test
    public void theSetStartsWithAScoreOf0GameForEachPlayer() {
        TennisSet set = new TennisSet();

        assertSetScores(set, 0, 0);
        assertSetNotWon(set);
    }

    @Test
    public void ifPlayerWin1GameHisScoreIs1() throws GameOverException {
        TennisSet set = buildSet(0, 0, null, buildGame(FORTY, LOVE, null));

        set.mark(PLAYER_ONE);

        assertSetScores(set, 1, 0);
        assertSetNotWon(set);
    }


    @Test
    public void ifPlayerWin2GamesHisScoreIs2() throws GameOverException {
        TennisSet set = buildSet(1, 0, null, buildGame(FORTY, LOVE, null));

        set.mark(PLAYER_ONE);

        assertSetScores(set, 2, 0);
        assertSetNotWon(set);
    }

    @Test
    public void ifPlayerWin3GamesHisScoreIs3() throws GameOverException {
        TennisSet set = buildSet(2, 0, null, buildGame(FORTY, LOVE, null));

        set.mark(PLAYER_ONE);

        assertSetScores(set, 3, 0);
        assertSetNotWon(set);
    }

    @Test
    public void ifPlayerWin4GamesHisScoreIs4() throws GameOverException {
        TennisSet set = buildSet(3, 0, null, buildGame(FORTY, LOVE, null));

        set.mark(PLAYER_ONE);

        assertSetScores(set, 4, 0);
        assertSetNotWon(set);
    }

    @Test
    public void ifPlayerWin5GamesHisScoreIs5() throws GameOverException {

        TennisSet set = buildSet(4, 0, null, buildGame(FORTY, LOVE, null));

        set.mark(PLAYER_ONE);

        assertSetScores(set, 5, 0);
        assertSetNotWon(set);
    }

    @Test
    public void ifPlayerReachTheSetScoreOf6AndTheOtherPlayerHasASetScoreOf4OrLowerThePlayerWinTheSet() throws GameOverException {

        final int expectedPlayerOneSetScore = 6;

        for (int playerTwoSetScore = 1; playerTwoSetScore <= 4; playerTwoSetScore++) {

            TennisSet set = buildSet(expectedPlayerOneSetScore - 1, playerTwoSetScore, null, buildGame(FORTY, LOVE, null));

            set.mark(PLAYER_ONE);

            assertSetScores(set, expectedPlayerOneSetScore, playerTwoSetScore);
            assertSetWon(set, PLAYER_ONE);
        }

    }

    @Test
    public void ifPlayerReachTheSetScoreOf6AndTheOtherPlayerHasASetScoreOf5ANewGameMustBePlayed() throws GameOverException {

        final int expectedPlayerOneSetScore = 6;
        final int playerTwoSetScore = 5;

        TennisSet set = buildSet(expectedPlayerOneSetScore - 1, playerTwoSetScore, null, buildGame(FORTY, LOVE, null));

        set.mark(PLAYER_ONE);

        assertSetScores(set, expectedPlayerOneSetScore, playerTwoSetScore);
        assertSetNotWon(set);
    }

    @Test
    public void ifPlayerReachTheSetScoreOf7ThePlayerWinTheSet() throws GameOverException {

        final int expectedPlayerOneSetScore = 7;
        final int playerTwoSetScore = 5;

        TennisSet set = buildSet(expectedPlayerOneSetScore - 1, playerTwoSetScore, null, buildGame(FORTY, LOVE, null));

        set.mark(PLAYER_ONE);

        assertSetScores(set, expectedPlayerOneSetScore, playerTwoSetScore);
        assertSetWon(set, PLAYER_ONE);
    }

    @Test(expected = GameOverException.class)
    public void shouldThrowAnExceptionWhenGameIsOver() throws GameOverException {

        TennisSet set = buildSet(6, 0, PLAYER_ONE, buildGame(LOVE, LOVE, PLAYER_ONE));

        set.mark(PLAYER_ONE);
    }

    @Test
    public void ifTheTwoPlayersReachTheScoreOf6GamesTheTieBreakRuleIsActivated() throws GameOverException {

        TennisSet set = buildSet(6, 5, null, buildGame(LOVE, FORTY, null));

        set.mark(PLAYER_TWO);

        assertSetScores(set, 6, 6);
        assertSetNotWon(set);
        assertTieBreakScores(set, 0, 0);
        assertTieBreakNotWon(set);
    }

    @Test
    public void thePlayerWhoWinsTheTieBreakWinTheSet() throws GameOverException {
        TennisSet set = buildSet(6, 6, null, buildGame(LOVE, LOVE, PLAYER_ONE), buildTieBreak(6, 5, null));

        set.mark(PLAYER_ONE);

        assertTieBreakScores(set, 0, 0);
        assertTieBreakWon(set, PLAYER_ONE);
        assertSetScores(set, 7, 6);
        assertSetWon(set, PLAYER_ONE);
    }


}