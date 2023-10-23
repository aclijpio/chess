package aclij.pio.board.pieces.pieceStep;

import aclij.pio.board.pieces.Piece;
import aclij.pio.board.pieces.coordinates.Color;

public abstract class PieceStep {
    public static final int [][] ATTACK_RULES_WHITE = {
            {1, 1},
            {-1, 1}
    };
    public static final int [][] ATTACK_RULES_BLACK = {
            {1, -1},
            {-1, -1}
    };
    private final Piece pawn;

    public PieceStep(Piece pawn) {
        this.pawn = pawn;
    }

    public abstract boolean isPieceStepped();
    public abstract boolean step(Piece targetSquare);
    public abstract boolean stepWithoutChange(Piece targetSquare);
    public abstract int [][] getMovementRules();
    public int[][] getAttackRules() {
        return this.pawn.color == Color.WHITE ? ATTACK_RULES_WHITE : ATTACK_RULES_BLACK;
    }
}
