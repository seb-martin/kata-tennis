package fr.sebmartin.kata.tennis;

import fr.sebmartin.kata.tennis.domain.GameOverException;
import fr.sebmartin.kata.tennis.domain.TennisMatch;
import fr.sebmartin.kata.tennis.presentation.TennisController;
import fr.sebmartin.kata.tennis.service.TennisService;

import java.io.*;

public class TennisApp {

    public static void main(String[] args) throws IOException, GameOverException {

        TennisMatch match = new TennisMatch();

        TennisService service = new TennisService(match);

        Reader reader = new InputStreamReader(System.in);

        Writer writer = new OutputStreamWriter(System.out);

        TennisController dialog = new TennisController(service, reader, writer);

        dialog.startMatch();

    }

}
