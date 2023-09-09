package aclij.pio.pieces;

import aclij.pio.Color;
import aclij.pio.Coordinates;

import java.util.List;
import java.util.Set;

public class King extends Piece{
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
    public King(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }

    @Override
    public Set<List<Coordinates>> getPossibleMoveCoordinates() {
        return super.getAbstractMultiplyPossibleMoveCoordinates(MOVEMENT_RULES);
    }

    @Override
    public boolean checkAvailableMove(Piece targetSquare) {
        int dFile = Math.abs((this.coordinates.file.ordinal() + 1) - (targetSquare.coordinates.file.ordinal() + 1));
        int dRank = Math.abs(this.coordinates.rank - targetSquare.coordinates.rank);
        return  this.coordinates.file == targetSquare.coordinates.file ||
                this.coordinates.rank.equals(targetSquare.coordinates.rank)||
                (dFile == dRank);
    }



}
