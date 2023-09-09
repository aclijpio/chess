package aclij.pio.pieces;

import aclij.pio.Color;
import aclij.pio.Coordinates;
import aclij.pio.pieces.pieceStep.pawn.PawnStep;
import aclij.pio.pieces.pieceStep.PieceStep;
import aclij.pio.pieces.pieceStep.pawn.PawnStepped;

import java.util.List;
import java.util.Set;

public class Pawn extends Piece{
    PieceStep pawnStep =  new PawnStep(this);
    public Pawn(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }
    public void setPawnStep(PawnStepped pawnStep) {
        this.pawnStep = pawnStep;
    }
    @Override
    public Set<List<Coordinates>> getPossibleMoveCoordinates() {
        return super.getAbstractSinglePossibleMoveCoordinates(pawnStep.getMovementRules());
    }
    @Override
    public boolean checkAvailableMove(Piece targetSquare) {
        return pawnStep.step(targetSquare);
    }
}
