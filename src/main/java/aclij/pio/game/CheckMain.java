package aclij.pio.game;

import aclij.pio.board.Board;
import aclij.pio.board.BoardFactory;
import aclij.pio.board.pieces.Pawn;
import aclij.pio.board.pieces.Piece;
import aclij.pio.board.pieces.coordinates.Color;
import aclij.pio.renderer.BoardConsoleRenderer;
import aclij.pio.renderer.Render;

import java.util.List;
import java.util.stream.Collectors;

public class CheckMain {
    public static void main(String[] args) {
        Board board = BoardFactory.fromFen("k7/8/8/8/8/R7/Q2pK3/R3P3 w - - 0 1");
        Render render = new BoardConsoleRenderer();
        render.render(board);
        System.out.println(board.toFen());
        CheckMate checkMate = new CheckMate(board);
        System.out.println(checkMate.isCheckMate());
        System.out.println(board.getPiece(Pawn.class, Color.WHITE).get().getAllPossibleMoveCoordinatesUntilColor(board)
                .stream().flatMap(List::stream).collect(Collectors.toSet()));
    }
}
    