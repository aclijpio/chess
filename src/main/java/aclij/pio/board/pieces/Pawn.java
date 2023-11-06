package aclij.pio.board.pieces;

import aclij.pio.board.pieces.coordinates.Color;
import aclij.pio.board.pieces.coordinates.Coordinates;
import aclij.pio.board.pieces.coordinates.File;
import aclij.pio.board.Board;
import aclij.pio.board.pieces.pieceStep.pawn.PawnStep;
import aclij.pio.board.pieces.pieceStep.PieceStep;
import aclij.pio.board.pieces.pieceStep.pawn.PawnStepped;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        super.getAbstractSinglePossibleMoveCoordinates( pawnStep.getMovementRules()).stream()
                .flatMap(List::stream)
                .forEach(System.out::println);
        return super.getAbstractSinglePossibleMoveCoordinates(pawnStep.getMovementRules());
    }
    @Override
    public Set<List<Coordinates>> getAllPossibleMoveCoordinatesUntilColor(Board board){
        Set<List<Coordinates>> moveCoordinatesLines = new HashSet<>();
        int file = this.coordinates.file.ordinal();
        int rank = this.coordinates.rank;
        for (int [] movementRule :
                pawnStep.getMovementRules()) {
            int dFile = file + movementRule[0];
            int dRank = rank + movementRule[1];
            if (isNotAboard(dFile, dRank)) {
                Coordinates selectedCoordinates = new Coordinates(File.values()[dFile], dRank);
                if (movementRule[1] == 2 && board.isSquareOccupied(new Coordinates(selectedCoordinates.file, dRank - 1))){
                    continue;
                } else if (movementRule[1] == -2 && board.isSquareOccupied(new Coordinates(selectedCoordinates.file, dRank + 1))){
                    continue;
                }
                if (board.isSquareOccupied(selectedCoordinates)){
                    if(board.getPiece(selectedCoordinates).color != this.color)
                        moveCoordinatesLines.add(List.of(selectedCoordinates));
                    continue;
                }
                moveCoordinatesLines.add(List.of(selectedCoordinates));
            }
        }
        return moveCoordinatesLines;
    }
    @Override
    public boolean checkAvailableMove(Piece targetSquare) {
        return pawnStep.step(targetSquare);
    }

    @Override
    public Set<Coordinates> getPossibleAttackCoordinates(Board board) {
        return super.getAbstractSinglePossibleMoveCoordinates(board, pawnStep.getAttackRules())
                .stream()
                .flatMap(List::stream)
                .filter(coordinates -> board.isSquareOccupied(coordinates) && board.getPiece(coordinates).isEnemy(this.color))
                .collect(Collectors.toSet());
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
                else if (isAvailableMove(board.wrapCoordinates(selectedCoordinates)))
                    moveCoordinatesLines.add(List.of(selectedCoordinates)
                    );
            }
        }
        return moveCoordinatesLines;
    }
}
