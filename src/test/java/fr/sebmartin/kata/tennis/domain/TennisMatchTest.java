package fr.sebmartin.kata.tennis.domain;

import org.junit.Test;

import static fr.sebmartin.kata.tennis.domain.AssertUtil.*;
import static fr.sebmartin.kata.tennis.domain.GameScore.FORTY;
import static fr.sebmartin.kata.tennis.domain.GameScore.LOVE;
import static fr.sebmartin.kata.tennis.domain.Player.PLAYER_ONE;
import static fr.sebmartin.kata.tennis.domain.TennisFactory.*;

public class TennisMatchTest {

    @Test
    public void thePlayerWhoWinsTheSetWinTheMatch() throws GameOverException {
        TennisMatch match = buildMatch(buildSet(6, 0, null, buildGame(FORTY, LOVE, null)));

        match.mark(PLAYER_ONE);

        assertGameScores(match, LOVE, LOVE);
        assertGameWon(match, PLAYER_ONE);
        assertNoTieBreak(match);
        assertSetScores(match, 7, 0);
        assertSetWon(match, PLAYER_ONE);
        assertMatchWon(match, PLAYER_ONE);

    }

    @Test
    public void thePlayerWhoWinsTheTieBreakWinTheMatch() throws GameOverException {
        TennisMatch match = buildMatch(buildSet(6, 6, null, buildGame(LOVE, LOVE, PLAYER_ONE), buildTieBreak(6, 5, null)));

        match.mark(PLAYER_ONE);

        assertGameScores(match, LOVE, LOVE);
        assertGameWon(match, PLAYER_ONE);
        assertTieBreakScores(match, 0, 0);
        assertTieBreakWon(match, PLAYER_ONE);
        assertSetScores(match, 7, 6);
        assertSetWon(match, PLAYER_ONE);
        assertMatchWon(match, PLAYER_ONE);

    }

}