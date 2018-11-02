package fr.sebmartin.kata.tennis.domain;

public enum Player {
    PLAYER_ONE,
    PLAYER_TWO;

    public Player competing() {
        return this.equals(PLAYER_ONE) ? PLAYER_TWO : PLAYER_ONE;
    }
}
