package aclij.pio.pieces.pawnstep;

import aclij.pio.Color;
import aclij.pio.Coordinates;
import aclij.pio.pieces.Pawn;

class PawnOtherStep implements PawnStep{
    private final Pawn pawn;

    public PawnOtherStep(Pawn pawn) {
        this.pawn = pawn;
    }

    @Override
    public boolean step(boolean isEnemy, int dFile, int dRank) {
        if(pawn.color == Color.BLACK)
            return (isEnemy && (dFile == 1 && dRank == 1)) ||
                    (!isEnemy && (dFile == 0 && dRank == 1));
        return  (isEnemy && (dFile == 1 && dRank == -1)) ||
                (!isEnemy && (dFile == 0 && dRank == -1));
    }

    @Override
    public boolean isFirst() {
        return false;
    }
}
