package aclij.pio.game.dto;

import aclij.pio.board.Board;
import aclij.pio.board.pieces.Piece;
import aclij.pio.board.pieces.coordinates.Coordinates;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChessMove {
    public final Piece source;
    public final Coordinates target;
    private final static Pattern pattern = Pattern.compile("^([A-H][1-8])$");

    public ChessMove(String source, String target, Board board) {
        this.source = toPiece(toCoordinates(source), board);
        this.target = toCoordinates(target);
    }

    public ChessMove(Coordinates source, Coordinates target, Board board) {
        this.source = toPiece(source, board);
        this.target = target;
    }

    private Coordinates toCoordinates(String step){
        Matcher matcher = pattern.matcher(step.toUpperCase());
        if(matcher.matches()){
            return Coordinates.valueOf(matcher.group(0));
        }
        return null;
    }
    private Piece toPiece(Coordinates coordinates, Board board){
        return board.getPiece(coordinates);
    }
}
