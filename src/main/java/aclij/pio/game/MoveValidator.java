package aclij.pio.game;

import aclij.pio.board.Board;
import aclij.pio.board.BoardFactory;
import aclij.pio.board.pieces.Knight;
import aclij.pio.board.pieces.Piece;
import aclij.pio.board.pieces.coordinates.Coordinates;
import aclij.pio.board.pieces.coordinates.File;
import aclij.pio.game.dto.ChessMove;

public class MoveValidator {
    public final Board board;
    private State state;
    private CheckMate checkMate;

    public MoveValidator(Board board) {
        this.board = board;
        this.checkMate = new CheckMate(board);
    }

    public boolean isValidMove(ChessMove chessMove){
        Piece selectedPiece = board.getPiece(chessMove.source);
        Piece targetPiece = board.tryGetPiece(chessMove.target);
        checkMate = new CheckMate(board);
        if (conditionForMove(selectedPiece, targetPiece)){
            board.pieceMoveTo(selectedPiece, targetPiece.coordinates);
            board.currentColorPlayerNegate();
            setState(checkMate.isCheckMate());
            return true;
        }
        return false;
    }
    public Board getBoard(){
        return this.board;
    }

    public String getFen(){
        return this.board.toFen();
    }

    private boolean conditionForMove(Piece piece, Piece targetSquare){

        boolean currentMove = (piece.color == board.currentPlayerColor) &&
                piece.checkAvailableMove(targetSquare) &&
                (canMove(piece, targetSquare)) &&
                isPieceJump(piece, targetSquare.coordinates);
        if (currentMove && state == State.CHECK){
            piece.coordinates = targetSquare.coordinates;
            return (new CheckMate(
                    BoardFactory.fromPieceCollection(board.getPieces().values()))
                    .isCheckMate().isActive());
        }
        return currentMove;
    }
    private boolean canMove(Piece piece, Piece targetSquare){
        return piece.isEnemy(targetSquare) || board.isSquareEmpty(targetSquare.coordinates);
    }
    private boolean isPieceJump(Piece piece, Coordinates coordinates){
        if (piece instanceof Knight) return true;
        int file = piece.coordinates.file.ordinal();
        int rank = piece.coordinates.rank;
        int dFile = piece.coordinates.file == coordinates.file ? 0 :
                (coordinates.file.ordinal() - file > 0 ? 1 : -1);
        int dRank = piece.coordinates.rank.equals(coordinates.rank) ? 0 :
                (coordinates.rank - rank > 0 ? 1 : -1);
        Coordinates start = new Coordinates(File.values()[file+=dFile], rank+=dRank);
        while (!start.equals(coordinates)){
            if (!board.isSquareEmpty(start)) return false;
            start = new Coordinates(File.values()[file+=dFile], rank+=dRank);
        }
        return true;
    }
    public State getState() {
        return state;
    }
    private void setState(State state){
        this.state = state;
    }
}
