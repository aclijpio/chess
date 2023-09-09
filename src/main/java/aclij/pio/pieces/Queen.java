package aclij.pio.pieces;

import aclij.pio.Color;
import aclij.pio.Coordinates;

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
    public Set<List<Coordinates>> getPossibleMoveCoordinates() {
        return super.getAbstractSinglePossibleMoveCoordinates(MOVEMENT_RULES);
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
