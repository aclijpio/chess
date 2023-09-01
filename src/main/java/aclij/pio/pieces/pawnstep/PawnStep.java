package aclij.pio.pieces.pawnstep;

public interface PawnStep {
    boolean step(boolean isEnemy, int dFile, int dRank);
    boolean isFirst();
}
