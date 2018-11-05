package fr.sebmartin.kata.tennis.service;

import fr.sebmartin.kata.tennis.domain.GameOverException;
import fr.sebmartin.kata.tennis.domain.Player;
import fr.sebmartin.kata.tennis.domain.TennisMatch;
import org.junit.Before;
import org.junit.Test;

import static fr.sebmartin.kata.tennis.domain.AssertUtil.*;
import static fr.sebmartin.kata.tennis.domain.GameScore.*;
import static fr.sebmartin.kata.tennis.domain.Player.PLAYER_ONE;
import static fr.sebmartin.kata.tennis.domain.Player.PLAYER_TWO;

public class TennisServiceIT {

    private TennisMatch match;
    private TennisService tennisService;


    @Before
    public void setUp() {
        match = new TennisMatch();
        
        tennisService = new TennisService(match);
    }

    @Test
    public void sprint1() throws GameOverException {

        // Start the game: 0 - 0
        assertGameScores(match, LOVE, LOVE);
        assertGameNotWon(match);

        // Player 1 wins 1 point : 15 - 0
        tennisService.mark(PLAYER_ONE);
        assertGameScores(match, FIFTEEN, LOVE);
        assertGameNotWon(match);

        // Player 1 wins 1 point : 30 - 0
        tennisService.mark(PLAYER_ONE);
        assertGameScores(match, THIRTY, LOVE);
        assertGameNotWon(match);

        // Player 2 wins 1 point : 30 - 15
        tennisService.mark(PLAYER_TWO);
        assertGameScores(match, THIRTY, FIFTEEN);
        assertGameNotWon(match);

        // Player 1 wins 1 point : 40 - 15
        tennisService.mark(PLAYER_ONE);
        assertGameScores(match, FORTY, FIFTEEN);
        assertGameNotWon(match);

        // Player 2 wins 1 point : 40 - 30
        tennisService.mark(PLAYER_TWO);
        assertGameScores(match, FORTY, THIRTY);
        assertGameNotWon(match);

        // Player 2 wins 1 point : Deuce - Deuce
        tennisService.mark(PLAYER_TWO);
        assertGameScores(match, DEUCE, DEUCE);
        assertGameNotWon(match);

        // Player 2 wins 1 point : 40 - Advantage
        tennisService.mark(PLAYER_TWO);
        assertGameScores(match, FORTY, ADVANTAGE);
        assertGameNotWon(match);

        // Player 1 wins 1 point : Deuce - Deuce
        tennisService.mark(PLAYER_ONE);
        assertGameScores(match, DEUCE, DEUCE);
        assertGameNotWon(match);

        // Player 1 wins 1 point : Advantage - 40
        tennisService.mark(PLAYER_ONE);
        assertGameScores(match, ADVANTAGE, FORTY);
        assertGameNotWon(match);

        // Player 1 wins 1 point : 0 - 0 ; Player 1 win the game
        tennisService.mark(PLAYER_ONE);
        assertGameScores(match, LOVE, LOVE);
        assertGameWon(match, PLAYER_ONE);

    }

    @Test
    public void sprint2_us1() throws GameOverException {
        // Start the game & the set : 0 - 0 | 0 - 0
        assertGameScores(match, LOVE, LOVE);
        assertSetScores(match, 0, 0);
        assertGameNotWon(match);
        assertSetNotWon(match);

        // Player 1 wins 1 point : 15 - 0 | 0 - 0
        tennisService.mark(PLAYER_ONE);
        assertGameScores(match, FIFTEEN, LOVE);
        assertSetScores(match, 0, 0);
        assertGameNotWon(match);
        assertSetNotWon(match);

        // Player 1 wins 1 point : 30 - 0 | 0 - 0
        tennisService.mark(PLAYER_ONE);
        assertGameScores(match, THIRTY, LOVE);
        assertSetScores(match, 0, 0);
        assertGameNotWon(match);
        assertSetNotWon(match);

        // Player 2 wins 1 point : 30 - 15 | 0 - 0
        tennisService.mark(PLAYER_TWO);
        assertGameScores(match, THIRTY, FIFTEEN);
        assertSetScores(match, 0, 0);
        assertGameNotWon(match);
        assertSetNotWon(match);

        // Player 1 wins 1 point : 40 - 15 | 0 - 0
        tennisService.mark(PLAYER_ONE);
        assertGameScores(match, FORTY, FIFTEEN);
        assertSetScores(match, 0, 0);
        assertGameNotWon(match);
        assertSetNotWon(match);

        // Player 1 wins 1 point : 0 - 0 | 1 - 0
        tennisService.mark(PLAYER_ONE);
        assertGameScores(match, LOVE, LOVE);
        assertSetScores(match, 1, 0);
        assertGameWon(match, PLAYER_ONE);
        assertSetNotWon(match);

        // Player 2 wins 1 game : 0 - 0 | 1 - 1
        winGame(PLAYER_TWO);
        assertGameScores(match, LOVE, LOVE);
        assertSetScores(match, 1, 1);
        assertGameWon(match, PLAYER_TWO);
        assertSetNotWon(match);

        // Player 2 wins 1 game : 0 - 0 | 1 - 2
        winGame(PLAYER_TWO);
        assertGameScores(match, LOVE, LOVE);
        assertSetScores(match, 1, 2);
        assertGameWon(match, PLAYER_TWO);
        assertSetNotWon(match);

        // Player 2 wins 1 game : 0 - 0 | 1 - 3
        winGame(PLAYER_TWO);
        assertGameScores(match, LOVE, LOVE);
        assertSetScores(match, 1, 3);
        assertGameWon(match, PLAYER_TWO);
        assertSetNotWon(match);

        // Player 2 wins 1 game : 0 - 0 | 1 - 4
        winGame(PLAYER_TWO);
        assertGameScores(match, LOVE, LOVE);
        assertSetScores(match, 1, 4);
        assertGameWon(match, PLAYER_TWO);
        assertSetNotWon(match);

        // Player 2 wins 1 game : 0 - 0 | 1 - 5
        winGame(PLAYER_TWO);
        assertGameScores(match, LOVE, LOVE);
        assertSetScores(match, 1, 5);
        assertGameWon(match, PLAYER_TWO);
        assertSetNotWon(match);

        // Player 2 wins 1 game : 0 - 0 | 1 - 6
        winGame(PLAYER_TWO);
        assertGameScores(match, LOVE, LOVE);
        assertSetScores(match, 1, 6);
        assertGameWon(match, PLAYER_TWO);
        assertSetWon(match, PLAYER_TWO);
    }

    @Test
    public void sprint2_us2() throws GameOverException {
        winGames(PLAYER_ONE, 5);
        winGames(PLAYER_TWO, 6);
        winPoints(PLAYER_ONE, 3);
        winPoints(PLAYER_TWO, 1);

        assertGameScores(match, FORTY, FIFTEEN);
        assertGameNotWon(match);
        assertSetScores(match, 5, 6);
        assertSetNotWon(match);
        assertNoTieBreak(match);
        assertMatchNotWon(match);

        tennisService.mark(PLAYER_ONE);

        assertGameScores(match, LOVE, LOVE);
        assertGameWon(match, PLAYER_ONE);
        assertSetScores(match, 6, 6);
        assertSetNotWon(match);
        assertTieBreak(match);
        assertTieBreakScores(match, 0, 0);
        assertTieBreakNotWon(match);
        assertMatchNotWon(match);

        tennisService.mark(PLAYER_ONE);

        assertGameScores(match, LOVE, LOVE);
        assertGameWon(match, PLAYER_ONE);
        assertSetScores(match, 6, 6);
        assertSetNotWon(match);
        assertTieBreak(match);
        assertTieBreakScores(match, 1, 0);
        assertTieBreakNotWon(match);
        assertMatchNotWon(match);

        tennisService.mark(PLAYER_TWO);

        assertGameScores(match, LOVE, LOVE);
        assertGameWon(match, PLAYER_ONE);
        assertSetScores(match, 6, 6);
        assertSetNotWon(match);
        assertTieBreak(match);
        assertTieBreakScores(match, 1, 1);
        assertTieBreakNotWon(match);
        assertMatchNotWon(match);

        tennisService.mark(PLAYER_ONE);

        assertGameScores(match, LOVE, LOVE);
        assertGameWon(match, PLAYER_ONE);
        assertSetScores(match, 6, 6);
        assertSetNotWon(match);
        assertTieBreak(match);
        assertTieBreakScores(match, 2, 1);
        assertTieBreakNotWon(match);
        assertMatchNotWon(match);

        tennisService.mark(PLAYER_ONE);

        assertGameScores(match, LOVE, LOVE);
        assertGameWon(match, PLAYER_ONE);
        assertSetScores(match, 6, 6);
        assertSetNotWon(match);
        assertTieBreak(match);
        assertTieBreakScores(match, 3, 1);
        assertTieBreakNotWon(match);
        assertMatchNotWon(match);

        tennisService.mark(PLAYER_TWO);

        assertGameScores(match, LOVE, LOVE);
        assertGameWon(match, PLAYER_ONE);
        assertSetScores(match, 6, 6);
        assertSetNotWon(match);
        assertTieBreak(match);
        assertTieBreakScores(match, 3, 2);
        assertTieBreakNotWon(match);
        assertMatchNotWon(match);

        tennisService.mark(PLAYER_ONE);

        assertGameScores(match, LOVE, LOVE);
        assertGameWon(match, PLAYER_ONE);
        assertSetScores(match, 6, 6);
        assertSetNotWon(match);
        assertTieBreak(match);
        assertTieBreakScores(match, 4, 2);
        assertTieBreakNotWon(match);
        assertMatchNotWon(match);

        tennisService.mark(PLAYER_ONE);

        assertGameScores(match, LOVE, LOVE);
        assertGameWon(match, PLAYER_ONE);
        assertSetScores(match, 6, 6);
        assertSetNotWon(match);
        assertTieBreak(match);
        assertTieBreakScores(match, 5, 2);
        assertTieBreakNotWon(match);
        assertMatchNotWon(match);

        tennisService.mark(PLAYER_ONE);

        assertGameScores(match, LOVE, LOVE);
        assertGameWon(match, PLAYER_ONE);
        assertSetScores(match, 6, 6);
        assertSetNotWon(match);
        assertTieBreak(match);
        assertTieBreakScores(match, 6, 2);
        assertTieBreakNotWon(match);
        assertMatchNotWon(match);

        tennisService.mark(PLAYER_ONE);

        assertGameScores(match, LOVE, LOVE);
        assertGameWon(match, PLAYER_ONE);
        assertSetScores(match, 7, 6);
        assertSetWon(match, PLAYER_ONE);
        assertTieBreak(match);
        assertTieBreakScores(match, 0, 0);
        assertTieBreakWon(match, PLAYER_ONE);
        assertMatchWon(match, PLAYER_ONE);

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