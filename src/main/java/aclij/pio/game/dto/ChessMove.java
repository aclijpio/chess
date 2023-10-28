package aclij.pio.game.dto;

import aclij.pio.board.pieces.coordinates.Coordinates;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChessMove {
    public final Coordinates source;
    public final Coordinates target;
    private final static Pattern pattern = Pattern.compile("^([A-H][1-8])$");

    public ChessMove(String source, String target) {
        this.source = convert(source);
        this.target = convert(target);
    }

    public ChessMove(Coordinates source, Coordinates target) {
        this.source = source;
        this.target = target;
    }

    private Coordinates convert(String step){
        Matcher matcher = pattern.matcher(step.toUpperCase());
        if(matcher.matches()){
            return Coordinates.valueOf(matcher.group(0));
        }
        return null;
    }
}
