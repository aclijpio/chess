package aclij.pio.game.dto;

import aclij.pio.board.pieces.Piece;
import aclij.pio.game.State;

import java.util.Optional;

public class CheckMateResult {
    private final State state;
    private Optional<Piece> piece;

    public CheckMateResult(State state, Optional<Piece> piece) {
        this.state = state;
        this.piece = piece;
    }

    public CheckMateResult(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public Optional<Piece> getPiece() {
        return piece;
    }
}
