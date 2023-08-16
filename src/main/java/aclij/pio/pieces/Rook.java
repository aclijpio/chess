package aclij.pio.pieces;

import aclij.pio.Color;
import aclij.pio.Coordinates;

import java.util.Set;

public class Rook extends Piece{
    public Rook(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }

    @Override
    public Set<Coordinates> getPossibleMoveCoordinates() {
        return null;
    }

    @Override
    public boolean checkAvailableMove(Coordinates coordinates, boolean isEnemy) {
        return  this.coordinates.file == coordinates.file ||
                this.coordinates.rank.equals(coordinates.rank);
    }

}
