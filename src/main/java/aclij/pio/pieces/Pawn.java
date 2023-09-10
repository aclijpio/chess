package aclij.pio.pieces;

import aclij.pio.Color;
import aclij.pio.Coordinates;
import aclij.pio.board.Board;
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
    public Set<List<Coordinates>> getAllPossibleMoveCoordinates() {
        return super.getAbstractSinglePossibleMoveCoordinates(pawnStep.getMovementRules());
    }
    @Override
    public Set<List<Coordinates>> getAllPossibleMoveCoordinatesUntilColor(Board board){
        return super.getAbstractSinglePossibleMoveCoordinates(board, pawnStep.getMovementRules());
    }
    @Override
    public boolean checkAvailableMove(Piece targetSquare) {
        return pawnStep.step(targetSquare);
    }
}
