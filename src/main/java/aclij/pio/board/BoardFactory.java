package aclij.pio.board;

import aclij.pio.board.fen.FenHandler;
import aclij.pio.board.pieces.Piece;
import aclij.pio.game.PieceCondition;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public final class BoardFactory {
    public static Board fromFen(String fen){
        return FenHandler.decode(fen);
    }
    public static Board fromPieceCollection(Collection<Piece> pieces){
        Board board = new Board();
        for (Piece piece:
                pieces)
            board.setPiece(piece);
        return board;
    }
    public static Board fromBoardWithout(Board board, PieceCondition pieceCondition){
        return fromPieceCollection(
                board.getPieces().values().stream()
                        .filter(pieceCondition::cond)
                        .collect(Collectors.toSet())
        );
    }
}
