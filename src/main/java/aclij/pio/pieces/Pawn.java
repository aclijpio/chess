package aclij.pio.pieces;

import aclij.pio.Color;
import aclij.pio.Coordinates;
import aclij.pio.File;
import aclij.pio.pieces.pawnstep.PawnFirstStep;
import aclij.pio.pieces.pawnstep.PawnStep;

import java.util.HashSet;
import java.util.Set;

public class Pawn extends Piece{
    PawnStep pawnStep =  new PawnFirstStep(this);
    public Pawn(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }
    public void setPawnStep(PawnStep pawnStep) {
        this.pawnStep = pawnStep;
    }

    @Override
    public Set<Coordinates> getPossibleMoveCoordinates() {
        Set<Coordinates> coordinatesSet = new HashSet<>();
        if (this.coordinates.file != File.H)coordinatesSet.
                add(new Coordinates(
                        File.values()[this.coordinates.file.ordinal() + 1],
                        this.coordinates.rank + 1));
        if (this.coordinates.file != File.A)coordinatesSet.
                add(new Coordinates(
                        File.values()[this.coordinates.file.ordinal() - 1],
                        this.coordinates.rank + 1));
        coordinatesSet.add(new Coordinates(File.values()[this.coordinates.file.ordinal()], this.coordinates.rank + 1));
        return coordinatesSet;
    }
    public Set<Integer[]> getSetPossibleMoves(){
        {
            Set<Integer[]> possibleMoves = new HashSet<>();
            int dRank = this.color.equals(Color.WHITE) ? 1 : -1;
            possibleMoves.add(new Integer[]{0, dRank});
            if(pawnStep.isFirst())
                possibleMoves.add(new Integer[]{0, dRank * 2});
        }
    }


    @Override
    public boolean checkAvailableMove(Coordinates coordinates, boolean isEnemy) {
        int dFile = Math.abs((this.coordinates.file.ordinal() + 1) - (coordinates.file.ordinal() + 1));
        int dRank = this.coordinates.rank - coordinates.rank;
        return pawnStep.step(isEnemy, dFile, dRank);
    }
}
