package aclij.pio.pieces;

import aclij.pio.Color;
import aclij.pio.Coordinates;

import java.util.Set;

public class Bishop extends Piece {
    public Bishop(Color color, Coordinates coordinates) {
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
        return dFile == dRank;
    }
    @Override
    public boolean isBishop() {
        return true;
    }
}
