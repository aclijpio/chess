package aclij.pio.game;

import aclij.pio.board.Board;
import aclij.pio.board.exceptions.ChessMoveIsEmpty;
import aclij.pio.board.pieces.Piece;
import aclij.pio.game.dto.ChessMove;

public class ChessGame {

    private final Board board;

    public ChessGame(Board board) {
        this.board = board;
    }
    public boolean move(ChessMove chessMove) {
        if (chessMove == null)
            throw new ChessMoveIsEmpty();
        if (MoveValidator.isValidMove(board, chessMove)){
            board.pieceMoveTo(chessMove.getSource(board), chessMove.getTarget());
            board.currentColorPlayerNegate();
            return true;
        }
        return false;
    }
/*    public boolean isPawnPromotion(ChessMove chessMove){
        return (chessMove.source instanceof Piece && (chessMove.target.rank == 1 || chessMove.target.rank == 8));
    }*/

    public Board getBoard() {
        return board;
    }
}
