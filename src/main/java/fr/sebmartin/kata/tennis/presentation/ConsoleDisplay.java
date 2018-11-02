package fr.sebmartin.kata.tennis.presentation;

import fr.sebmartin.kata.tennis.domain.GameScore;
import fr.sebmartin.kata.tennis.domain.Player;
import fr.sebmartin.kata.tennis.domain.TennisMatch;
import fr.sebmartin.kata.tennis.service.Display;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;

import static fr.sebmartin.kata.tennis.domain.Player.PLAYER_ONE;
import static fr.sebmartin.kata.tennis.domain.Player.PLAYER_TWO;

public class ConsoleDisplay implements Display {

    private PrintWriter printer;

    public ConsoleDisplay() {
        this(new PrintWriter(System.out, true));
    }

    public ConsoleDisplay(PrintWriter printer) {
        this.printer = printer;
    }

    @Override
    public void playerWonPoint(Player winner) {
        printer.println(String.format("%s win 1 point", winner));
    }

    @Override
    public void matchUpdated(TennisMatch match) {
        displayGameScores(match);
        displaySetScores(match);
        displayTieBreakScores(match);

        displayGameWinner(match);
        displayTieBreakWinner(match);
        displaySetWinner(match);
        displayMatchWinner(match);

        printer.println();
    }

    private void displayGameScores(TennisMatch match) {
        Map<Player, GameScore> scores = match.game().scores();

        printer.println(String.format("Game: %s - %s", scores.get(PLAYER_ONE), scores.get(PLAYER_TWO)));
    }

    private void displaySetScores(TennisMatch match) {
        Map<Player, Integer> scores = match.set().scores();

        printer.println(String.format("Set: %s - %s", scores.get(PLAYER_ONE), scores.get(PLAYER_TWO)));
    }

    private void displayTieBreakScores(TennisMatch match) {
        if (match.tieBreak().isPresent()) {
            Map<Player, Integer> scores = match.tieBreak().get().scores();

            printer.println(String.format("Tie Break: %s - %s", scores.get(PLAYER_ONE), scores.get(PLAYER_TWO)));
        }
    }

    private void displayGameWinner(TennisMatch match) {

        boolean tieBreakNotPresentOrEachPlayerHasTieBreakScoreOf0AndTieBreakNotWon = !match.tieBreak().isPresent() ||
                (
                        Arrays.stream(Player.values())
                        .map(player -> match.tieBreak().get().scores().get(player))
                        .allMatch(score -> score == 0) &&
                                !match.tieBreak().get().winner().isPresent()
                );

        if (match.game().winner().isPresent() && tieBreakNotPresentOrEachPlayerHasTieBreakScoreOf0AndTieBreakNotWon) {
            printer.println(String.format("%s won the game", match.game().winner().get()));
        }
    }

    private void displayTieBreakWinner(TennisMatch match) {
        if (match.tieBreak().isPresent() && match.tieBreak().get().winner().isPresent()) {
            printer.println(String.format("%s won the tie break", match.tieBreak().get().winner().get()));
        }
    }

    private void displaySetWinner(TennisMatch match) {
        if (match.set().winner().isPresent()) {
            printer.println(String.format("%s won the set", match.set().winner().get()));
        }
    }

    private void displayMatchWinner(TennisMatch match) {
        if (match.winner().isPresent()) {
            printer.println(String.format("%s won the match", match.winner().get()));
        }
    }

}
