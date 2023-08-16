package aclij.pio.pieces;

import aclij.pio.Color;
import aclij.pio.Coordinates;
import aclij.pio.File;

import java.util.HashSet;
import java.util.Set;

public class Pawn extends Piece{

    public Pawn(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }

    @Override
    public Set<Coordinates> getPossibleMoveCoordinates() {
        Set<Coordinates> coordinatesSet = new HashSet<>();
        if (this.coordinates.file != File.H)coordinatesSet.add(new Coordinates(File.values()[this.coordinates.file.ordinal() + 1], this.coordinates.rank + 1));
        if (this.coordinates.file != File.A)coordinatesSet.add(new Coordinates(File.values()[this.coordinates.file.ordinal() - 1], this.coordinates.rank + 1));
        coordinatesSet.add(new Coordinates(File.values()[this.coordinates.file.ordinal()], this.coordinates.rank + 1));
        return coordinatesSet;
    }

    @Override
    public boolean checkAvailableMove(Coordinates coordinates, boolean isEnemy) {
        int dFile = Math.abs((this.coordinates.file.ordinal() + 1) - (coordinates.file.ordinal() + 1));
        int dRank = this.coordinates.rank - coordinates.rank;
        if(this.color == Color.BLACK)
            return (isEnemy && (dFile == 1 && dRank == 1)) ||
                    (!isEnemy && (dFile == 0 && dRank == 1));
        return  (isEnemy && (dFile == 1 && dRank == -1)) ||
                (!isEnemy && (dFile == 0 && dRank == -1));
    }
}
