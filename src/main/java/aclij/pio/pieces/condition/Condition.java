package aclij.pio.pieces.condition;

import aclij.pio.pieces.Piece;

@FunctionalInterface
public interface Condition {
    boolean isMet(Piece piece);
}
