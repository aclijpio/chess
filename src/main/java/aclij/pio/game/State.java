package aclij.pio.game;

public enum State {
    ACTIVE,
    CHECK,
    MATE,
    DRAW,
    CANCELLED,
    PIECE_CHOOSING,
    PAUSED;
    public boolean isActive(){
        return this == ACTIVE;
    }
}
