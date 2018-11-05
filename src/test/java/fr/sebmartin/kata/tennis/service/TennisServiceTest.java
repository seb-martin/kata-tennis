package fr.sebmartin.kata.tennis.service;

import fr.sebmartin.kata.tennis.domain.GameOverException;
import fr.sebmartin.kata.tennis.domain.Player;
import fr.sebmartin.kata.tennis.domain.TennisMatch;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static fr.sebmartin.kata.tennis.domain.AssertUtil.assertGameScores;
import static fr.sebmartin.kata.tennis.domain.GameScore.FIFTEEN;
import static fr.sebmartin.kata.tennis.domain.GameScore.LOVE;
import static fr.sebmartin.kata.tennis.domain.Player.PLAYER_ONE;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

public class TennisServiceTest {

    @Test
    public void displayWinnerAndMatchForEachPointWon() throws GameOverException {

        TennisMatch match = new TennisMatch();
        TennisService service = new TennisService(match);

        TennisDisplay display = Mockito.mock(TennisDisplay.class);
        service.register(display);

        service.mark(PLAYER_ONE);

        ArgumentCaptor<Player> playerCaptor = ArgumentCaptor.forClass(Player.class);
        ArgumentCaptor<TennisMatch> matchCaptor = ArgumentCaptor.forClass(TennisMatch.class);

        verify(display).playerWonPoint(playerCaptor.capture(), matchCaptor.capture());

        assertEquals(PLAYER_ONE, playerCaptor.getValue());
        assertEquals(match, matchCaptor.getValue());
        assertGameScores(matchCaptor.getValue(), FIFTEEN, LOVE);

    }
}