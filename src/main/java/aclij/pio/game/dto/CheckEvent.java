package aclij.pio.game.dto;

import aclij.pio.board.Board;
import aclij.pio.board.BoardFactory;
import aclij.pio.board.pieces.Pawn;
import aclij.pio.game.CheckMate;
import aclij.pio.game.State;
import aclij.pio.renderer.Render;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class CheckEvent {


    private final Map<String, State> fenCodes;

    private final Render render;

    public CheckEvent(Map<String, State> fenCodes, Render render) {
        this.fenCodes = fenCodes;
        this.render = render;
    }


    public void check(){
        for (Map.Entry<String, State> pair : fenCodes.entrySet()){
            if (render(pair.getKey()) == pair.getValue()){
                System.out.println(String.format(" board : %s is OK", pair.getKey()));
            } else {
                System.out.println(String.format(" board : %s is BAD", pair.getKey()));
            }
        }
    }
    public State render(String fenCode){
        Board board = BoardFactory.fromFen(fenCode);
        render.render(board);
        CheckMate checkMate = new CheckMate(board);
        board.getPieces().values().stream()
                .filter(piece -> piece instanceof Pawn)
                .map(piece -> piece.getAllPossibleMoveCoordinatesUntilColor(board))
                .flatMap(Set::stream)
                .flatMap(List::stream)
                .forEach(System.out::println);
        return checkMate.isCheckMate().getState();
    }
}
