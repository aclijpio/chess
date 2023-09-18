package aclij.pio.board.fen.exceptions;

public class DecodingException extends RuntimeException{
    private final String message;
    public DecodingException(String message) {
        super(message);
        this.message = message;
    }

    public DecodingException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
