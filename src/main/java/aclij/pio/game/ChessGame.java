package aclij.pio.game;

import aclij.pio.board.Board;
import aclij.pio.board.pieces.Piece;
import aclij.pio.board.pieces.coordinates.Coordinates;
import aclij.pio.game.dto.ChessMove;
import aclij.pio.game.moveValidator.MoveValidator;

public class ChessGame {

    private final Board board;
    private final MoveValidator validMove;
    private final CheckMate checkMate;

    public ChessGame(Board board) {
        this.board = board;
        this.validMove = new MoveValidator(board);
        this.checkMate = new CheckMate(board);
    }

    public void initBoard(){
        board.updateState(checkMate.isCheckMate());
    }
    public void move(ChessMove chessMove) {
        if (chessMove == null)
            return;
        if (validMove.isValidMove(chessMove)) {
            Piece source = chessMove.source;
            Coordinates target = chessMove.target;

            updateBoard(chessMove);

            if (isPawnPromotion(chessMove)){

            }
        }
    }
    public static void updateBoard(Board board, ChessMove chessMove, CheckMate checkMate){
        board.pieceMoveTo(chessMove.source, chessMove.target);
        board.currentColorPlayerNegate();
        board.state = checkMate.isCheckMate();
    }
    public void updateBoard(ChessMove chessMove){
        board.pieceMoveTo(chessMove.source, chessMove.target);
        board.currentColorPlayerNegate();
        board.state = checkMate.isCheckMate();
    }
    public static void initBoard(Board board){
        board.state = new CheckMate(board).isCheckMate();
    }
    public static void move(Board board, ChessMove chessMove){
        MoveValidator moveValidator = new MoveValidator(board);
        CheckMate cm = new CheckMate(board);
        if (chessMove == null)
            return;
        if (moveValidator.isValidMove(chessMove)) {
            Piece source = chessMove.source;
            Coordinates target = chessMove.target;

            updateBoard(board, chessMove, cm);

        }
    }
    public boolean isPawnPromotion(ChessMove chessMove){
        return (chessMove.source instanceof Piece && (chessMove.target.rank == 1 || chessMove.target.rank == 8));
    }

    public Board getBoard() {
        return board;
    }
}
