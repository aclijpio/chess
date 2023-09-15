package aclij.pio.pieces;

import aclij.pio.coordinates.Color;
import aclij.pio.coordinates.Coordinates;
import aclij.pio.coordinates.File;
import aclij.pio.board.Board;
import aclij.pio.pieces.pieceStep.pawn.PawnStep;
import aclij.pio.pieces.pieceStep.PieceStep;
import aclij.pio.pieces.pieceStep.pawn.PawnStepped;

import java.util.HashSet;
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
        return super.getAbstractSinglePossibleMoveCoordinates(pawnStep.getMovementRules());
    }
    @Override
    public boolean checkAvailableMove(Piece targetSquare) {
        return pawnStep.step(targetSquare);

    }
    protected boolean isAvailableMove(Piece targetSquare){
        return pawnStep.stepWithoutChange(targetSquare);
    }

    @Override
    protected Set<List<Coordinates>> getAbstractSinglePossibleMoveCoordinates(Board board, int[][] MOVEMENT_RULES) {
        Set<List<Coordinates>> moveCoordinatesLines = new HashSet<>();
        int file = this.coordinates.file.ordinal();
        int rank = this.coordinates.rank;
        for (int [] movementRule :
                MOVEMENT_RULES) {
            int dFile = file + movementRule[0];
            int dRank = rank + movementRule[1];
            if (isNotAboard(dFile, dRank)) {
                Coordinates selectedCoordinates = new Coordinates(File.values()[dFile], dRank);
                if (board.isSquareOccupied(selectedCoordinates) && board.getPiece(selectedCoordinates).color == this.color)
                    break;
                else if (isAvailableMove(board.tryGetPiece(selectedCoordinates)))
                    moveCoordinatesLines.add(List.of(selectedCoordinates)
                    );
            }
        }
        return moveCoordinatesLines;
    }
}
