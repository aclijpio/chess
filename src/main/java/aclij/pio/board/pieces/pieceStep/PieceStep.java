package aclij.pio.board.pieces.pieceStep;

import aclij.pio.board.pieces.Piece;

public interface PieceStep {
    boolean isPieceStepped();
    boolean step(Piece targetSquare);
    boolean stepWithoutChange(Piece targetSquare);
    int [][] getMovementRules();

}
