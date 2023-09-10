package aclij.pio.pieces;

import aclij.pio.Color;
import aclij.pio.Coordinates;
import aclij.pio.board.Board;

import java.util.List;
import java.util.Set;

public class Queen extends Piece{
    private static final int [][] MOVEMENT_RULES = {
            {1, 1},
            {1, 0},
            {1, -1},
            {0, 1},
            {0, -1},
            {-1, 1},
            {-1, 0},
            {-1, -1}
    };
    public Queen(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }

    @Override
    public boolean isQueen() {
        return true;
    }

    @Override
    public Set<List<Coordinates>> getAllPossibleMoveCoordinates() {
        return super.getAbstractSinglePossibleMoveCoordinates(MOVEMENT_RULES);
    }
    @Override
    public Set<List<Coordinates>> getAllPossibleMoveCoordinatesUntilColor(Board board){
        return super.getAbstractSinglePossibleMoveCoordinates(board, MOVEMENT_RULES);
    }

    @Override
    public boolean checkAvailableMove(Piece targetSquare) {
        int dFile = Math.abs((this.coordinates.file.ordinal() + 1) - (targetSquare.coordinates.file.ordinal() + 1));
        int dRank = Math.abs(this.coordinates.rank - targetSquare.coordinates.rank);
        return  (dFile == 1 && dRank == 1) ||
                (dFile == 1 && dRank == 0) ||
                (dFile == 0 && dRank == 1);
    }

}
