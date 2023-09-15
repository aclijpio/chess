package aclij.pio.pieces.pieceStep;

import aclij.pio.pieces.Piece;

public interface PieceStep {
    boolean isPieceStepped();
    boolean step(Piece targetSquare);
    boolean stepWithoutChange(Piece targetSquare);
    int [][] getMovementRules();

}
