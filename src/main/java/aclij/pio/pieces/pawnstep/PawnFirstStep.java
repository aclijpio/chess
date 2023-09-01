package aclij.pio.pieces.pawnstep;

import aclij.pio.Color;
import aclij.pio.Coordinates;
import aclij.pio.pieces.Pawn;

public class PawnFirstStep implements PawnStep{
    private final Pawn pawn;

    public PawnFirstStep(Pawn pawn) {
        this.pawn = pawn;
    }

    @Override
    public boolean step(boolean isEnemy, int dFile, int dRank) {
        pawn.setPawnStep(new PawnOtherStep(pawn));
        if(pawn.color == Color.BLACK)
            return (isEnemy && (dFile == 1 && dRank == 1)) ||
                    (!isEnemy && (dFile == 0 && (dRank == 1 || dRank == 2)));
        return  (isEnemy && (dFile == 1 && dRank == -1)) ||
                (!isEnemy && (dFile == 0 && (dRank == -1 || dRank == -2)));
    }

    @Override
    public boolean isFirst() {
        return true;
    }
}
