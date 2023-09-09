package aclij.pio.pieces.pieceStep;

import aclij.pio.Coordinates;
import aclij.pio.pieces.Piece;

public interface PieceStep {
    boolean isPieceStepped();
    boolean step(Piece targetPiece);
    int [][] getMovementRules();

}
