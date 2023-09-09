package aclij.pio.exceptions;

public class PieceNotFoundException extends RuntimeException{

    public PieceNotFoundException(String message) {
        super(message);
    }

    public PieceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
