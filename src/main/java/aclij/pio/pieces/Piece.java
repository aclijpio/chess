package aclij.pio.pieces;

import aclij.pio.Color;
import aclij.pio.Coordinates;
import aclij.pio.File;
import aclij.pio.board.Board;

import java.util.*;
import java.util.stream.Collectors;

public abstract class Piece {
    public final Color color;
    public Coordinates coordinates;
    public Piece(Color color, Coordinates coordinates) {
        this.color = color;
        this.coordinates = coordinates;
    }
    public Piece moveTo(Coordinates coordinates){
        this.coordinates = coordinates;
        return this;
    }
    public abstract Set<List<Coordinates>> getPossibleMoveCoordinates();

    public abstract boolean checkAvailableMove(Piece targetSquare);
    public boolean isQueen(){
        return false;
    }
    public boolean isEnemy(Piece piece){
        return isEnemy(piece.color);
    }
    public boolean isEnemy(Color color){
        if (color == null) return false;
        return !(this.color == color);
    }
    public boolean isNotAboard(int file, int rank){
        return  (file >= 0 && file < 8) &&
                (rank > 0 && rank < 9);
    }
    public Set<Coordinates> getPossibleAttackCoordinates(Board board){
        return getPossibleMoveCoordinates()
                .stream()
                .flatMap(List::stream)
                .filter(coordinates -> board.isSquareOccupied(coordinates) && board.getPiece(coordinates).isEnemy(this.color))
                .collect(Collectors.toSet());
    }
    public boolean isUnderAttack(Board board){
        return getPossibleMoveCoordinates()
                .stream()
                .flatMap(List::stream)
                .filter(board::isSquareOccupied)
                .map(board::getPiece)
                .anyMatch(piece -> piece.isEnemy(this.color));
    }
    int count = 0;
    protected final Set<List<Coordinates>> getAbstractSinglePossibleMoveCoordinates(int [][] MOVEMENT_RULES){
        Set<List<Coordinates>> moveCoordinatesLines = new HashSet<>();
        int file = this.coordinates.file.ordinal();
        int rank = this.coordinates.rank;
        for (int [] movementRule :
                MOVEMENT_RULES) {
            int dFile = file + movementRule[0];
            int dRank = rank + movementRule[1];
            if (isNotAboard(dFile, dRank)) {
                moveCoordinatesLines.add(List.of(
                        new Coordinates(File.values()[dFile], dRank)
                    )
                );
                count++;
            }
        }
        return moveCoordinatesLines;
    }
    protected final Set<List<Coordinates>> getAbstractMultiplyPossibleMoveCoordinates(int [][] MOVEMENT_RULES){
        Set<List<Coordinates>> moveCoordinatesLines = new HashSet<>();
        int file = this.coordinates.file.ordinal();
        for (int [] movementRule :
                MOVEMENT_RULES) {
            int dFile = file;
            int dRank = this.coordinates.rank;
            List<Coordinates> moveCoordinates = new ArrayList<>();
            while(isNotAboard(dFile, dRank)){
                moveCoordinates.add(new Coordinates(File.values()[dFile += movementRule[0]], dRank += movementRule[1]));
            }
            moveCoordinatesLines.add(moveCoordinates);
        }
        return moveCoordinatesLines;
    }
}
