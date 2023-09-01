package aclij.pio.board.exceptions;

public class QueenPieceNotFound extends RuntimeException{
    public QueenPieceNotFound(String message) {
        super(message);
    }

    public QueenPieceNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
