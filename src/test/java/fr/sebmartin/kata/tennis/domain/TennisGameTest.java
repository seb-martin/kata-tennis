package fr.sebmartin.kata.tennis.domain;

import org.junit.Test;

import static fr.sebmartin.kata.tennis.domain.AssertUtil.*;
import static fr.sebmartin.kata.tennis.domain.GameScore.*;
import static fr.sebmartin.kata.tennis.domain.Player.PLAYER_ONE;
import static fr.sebmartin.kata.tennis.domain.Player.PLAYER_TWO;
import static fr.sebmartin.kata.tennis.domain.TennisFactory.buildGame;

public class TennisGameTest {

    @Test
    public void gameStartsWithScoreOfLoveForEachPlayer() {

        TennisGame game = new TennisGame();

        assertGameScores(game, LOVE, LOVE);
        assertGameNotWon(game);
    }

    @Test
    public void gameScoreChangesFromLoveToFifteen() throws GameOverException {

        TennisGame game = buildGame(LOVE, LOVE,null);

        game.mark(PLAYER_ONE);

        assertGameScores(game, FIFTEEN, LOVE);
        assertGameNotWon(game);
    }

    @Test
    public void gameScoreChangesFromFifteenToThirty() throws GameOverException {

        TennisGame game = buildGame(FIFTEEN, LOVE,null);

        game.mark(PLAYER_ONE);

        assertGameScores(game, THIRTY, LOVE);
        assertGameNotWon(game);
    }

    @Test
    public void gameScoreChangesFromThirtyToForty() throws GameOverException {

        TennisGame game = buildGame(GameScore.THIRTY, LOVE,null);

        game.mark(PLAYER_ONE);

        assertGameScores(game, FORTY, LOVE);
        assertGameNotWon(game);
    }

    @Test
    public void gameScoreChangesFromFortyToWinGame() throws GameOverException {

        TennisGame game = buildGame(GameScore.FORTY, LOVE,null);

        game.mark(PLAYER_ONE);

        assertGameScores(game, LOVE, LOVE);
        assertGameWon(game, PLAYER_ONE);
    }

    @Test(expected = GameOverException.class)
    public void shouldThrowsAnExceptionWhenGameIsOver() throws GameOverException {

        TennisGame game = buildGame(LOVE, LOVE, PLAYER_ONE);

        game.mark(PLAYER_ONE);
    }

    @Test
    public void ifTheTwoPlayersReachTheScoreOfFortyTheDeuceRuleIsActivated() throws GameOverException {

        TennisGame game = buildGame(FORTY, THIRTY, null);

        game.mark(PLAYER_TWO);

        assertGameScores(game, DEUCE, DEUCE);
        assertGameNotWon(game);
    }

    @Test
    public void ifTheScoreIsDeuceThePlayerWhoWinThePointTakeTheAdvantage() throws GameOverException {

        TennisGame game = buildGame(DEUCE, DEUCE, null);

        game.mark(PLAYER_ONE);

        assertGameScores(game, ADVANTAGE, FORTY);
        assertGameNotWon(game);
    }

    @Test
    public void ifThePlayerWhoHasTheAdvantageWinThePointHeWinTheGame() throws GameOverException {

        TennisGame game = buildGame(ADVANTAGE, FORTY, null);

        game.mark(PLAYER_ONE);

        assertGameScores(game, LOVE, LOVE);
        assertGameWon(game, PLAYER_ONE);
    }

    @Test
    public void ifThePlayerWhoHasTheAdvantageLoosesThePointTheScoreIsDeuce() throws GameOverException {

        TennisGame game = buildGame(ADVANTAGE, FORTY, null);

        game.mark(PLAYER_TWO);

        assertGameScores(game, DEUCE, DEUCE);
        assertGameNotWon(game);
    }

}