package fr.sebmartin.kata.tennis.presentation;

import fr.sebmartin.kata.tennis.domain.GameOverException;
import fr.sebmartin.kata.tennis.domain.GameScore;
import fr.sebmartin.kata.tennis.domain.Player;
import fr.sebmartin.kata.tennis.domain.TennisMatch;
import fr.sebmartin.kata.tennis.service.TennisDisplay;
import fr.sebmartin.kata.tennis.service.TennisService;

import java.io.*;
import java.util.*;

import static fr.sebmartin.kata.tennis.domain.Player.PLAYER_ONE;
import static fr.sebmartin.kata.tennis.domain.Player.PLAYER_TWO;

public class TennisController implements TennisDisplay {

    private static final Map<String, Player> RESPONSE_PLAYER_MAP = new HashMap<>();
    static {
        RESPONSE_PLAYER_MAP.put("1", PLAYER_ONE);
        RESPONSE_PLAYER_MAP.put("2", PLAYER_TWO);
    }

    private final TennisService service;
    private final PrintWriter printer;
    private final BufferedReader reader;

    private boolean matchEnded = false;

    public TennisController(TennisService service, Reader input, Writer output) {
        this.service = Objects.requireNonNull(service);
        this.reader = new BufferedReader(Objects.requireNonNull(input));
        this.printer = new PrintWriter(output, true);

        this.service.register(this);
    }

    public void startMatch() throws IOException, GameOverException {

        while (!matchEnded) {

            String player = askPointWinner();

            mark(player);

        }
    }

    private String askPointWinner() throws IOException {

        printer.print("Enter point winner (1 or 2): ");
        printer.flush();

        return reader.readLine().trim();

    }

    private void mark(String player) throws GameOverException {

        if (RESPONSE_PLAYER_MAP.containsKey(player)) {
            this.service.mark(RESPONSE_PLAYER_MAP.get(player));
        }
    }

    @Override
    public void playerWonPoint(Player pointWinner, TennisMatch matchState) {
        displayPointWinner(pointWinner);

        displayGameScores(matchState);
        displaySetScores(matchState);
        displayTieBreakScores(matchState);

        displayGameWinner(matchState);
        displayTieBreakWinner(matchState);
        displaySetWinner(matchState);
        displayMatchWinner(matchState);

        displayEmptyLine();

        matchEnded = matchState.winner().isPresent();
    }

    private void displayEmptyLine() {
        printer.println();
    }

    private void displayPointWinner(Player pointWinner) {
        printer.println(String.format("%s win 1 point", pointWinner));
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
