package aclij.pio.board.pieces.pieceStep.pawn;

import aclij.pio.board.pieces.Pawn;
import aclij.pio.board.pieces.coordinates.Color;
import aclij.pio.board.pieces.Piece;
import aclij.pio.board.pieces.pieceStep.PieceStep;

public class PawnStep extends PieceStep {

    public static final int [][] MOVEMENT_RULES_WHITE = {
            {0, 1},
            {0, 2},
    };
    public static final int [][] MOVEMENT_RULES_BLACK= {
            {0, -1},
            {0, -2},
    };
    private final Pawn pawn;

    public PawnStep(Piece pawn) {
        super(pawn);
        this.pawn = (Pawn)pawn;
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
        System.out.println(isEnemy);
        System.out.println(dFile);
        System.out.println(dRank);
        if(this.pawn.color == Color.BLACK) {
            if ((isEnemy && (dFile == 1 && dRank == 1)) ||
                    (!isEnemy && (dFile == 0 && (dRank == 1 || dRank == 2)))) {
                pawn.setPawnStep(new PawnStepped(this.pawn));
                return true;
            }
            return false;
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
