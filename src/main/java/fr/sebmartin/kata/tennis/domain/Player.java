package fr.sebmartin.kata.tennis.domain;

public enum Player {
    PLAYER_ONE("Player 1"),
    PLAYER_TWO("Player 2");

    private String label;

    Player(String label) {
        this.label = label;
    }

    public Player opponent() {
        return this.equals(PLAYER_ONE) ? PLAYER_TWO : PLAYER_ONE;
    }

    @Override
    public String toString() {
        return label;
    }
}
