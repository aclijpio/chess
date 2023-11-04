package aclij.pio.game.moveValidator;

import aclij.pio.board.Board;
import aclij.pio.board.BoardFactory;
import aclij.pio.board.pieces.Knight;
import aclij.pio.board.pieces.Piece;
import aclij.pio.board.pieces.coordinates.Coordinates;
import aclij.pio.board.pieces.coordinates.File;
import aclij.pio.game.CheckMate;
import aclij.pio.game.State;
import aclij.pio.game.dto.ChessMove;

public class MoveValidator {

    private final Board board;

    public MoveValidator(Board board) {
        this.board = board;
    }


    public boolean isValidMove(ChessMove chessMove){
        return conditionForMove(chessMove);
    }
    protected boolean conditionForMove(ChessMove chessMove){
        Piece source = chessMove.source;
        Piece target = board.wrapCoordinates(chessMove.target);
        boolean currentMove = (source.color == board.currentPlayerColor) &&
                source.checkAvailableMove(target) &&
                (canMove(source, target)) &&
                isPieceJump(source, target.coordinates);
        if (currentMove && board.state == State.CHECK){
            source.coordinates = target.coordinates;
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

}
