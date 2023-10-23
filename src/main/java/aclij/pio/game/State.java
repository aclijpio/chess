package aclij.pio.game;

public enum State {
    ACTIVE,
    CHECK,
    MATE,
    COMPLETED,
    DRAW,
    CANCELLED,
    PAUSED;
    public boolean isActive(){
        return this == ACTIVE;
    }
}
