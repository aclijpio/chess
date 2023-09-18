package aclij.pio.board.pieces.coordinates.exceptions;

public class CoordinatesFormatException extends Exception{
    public String getValue(){
        return this.value;
    }
    String value;
    public CoordinatesFormatException(String message, String value) {
        super(message);
        this.value = value;
    }
    public CoordinatesFormatException(String message) {
        super(message);
    }
}
