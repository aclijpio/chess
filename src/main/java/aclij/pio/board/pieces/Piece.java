package aclij.pio.board.pieces;

import aclij.pio.board.pieces.coordinates.Color;
import aclij.pio.board.pieces.coordinates.Coordinates;
import aclij.pio.board.pieces.coordinates.File;
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
    public abstract Set<List<Coordinates>> getAllPossibleMoveCoordinates();
    public abstract Set<List<Coordinates>> getAllPossibleMoveCoordinatesUntilColor(Board board);

    public abstract boolean checkAvailableMove(Piece targetSquare);
    public boolean isEnemy(Piece piece){
        return isEnemy(piece.color);
    }
    public boolean isEnemy(Color color){
        if (color == null) return false;
        return this.color != color;
    }
    public boolean isNotAboard(int file, int rank){
        return  (file >= 0 && file < 8) &&
                (rank > 0 && rank < 9);
    }
    public Set<Coordinates> getPossibleAttackCoordinates(Board board){
        return getAllPossibleMoveCoordinatesUntilColor(board)
                .stream()
                .flatMap(List::stream)
                .filter(coordinates -> board.isSquareOccupied(coordinates) && board.getPiece(coordinates).isEnemy(this.color))
                .collect(Collectors.toSet());
    }
    public boolean isAttacks(Board board){
        return getPossibleAttackCoordinates(board)
                .stream()
                .anyMatch(Objects::nonNull);
    }
    public boolean isAttacksPieceClass(Board board, Class<? extends Piece> pieceClass){
        return this.getPossibleAttackCoordinates(board)
                .stream()
                .map(board::getPiece)
                .anyMatch(x -> x.getClass().equals(pieceClass));
    }
    public boolean isAttacksCoordinates(Board board, Coordinates coordinates){
        return getPossibleAttackCoordinates(board).stream()
                .anyMatch(x -> x.equals(coordinates));
    }
    protected Set<List<Coordinates>> getAbstractSinglePossibleMoveCoordinates(int [][] MOVEMENT_RULES){
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
            }
        }
        return moveCoordinatesLines;
    }
    protected Set<List<Coordinates>> getAbstractMultiplyPossibleMoveCoordinates(int [][] MOVEMENT_RULES){
        Set<List<Coordinates>> moveCoordinatesLines = new HashSet<>();
        int file = this.coordinates.file.ordinal();
        for (int [] movementRule :
                MOVEMENT_RULES) {
            int dFile = file;
            int dRank = this.coordinates.rank;
            List<Coordinates> moveCoordinates = new ArrayList<>();
            while(isNotAboard(dFile += movementRule[0], dRank += movementRule[1])) {
                Coordinates selectedCoordinates = new Coordinates(File.values()[dFile], dRank);
                moveCoordinates.add(selectedCoordinates);
            }
            moveCoordinatesLines.add(moveCoordinates);
        }
        return moveCoordinatesLines;
    }
    protected Set<List<Coordinates>> getAbstractSinglePossibleMoveCoordinates(Board board, int [][] MOVEMENT_RULES){
        Set<List<Coordinates>> moveCoordinatesLines = new HashSet<>();
        int file = this.coordinates.file.ordinal();
        int rank = this.coordinates.rank;
        for (int [] movementRule :
                MOVEMENT_RULES) {
            int dFile = file + movementRule[0];
            int dRank = rank + movementRule[1];
            if (isNotAboard(dFile, dRank)) {
                Coordinates selectedCoordinates = new Coordinates(File.values()[dFile], dRank);
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
    protected Set<List<Coordinates>> getAbstractMultiplyPossibleMoveCoordinates(Board board, int [][] MOVEMENT_RULES) {
        Set<List<Coordinates>> moveCoordinatesLines = new HashSet<>();
        int file = this.coordinates.file.ordinal();
        for (int[] movementRule :
                MOVEMENT_RULES) {
            int dFile = file;
            int dRank = this.coordinates.rank;
            List<Coordinates> moveCoordinates = new ArrayList<>();
            while (isNotAboard(dFile += movementRule[0], dRank += movementRule[1])) {
                Coordinates selectedCoordinates = new Coordinates(File.values()[dFile], dRank);
                if (board.isSquareOccupied(selectedCoordinates)) {
                    if (board.getPiece(selectedCoordinates).color != this.color)
                        moveCoordinates.add(selectedCoordinates);
                    break;
                }
                moveCoordinates.add(selectedCoordinates);
            }
            moveCoordinatesLines.add(moveCoordinates);
        }
            return moveCoordinatesLines;
        }

}
