package fr.sebmartin.kata.tennis.domain;

public enum GameScore {
    LOVE("0"), FIFTEEN("15"), THIRTY("30"), FORTY("40"), DEUCE("DEUCE"), ADVANTAGE("ADV");

    private String label;

    GameScore(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
