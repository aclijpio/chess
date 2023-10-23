package aclij.pio.board.pieces.pieceStep.pawn;

import aclij.pio.board.pieces.coordinates.Color;
import aclij.pio.board.pieces.Piece;
import aclij.pio.board.pieces.pieceStep.PieceStep;

public class PawnStepped extends PieceStep {
    public static final int [][] MOVEMENT_RULES_WHITE = {
            {0, 1}
    };
    public static final int [][] MOVEMENT_RULES_BLACK= {
            {0, -1}
    };
    private final Piece piece;

    public PawnStepped(Piece pawn) {
        super(pawn);
        this.piece = pawn;
    }

    @Override
    public boolean isPieceStepped() {
        return true;
    }

    @Override
    public boolean step(Piece targetPiece) {
        int dFile = Math.abs(
                (this.piece.coordinates.file.ordinal() + 1) - (targetPiece.coordinates.file.ordinal() + 1)
        );
        int dRank = this.piece.coordinates.rank - targetPiece.coordinates.rank;
        boolean isEnemy = this.piece.isEnemy(targetPiece);
        if(this.piece.color == Color.BLACK)
            return (isEnemy && (dFile == 1 && dRank == 1)) ||
                    (!isEnemy && (dFile == 0 && dRank == 1));
        return  (isEnemy && (dFile == 1 && dRank == -1)) ||
                (!isEnemy && (dFile == 0 && dRank == -1));
    }

    @Override
    public boolean stepWithoutChange(Piece targetSquare) {
        return step(targetSquare);
    }

    @Override
    public int [][] getMovementRules() {
        return this.piece.color == Color.WHITE ? MOVEMENT_RULES_WHITE : MOVEMENT_RULES_BLACK;
    }
}