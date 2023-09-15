package aclij.pio.pieces.pieceStep.pawn;

import aclij.pio.coordinates.Color;
import aclij.pio.pieces.Pawn;
import aclij.pio.pieces.Piece;
import aclij.pio.pieces.pieceStep.PieceStep;

public class PawnStep implements PieceStep {

    public static final int [][] MOVEMENT_RULES_WHITE = {
            {0, 1},
            {0, 2},
            {1, 1},
            {-1, 1}
    };
    public static final int [][] MOVEMENT_RULES_BLACK= {
            {0, -1},
            {0, -2},
            {1, -1},
            {-1, -1}
    };
    private final Pawn pawn;
    public PawnStep(Pawn pawn) {
        this.pawn = pawn;
    }

    @Override
    public boolean isPieceStepped() {
        pawn.setPawnStep(new PawnStepped(this.pawn));
        return false;
    }
    @Override
    public boolean step(Piece targetSquare) {
        int dFile = Math.abs(
                (this.pawn.coordinates.file.ordinal() + 1) - (targetSquare.coordinates.file.ordinal() + 1)
        );
        int dRank = this.pawn.coordinates.rank - targetSquare.coordinates.rank;
        boolean isEnemy = this.pawn.isEnemy(targetSquare);
        if(this.pawn.color == Color.BLACK)
            if ((isEnemy && (dFile == 1 && dRank == 1)) ||
                    (!isEnemy && (dFile == 0 && (dRank == 1 || dRank == 2)))){
                pawn.setPawnStep(new PawnStepped(this.pawn));
                return true;
            }
        if  ((isEnemy && (dFile == 1 && dRank == -1)) ||
                (!isEnemy && (dFile == 0 && (dRank == -1 || dRank == -2)))){
            pawn.setPawnStep(new PawnStepped(this.pawn));
            return true;
        }
        return false;
    }
    @Override
    public boolean stepWithoutChange(Piece targetSquare){
        int dFile = Math.abs(
                (this.pawn.coordinates.file.ordinal() + 1) - (targetSquare.coordinates.file.ordinal() + 1)
        );
        int dRank = this.pawn.coordinates.rank - targetSquare.coordinates.rank;
        boolean isEnemy = this.pawn.isEnemy(targetSquare);
        if(this.pawn.color == Color.BLACK)
            return (isEnemy && (dFile == 1 && dRank == 1)) ||
                    (!isEnemy && (dFile == 0 && (dRank == 1 || dRank == 2)));
        return (isEnemy && (dFile == 1 && dRank == -1)) ||
                (!isEnemy && (dFile == 0 && (dRank == -1 || dRank == -2)));
    }

    @Override
    public int [][] getMovementRules() {
        return this.pawn.color == Color.WHITE ? MOVEMENT_RULES_WHITE : MOVEMENT_RULES_BLACK;
    }
}
