package fr.sebmartin.kata.tennis.presentation;

import fr.sebmartin.kata.tennis.domain.GameOverException;
import fr.sebmartin.kata.tennis.domain.Player;
import fr.sebmartin.kata.tennis.domain.TennisMatch;
import fr.sebmartin.kata.tennis.service.TennisService;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static fr.sebmartin.kata.tennis.domain.Player.PLAYER_ONE;
import static fr.sebmartin.kata.tennis.domain.Player.PLAYER_TWO;
import static org.junit.Assert.*;

public class TennisControllerIT {

    private static final Map<Player, String> PLAYER_RESPONSE_MAP;
    static {
        PLAYER_RESPONSE_MAP = new EnumMap<>(Player.class);
        PLAYER_RESPONSE_MAP.put(PLAYER_ONE, "1");
        PLAYER_RESPONSE_MAP.put(PLAYER_TWO, "2");
    }

    private static final Collector<CharSequence,?,String> RESPONSE_COLLECTOR = Collectors.joining("\n", "", "\n");

    private TennisService service;

    @Before
    public void setUp() {

        service = new TennisService(new TennisMatch());

    }

    @Test
    public void smallerMatch() throws IOException, GameOverException {

        String exchanges = winGames(PLAYER_ONE, 7)
                .map(PLAYER_RESPONSE_MAP::get)
                .collect(RESPONSE_COLLECTOR);

        Reader reader = new StringReader(exchanges);

        Writer writer = new StringWriter();

        TennisController dialog = new TennisController(service, reader, writer);

        dialog.startMatch();

        assertFalse(writer.toString().isEmpty());

        System.out.println(writer.toString());

    }

    @Test
    public void sprint2_us2_match() throws IOException, GameOverException {

        Reader reader = new StringReader(sprint2_us2_sample());

        Writer writer = new StringWriter();

        TennisController dialog = new TennisController(service, reader, writer);

        dialog.startMatch();

        assertFalse(writer.toString().isEmpty());

        System.out.println(writer.toString());

    }


    private String sprint2_us2_sample() {

        return Stream.of(
                winGames(PLAYER_ONE, 5),
                winGames(PLAYER_TWO, 6),
                winPoints(PLAYER_ONE, 3),
                winPoints(PLAYER_TWO, 1),

                winPoints(PLAYER_ONE, 2),
                winPoints(PLAYER_TWO, 1),
                winPoints(PLAYER_ONE, 2),
                winPoints(PLAYER_TWO, 1),
                winPoints(PLAYER_ONE, 4)

        ).flatMap(Function.identity())
                .map(PLAYER_RESPONSE_MAP::get)
                .collect(RESPONSE_COLLECTOR);

    }


    private Stream<Player> winGames(Player player, int numberOfGames) {
        return IntStream.rangeClosed(1, numberOfGames)
                .mapToObj(n -> winPoints(player, 4))
                .flatMap(Function.identity());
    }

    private Stream<Player> winPoints(Player p, int numberOfPoints) {
        return IntStream.rangeClosed(1, numberOfPoints).mapToObj(n -> p);
    }
}