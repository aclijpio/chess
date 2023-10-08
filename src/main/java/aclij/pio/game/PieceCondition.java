package aclij.pio.game;

import aclij.pio.board.pieces.Piece;

@FunctionalInterface
public interface PieceCondition {
    boolean cond(Piece piece);
}
