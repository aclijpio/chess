package aclij.pio.board;

import aclij.pio.board.fen.FenHandler;
public final class BoardFactory {
    public static Board fromFen(String fen){
        return FenHandler.decode(fen);
    }
}
