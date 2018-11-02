package fr.sebmartin.kata.tennis.domain;

import org.junit.Test;

import static fr.sebmartin.kata.tennis.domain.AssertUtil.*;
import static fr.sebmartin.kata.tennis.domain.Player.PLAYER_ONE;
import static fr.sebmartin.kata.tennis.domain.Player.PLAYER_TWO;
import static fr.sebmartin.kata.tennis.domain.TennisFactory.buildTieBreak;

public class TieBreakTest {

    @Test
    public void eachTimeAPlayerWinAPointTheScoreChange() throws GameOverException {
        final int numberOfRound = 20;

        TieBreak tieBreak = buildTieBreak(1, 0, null);

        for (int round = 1; round <= numberOfRound; round++) {
            tieBreak.mark(PLAYER_TWO);
            tieBreak.mark(PLAYER_ONE);

            assertTieBreakScores(tieBreak, round + 1, round);
            assertTieBreakNotWon(tieBreak);
        }
    }

    @Test
    public void tieBreakEndsAsSoonAsAPlayerGetsAtLeast7PointsAndTwoPointsMoreThatIsOpponent() throws GameOverException {
        TieBreak tieBreak = buildTieBreak(6, 6, null);

        tieBreak.mark(PLAYER_ONE);
        assertTieBreakScores(tieBreak, 7, 6);
        assertTieBreakNotWon(tieBreak);

        tieBreak.mark(PLAYER_TWO);
        tieBreak.mark(PLAYER_TWO);
        assertTieBreakScores(tieBreak, 7, 8);
        assertTieBreakNotWon(tieBreak);

        tieBreak.mark(PLAYER_TWO);
        assertTieBreakScores(tieBreak, 0, 0);
        assertTieBreakWon(tieBreak, PLAYER_TWO);
    }

    @Test(expected = GameOverException.class)
    public void shouldThrowAnExceptionIfTimeBreakIsOver() throws GameOverException {

        TieBreak tieBreak = buildTieBreak(0, 0, PLAYER_ONE);

        tieBreak.mark(PLAYER_ONE);

    }
}