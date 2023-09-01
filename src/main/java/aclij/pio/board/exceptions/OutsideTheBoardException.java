package aclij.pio.board.exceptions;

public class OutsideTheBoardException extends Exception{
    private final String message;
    public OutsideTheBoardException(String message) {
        super(message);
        this.message = message;
    }

    public OutsideTheBoardException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
