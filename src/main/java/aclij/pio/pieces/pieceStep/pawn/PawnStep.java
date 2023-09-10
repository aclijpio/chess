package aclij.pio.pieces.pieceStep.pawn;

import aclij.pio.Color;
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
    public boolean step(Piece targetPiece) {
        this.pawn.setPawnStep(new PawnStepped(this.pawn));
        int dFile = Math.abs(
                (this.pawn.coordinates.file.ordinal() + 1) - (targetPiece.coordinates.file.ordinal() + 1)
        );
        int dRank = this.pawn.coordinates.rank - targetPiece.coordinates.rank;
        boolean isEnemy = this.pawn.isEnemy(targetPiece);
        if(this.pawn.color == Color.BLACK)
            return (isEnemy && (dFile == 1 && dRank == 1)) ||
                    (!isEnemy && (dFile == 0 && (dRank == 1 || dRank == 2)));
        return  (isEnemy && (dFile == 1 && dRank == -1)) ||
                (!isEnemy && (dFile == 0 && (dRank == -1 || dRank == -2)));
    }

    @Override
    public int [][] getMovementRules() {
        return this.pawn.color == Color.WHITE ? MOVEMENT_RULES_WHITE : MOVEMENT_RULES_BLACK;
    }
}
