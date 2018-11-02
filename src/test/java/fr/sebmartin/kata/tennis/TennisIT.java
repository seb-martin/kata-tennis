package fr.sebmartin.kata.tennis;

import fr.sebmartin.kata.tennis.domain.GameOverException;
import fr.sebmartin.kata.tennis.domain.Player;
import fr.sebmartin.kata.tennis.presentation.ConsoleDisplay;
import fr.sebmartin.kata.tennis.service.TennisService;
import org.junit.Before;
import org.junit.Test;

import static fr.sebmartin.kata.tennis.domain.AssertUtil.*;
import static fr.sebmartin.kata.tennis.domain.GameScore.*;
import static fr.sebmartin.kata.tennis.domain.Player.PLAYER_ONE;
import static fr.sebmartin.kata.tennis.domain.Player.PLAYER_TWO;

public class TennisIT {

    private TennisService tennisService;

    @Before
    public void setUp() {
        tennisService = new TennisService(new ConsoleDisplay());
    }

    @Test
    public void sprint1() throws GameOverException {

        // Start the game: 0 - 0
        assertGameScores(tennisService.match(), LOVE, LOVE);
        assertGameNotWon(tennisService.match());

        // Player 1 wins 1 point : 15 - 0
        tennisService.mark(PLAYER_ONE);
        assertGameScores(tennisService.match(), FIFTEEN, LOVE);
        assertGameNotWon(tennisService.match());

        // Player 1 wins 1 point : 30 - 0
        tennisService.mark(PLAYER_ONE);
        assertGameScores(tennisService.match(), THIRTY, LOVE);
        assertGameNotWon(tennisService.match());

        // Player 2 wins 1 point : 30 - 15
        tennisService.mark(PLAYER_TWO);
        assertGameScores(tennisService.match(), THIRTY, FIFTEEN);
        assertGameNotWon(tennisService.match());

        // Player 1 wins 1 point : 40 - 15
        tennisService.mark(PLAYER_ONE);
        assertGameScores(tennisService.match(), FORTY, FIFTEEN);
        assertGameNotWon(tennisService.match());

        // Player 2 wins 1 point : 40 - 30
        tennisService.mark(PLAYER_TWO);
        assertGameScores(tennisService.match(), FORTY, THIRTY);
        assertGameNotWon(tennisService.match());

        // Player 2 wins 1 point : Deuce - Deuce
        tennisService.mark(PLAYER_TWO);
        assertGameScores(tennisService.match(), DEUCE, DEUCE);
        assertGameNotWon(tennisService.match());

        // Player 2 wins 1 point : 40 - Advantage
        tennisService.mark(PLAYER_TWO);
        assertGameScores(tennisService.match(), FORTY, ADVANTAGE);
        assertGameNotWon(tennisService.match());

        // Player 1 wins 1 point : Deuce - Deuce
        tennisService.mark(PLAYER_ONE);
        assertGameScores(tennisService.match(), DEUCE, DEUCE);
        assertGameNotWon(tennisService.match());

        // Player 1 wins 1 point : Advantage - 40
        tennisService.mark(PLAYER_ONE);
        assertGameScores(tennisService.match(), ADVANTAGE, FORTY);
        assertGameNotWon(tennisService.match());

        // Player 1 wins 1 point : 0 - 0 ; Player 1 win the game
        tennisService.mark(PLAYER_ONE);
        assertGameScores(tennisService.match(), LOVE, LOVE);
        assertGameWon(tennisService.match(), PLAYER_ONE);

    }

    @Test
    public void sprint2_us1() throws GameOverException {
        // Start the game & the set : 0 - 0 | 0 - 0
        assertGameScores(tennisService.match(), LOVE, LOVE);
        assertSetScores(tennisService.match(), 0, 0);
        assertGameNotWon(tennisService.match());
        assertSetNotWon(tennisService.match());

        // Player 1 wins 1 point : 15 - 0 | 0 - 0
        tennisService.mark(PLAYER_ONE);
        assertGameScores(tennisService.match(), FIFTEEN, LOVE);
        assertSetScores(tennisService.match(), 0, 0);
        assertGameNotWon(tennisService.match());
        assertSetNotWon(tennisService.match());

        // Player 1 wins 1 point : 30 - 0 | 0 - 0
        tennisService.mark(PLAYER_ONE);
        assertGameScores(tennisService.match(), THIRTY, LOVE);
        assertSetScores(tennisService.match(), 0, 0);
        assertGameNotWon(tennisService.match());
        assertSetNotWon(tennisService.match());

        // Player 2 wins 1 point : 30 - 15 | 0 - 0
        tennisService.mark(PLAYER_TWO);
        assertGameScores(tennisService.match(), THIRTY, FIFTEEN);
        assertSetScores(tennisService.match(), 0, 0);
        assertGameNotWon(tennisService.match());
        assertSetNotWon(tennisService.match());

        // Player 1 wins 1 point : 40 - 15 | 0 - 0
        tennisService.mark(PLAYER_ONE);
        assertGameScores(tennisService.match(), FORTY, FIFTEEN);
        assertSetScores(tennisService.match(), 0, 0);
        assertGameNotWon(tennisService.match());
        assertSetNotWon(tennisService.match());

        // Player 1 wins 1 point : 0 - 0 | 1 - 0
        tennisService.mark(PLAYER_ONE);
        assertGameScores(tennisService.match(), LOVE, LOVE);
        assertSetScores(tennisService.match(), 1, 0);
        assertGameWon(tennisService.match(), PLAYER_ONE);
        assertSetNotWon(tennisService.match());

        // Player 2 wins 1 game : 0 - 0 | 1 - 1
        winGame(PLAYER_TWO);
        assertGameScores(tennisService.match(), LOVE, LOVE);
        assertSetScores(tennisService.match(), 1, 1);
        assertGameWon(tennisService.match(), PLAYER_TWO);
        assertSetNotWon(tennisService.match());

        // Player 2 wins 1 game : 0 - 0 | 1 - 2
        winGame(PLAYER_TWO);
        assertGameScores(tennisService.match(), LOVE, LOVE);
        assertSetScores(tennisService.match(), 1, 2);
        assertGameWon(tennisService.match(), PLAYER_TWO);
        assertSetNotWon(tennisService.match());

        // Player 2 wins 1 game : 0 - 0 | 1 - 3
        winGame(PLAYER_TWO);
        assertGameScores(tennisService.match(), LOVE, LOVE);
        assertSetScores(tennisService.match(), 1, 3);
        assertGameWon(tennisService.match(), PLAYER_TWO);
        assertSetNotWon(tennisService.match());

        // Player 2 wins 1 game : 0 - 0 | 1 - 4
        winGame(PLAYER_TWO);
        assertGameScores(tennisService.match(), LOVE, LOVE);
        assertSetScores(tennisService.match(), 1, 4);
        assertGameWon(tennisService.match(), PLAYER_TWO);
        assertSetNotWon(tennisService.match());

        // Player 2 wins 1 game : 0 - 0 | 1 - 5
        winGame(PLAYER_TWO);
        assertGameScores(tennisService.match(), LOVE, LOVE);
        assertSetScores(tennisService.match(), 1, 5);
        assertGameWon(tennisService.match(), PLAYER_TWO);
        assertSetNotWon(tennisService.match());

        // Player 2 wins 1 game : 0 - 0 | 1 - 6
        winGame(PLAYER_TWO);
        assertGameScores(tennisService.match(), LOVE, LOVE);
        assertSetScores(tennisService.match(), 1, 6);
        assertGameWon(tennisService.match(), PLAYER_TWO);
        assertSetWon(tennisService.match(), PLAYER_TWO);
    }

    @Test
    public void sprint2_us2() throws GameOverException {
        winGames(PLAYER_ONE, 5);
        winGames(PLAYER_TWO, 6);
        winPoints(PLAYER_ONE, 3);
        winPoints(PLAYER_TWO, 1);

        assertGameScores(tennisService.match(), FORTY, FIFTEEN);
        assertGameNotWon(tennisService.match());
        assertSetScores(tennisService.match(), 5, 6);
        assertSetNotWon(tennisService.match());
        assertNoTieBreak(tennisService.match());
        assertMatchNotWon(tennisService.match());

        tennisService.mark(PLAYER_ONE);

        assertGameScores(tennisService.match(), LOVE, LOVE);
        assertGameWon(tennisService.match(), PLAYER_ONE);
        assertSetScores(tennisService.match(), 6, 6);
        assertSetNotWon(tennisService.match());
        assertTieBreak(tennisService.match());
        assertTieBreakScores(tennisService.match(), 0, 0);
        assertTieBreakNotWon(tennisService.match());
        assertMatchNotWon(tennisService.match());

        tennisService.mark(PLAYER_ONE);

        assertGameScores(tennisService.match(), LOVE, LOVE);
        assertGameWon(tennisService.match(), PLAYER_ONE);
        assertSetScores(tennisService.match(), 6, 6);
        assertSetNotWon(tennisService.match());
        assertTieBreak(tennisService.match());
        assertTieBreakScores(tennisService.match(), 1, 0);
        assertTieBreakNotWon(tennisService.match());
        assertMatchNotWon(tennisService.match());

        tennisService.mark(PLAYER_TWO);

        assertGameScores(tennisService.match(), LOVE, LOVE);
        assertGameWon(tennisService.match(), PLAYER_ONE);
        assertSetScores(tennisService.match(), 6, 6);
        assertSetNotWon(tennisService.match());
        assertTieBreak(tennisService.match());
        assertTieBreakScores(tennisService.match(), 1, 1);
        assertTieBreakNotWon(tennisService.match());
        assertMatchNotWon(tennisService.match());

        tennisService.mark(PLAYER_ONE);

        assertGameScores(tennisService.match(), LOVE, LOVE);
        assertGameWon(tennisService.match(), PLAYER_ONE);
        assertSetScores(tennisService.match(), 6, 6);
        assertSetNotWon(tennisService.match());
        assertTieBreak(tennisService.match());
        assertTieBreakScores(tennisService.match(), 2, 1);
        assertTieBreakNotWon(tennisService.match());
        assertMatchNotWon(tennisService.match());

        tennisService.mark(PLAYER_ONE);

        assertGameScores(tennisService.match(), LOVE, LOVE);
        assertGameWon(tennisService.match(), PLAYER_ONE);
        assertSetScores(tennisService.match(), 6, 6);
        assertSetNotWon(tennisService.match());
        assertTieBreak(tennisService.match());
        assertTieBreakScores(tennisService.match(), 3, 1);
        assertTieBreakNotWon(tennisService.match());
        assertMatchNotWon(tennisService.match());

        tennisService.mark(PLAYER_TWO);

        assertGameScores(tennisService.match(), LOVE, LOVE);
        assertGameWon(tennisService.match(), PLAYER_ONE);
        assertSetScores(tennisService.match(), 6, 6);
        assertSetNotWon(tennisService.match());
        assertTieBreak(tennisService.match());
        assertTieBreakScores(tennisService.match(), 3, 2);
        assertTieBreakNotWon(tennisService.match());
        assertMatchNotWon(tennisService.match());

        tennisService.mark(PLAYER_ONE);

        assertGameScores(tennisService.match(), LOVE, LOVE);
        assertGameWon(tennisService.match(), PLAYER_ONE);
        assertSetScores(tennisService.match(), 6, 6);
        assertSetNotWon(tennisService.match());
        assertTieBreak(tennisService.match());
        assertTieBreakScores(tennisService.match(), 4, 2);
        assertTieBreakNotWon(tennisService.match());
        assertMatchNotWon(tennisService.match());

        tennisService.mark(PLAYER_ONE);

        assertGameScores(tennisService.match(), LOVE, LOVE);
        assertGameWon(tennisService.match(), PLAYER_ONE);
        assertSetScores(tennisService.match(), 6, 6);
        assertSetNotWon(tennisService.match());
        assertTieBreak(tennisService.match());
        assertTieBreakScores(tennisService.match(), 5, 2);
        assertTieBreakNotWon(tennisService.match());
        assertMatchNotWon(tennisService.match());

        tennisService.mark(PLAYER_ONE);

        assertGameScores(tennisService.match(), LOVE, LOVE);
        assertGameWon(tennisService.match(), PLAYER_ONE);
        assertSetScores(tennisService.match(), 6, 6);
        assertSetNotWon(tennisService.match());
        assertTieBreak(tennisService.match());
        assertTieBreakScores(tennisService.match(), 6, 2);
        assertTieBreakNotWon(tennisService.match());
        assertMatchNotWon(tennisService.match());

        tennisService.mark(PLAYER_ONE);

        assertGameScores(tennisService.match(), LOVE, LOVE);
        assertGameWon(tennisService.match(), PLAYER_ONE);
        assertSetScores(tennisService.match(), 7, 6);
        assertSetWon(tennisService.match(), PLAYER_ONE);
        assertTieBreak(tennisService.match());
        assertTieBreakScores(tennisService.match(), 0, 0);
        assertTieBreakWon(tennisService.match(), PLAYER_ONE);
        assertMatchWon(tennisService.match(), PLAYER_ONE);

    }

    private void winGames(Player player, int numberOfGames) throws GameOverException {
        for (int i = 0; i < numberOfGames; i++) {
            winGame(player);
        }
    }

    private void winGame(Player player) throws GameOverException {
        winPoints(player, 4);
    }

    private void winPoints(Player player, int numberOfPoints) throws GameOverException {
        for (int i = 0; i < numberOfPoints; i++) {
            tennisService.mark(player);
        }
    }

}