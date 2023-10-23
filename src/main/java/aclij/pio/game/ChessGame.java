package aclij.pio.game;

import aclij.pio.board.Board;
import aclij.pio.board.BoardFactory;
import aclij.pio.board.pieces.coordinates.Color;
import aclij.pio.board.pieces.coordinates.Coordinates;
import aclij.pio.board.pieces.coordinates.File;
import aclij.pio.board.pieces.Knight;
import aclij.pio.board.pieces.Piece;
import aclij.pio.renderer.Render;
import aclij.pio.waitForAnswer.WaitForResponse;

public class ChessGame {

    private final Board board;
    private final Render render;
    private State state;
    CheckMate checkMate;

    public ChessGame(Board board, Render render) {
        this.board = board;
        this.checkMate = new CheckMate(board);
        this.render = render;
    }
    public void start(WaitForResponse response) {
        boolean isWhiteToMove = board.currentPlayerColor == Color.WHITE;
        state = State.ACTIVE;
        render.render(board);
        do {
            Piece selectedPiece = board.getPiece(response.getMove());
            Piece targetPiece = board.tryGetPiece(response.getMove());
            boolean condition = conditionForMove(selectedPiece, targetPiece);
            if (condition) {
                board.pieceMoveTo(selectedPiece, targetPiece.coordinates);
                isWhiteToMove = !isWhiteToMove;
                render.render(board);
            }
        } while (state == State.ACTIVE);
    }
    public boolean move(ChessMove chessMove){
        Piece selectedPiece = board.getPiece(chessMove.source);
        Piece targetPiece = board.tryGetPiece(chessMove.target);
        boolean currentMove = conditionForMove(selectedPiece, targetPiece);
        if (currentMove){
            board.pieceMoveTo(selectedPiece, targetPiece.coordinates);
            board.currentPlayerColor = board.currentPlayerColor.negate();
            state = checkMate.isCheckMate();
        }
        return currentMove;
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
    public void setState(State state){
        this.state = state;
    }
}
