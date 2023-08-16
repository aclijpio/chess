package aclij.pio.pieces;

import aclij.pio.Color;
import aclij.pio.Coordinates;

import java.util.HashSet;
import java.util.Set;

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
    public abstract Set<Coordinates> getPossibleMoveCoordinates();

    public abstract boolean checkAvailableMove(Coordinates coordinates, boolean isEnemy);
}
