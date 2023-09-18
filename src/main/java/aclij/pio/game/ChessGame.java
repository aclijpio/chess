package aclij.pio.game;

import aclij.pio.board.Board;
import aclij.pio.board.BoardFactory;
import aclij.pio.board.pieces.coordinates.Color;
import aclij.pio.board.pieces.coordinates.Coordinates;
import aclij.pio.board.pieces.coordinates.File;
import aclij.pio.board.pieces.Knight;
import aclij.pio.board.pieces.Piece;
import aclij.pio.board.pieces.Queen;
import aclij.pio.renderer.Render;
import aclij.pio.waitForAnswer.WaitForResponse;

import java.util.List;

public class ChessGame {

    private final Board board;
    private final Render render;
    private State state;

    public ChessGame(Board board, Render render) {
        this.board = board;
        this.render = render;
    }
    public void initBoard(){
        render.render(board);
    }
    public void start(WaitForResponse response){
        boolean isWhiteToMove = true;
        state = State.ACTIVE;
        do{
            Piece selectedPiece = board.getPiece(response.getNextStep());
            Piece targetPiece = board.tryGetPiece(response.getNextStep());
            if (conditionForMove(selectedPiece, targetPiece, isWhiteToMove)) {
                board.pieceMoveTo(selectedPiece, targetPiece.coordinates);
                isWhiteToMove = !isWhiteToMove;
                render.render(board);
            }
        } while (state == State.ACTIVE);
    }
    public String getFen(){
        return this.board.toFen();
    }
    private boolean pieceIsUnderAttack(Class<? extends Piece> pieceClass){
        for (Piece piece:
                board.getPieces().values()) {
            if(piece.isAttacksPiece(board, pieceClass))
                return true;
        }
        return false;
    }

    private boolean isCheckmate(Piece piece){
        boolean isUnderAttack = pieceIsUnderAttack(Queen.class);
        if (isUnderAttack && (queenCantMove(piece)))
            state = State.COMPLETED;
        return isUnderAttack;
    }
    private boolean moveAndCheckForCheck(Piece piece, Coordinates coordinates) {
        if (piece.isQueen()) {
            Coordinates oldCoordinates = piece.coordinates;
            piece.coordinates = coordinates;
            boolean isUnderAttack = pieceIsUnderAttack(Queen.class);
            piece.coordinates = oldCoordinates;
            return !isUnderAttack;
        }
        return false;
    }
    private boolean queenCantMove(Piece piece){
        if (piece.isQueen())
            return piece.getAllPossibleMoveCoordinatesUntilColor(board)
                .stream()
                .flatMap(List::stream)
                .noneMatch(coordinates -> moveAndCheckForCheck(piece, coordinates));
        return true;
    }
    private boolean conditionForMove(Piece piece, Piece targetSquare, boolean isWhiteMove){
        return (isWhiteMove == (piece.color == Color.WHITE) &&
                (!isCheckmate(piece) || moveAndCheckForCheck(piece, targetSquare.coordinates)) &&
                piece.checkAvailableMove(targetSquare) &&
                (piece.isEnemy(targetSquare) || board.isSquareEmpty(targetSquare.coordinates)) &&
                isPieceJump(piece, targetSquare.coordinates));
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
}
