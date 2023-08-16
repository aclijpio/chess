package aclij.pio.pieces;

import aclij.pio.Color;
import aclij.pio.Coordinates;

import java.util.Set;

public class Knight extends Piece {

    public Knight(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }

    @Override
    public Set<Coordinates> getPossibleMoveCoordinates() {
        return null;
    }

    @Override
    public boolean checkAvailableMove(Coordinates coordinates, boolean isEnemy) {
        int dFile = Math.abs((this.coordinates.file.ordinal() + 1) - (coordinates.file.ordinal() + 1));
        int dRank = Math.abs(this.coordinates.rank - coordinates.rank);
        return  (dFile == 2 && dRank == 1) ||
                (dFile == 1 && dRank == 2);
    }


}
