package fr.sebmartin.kata.tennis.service;

import fr.sebmartin.kata.tennis.domain.GameOverException;
import fr.sebmartin.kata.tennis.domain.TennisMatch;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static fr.sebmartin.kata.tennis.domain.AssertUtil.assertGameScores;
import static fr.sebmartin.kata.tennis.domain.GameScore.FIFTEEN;
import static fr.sebmartin.kata.tennis.domain.GameScore.LOVE;
import static fr.sebmartin.kata.tennis.domain.Player.PLAYER_ONE;
import static org.mockito.Mockito.verify;

public class TennisServiceTest {

    @Test
    public void displayWinnerAndMatchForEachPointWon() throws GameOverException {

        Display display = Mockito.mock(Display.class);

        TennisService service = new TennisService(display);

        service.mark(PLAYER_ONE);

        verify(display).playerWonPoint(PLAYER_ONE);

        ArgumentCaptor<TennisMatch> argument = ArgumentCaptor.forClass(TennisMatch.class);
        verify(display).matchUpdated(argument.capture());

        assertGameScores(argument.getValue().game(), FIFTEEN, LOVE);

    }
}