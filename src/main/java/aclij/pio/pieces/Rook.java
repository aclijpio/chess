package aclij.pio.pieces;

import aclij.pio.Color;
import aclij.pio.Coordinates;

import java.util.List;
import java.util.Set;

public class Rook extends Piece{
    private static final int [][] MOVEMENT_RULES = {
            {1, 0},
            {0, 1},
            {0, -1},
            {-1, 0},
    };
    public Rook(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }

    @Override
    public Set<List<Coordinates>> getPossibleMoveCoordinates() {
        return super.getAbstractMultiplyPossibleMoveCoordinates(MOVEMENT_RULES);
    }

    @Override
    public boolean checkAvailableMove(Piece targetSquare) {
        return  this.coordinates.file == targetSquare.coordinates.file ||
                this.coordinates.rank.equals(targetSquare.coordinates.rank);
    }


}
