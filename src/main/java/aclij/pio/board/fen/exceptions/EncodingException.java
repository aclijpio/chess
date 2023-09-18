package aclij.pio.board.fen.exceptions;

public class EncodingException extends RuntimeException{
    private final String message;
    public EncodingException(String message) {
        super(message);
        this.message = message;
    }

    public EncodingException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
