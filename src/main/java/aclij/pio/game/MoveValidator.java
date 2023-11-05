package aclij.pio.game;
import aclij.pio.board.Board;
import aclij.pio.board.BoardFactory;
import aclij.pio.board.pieces.Knight;
import aclij.pio.board.pieces.Piece;
import aclij.pio.board.pieces.coordinates.Coordinates;
import aclij.pio.board.pieces.coordinates.File;
import aclij.pio.game.dto.CheckMateResult;
import aclij.pio.game.dto.ChessMove;
import java.util.Optional;

public class MoveValidator {
    public static boolean isValidMove(Board board, ChessMove chessMove){
        return conditionForMove(board, chessMove) && conditionAfterMove(board, chessMove);
    }
    protected static boolean conditionForMove(Board board, ChessMove chessMove){
        Piece source = chessMove.getSource(board);
        Piece target = board.wrapCoordinates(chessMove.getTarget());
        return (source.color == board.currentPlayerColor) &&
                source.checkAvailableMove(target) &&
                (canMove(board, source, target)) &&
                isPieceJump(board, source, target.coordinates);
    }
    private static boolean canMove(Board board, Piece piece, Piece targetSquare){
        return piece.isEnemy(targetSquare) || board.isSquareEmpty(targetSquare.coordinates);
    }
    private static boolean isPieceJump(Board board, Piece piece, Coordinates coordinates){
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
    private static boolean conditionAfterMove(Board board, ChessMove chessMove){
        Board tempBoard = BoardFactory.fromFen(board.toFen());
        Piece source = chessMove.getSource(tempBoard);
        Coordinates target = chessMove.getTarget();
        tempBoard.pieceMoveTo(source, target);
        CheckMateResult checkMateResult = new CheckMate(tempBoard).isCheckMate();
        CheckMateResult originalCheckMateResult = new CheckMate(board).isCheckMate();
        if (originalCheckMateResult.getState() == State.CHECK && checkMateResult.getState().isActive()) {
            return true;
        }

        if (checkMateResult.getState() == State.CHECK) {
            Optional<Piece> king = checkMateResult.getPiece();
            if (king.isPresent()) {
                return king.get().isEnemy(source.color);
            }
        }

        return true;
    }
}