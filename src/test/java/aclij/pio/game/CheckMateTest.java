package aclij.pio.game;

import aclij.pio.board.Board;
import aclij.pio.board.BoardFactory;
import aclij.pio.board.pieces.coordinates.Color;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class CheckMateTest {

    @Test
    public void testIsCheckMateForCheck() {
        Board board = createTestBoardWithCheck();
        board.getKing(Color.BLACK).get().getAllPossibleMoveCoordinatesUntilColor(board).stream()
                .flatMap(List::stream)
                .forEach(System.out::println);
        CheckMate checkMate = new CheckMate(board);
        assertEquals(State.CHECK, checkMate.isCheckMate());
    }

    @Test
    public void testIsCheckMateForMate() {
        Board board = createTestBoardWithMate();

        CheckMate checkMate = new CheckMate(board);
        assertEquals(State.MATE, checkMate.isCheckMate());
    }


    @Test
    public void testIsCheckMateForActive() {
        Board board = createTestBoardWithoutCheckMate();
        CheckMate checkMate = new CheckMate(board);
        assertEquals(State.ACTIVE, checkMate.isCheckMate());
    }

    private Board createTestBoardWithoutCheckMate() {
        return BoardFactory.fromFen("K7/8/8/8/8/4k3/R7/Q7 b - - 0 1");
    }
    private Board createTestBoardWithMate() {
        return BoardFactory.fromFen("K7/8/8/8/8/8/R7/Q2k4 b - - 0 1");
    }
    private Board createTestBoardWithCheck(){
        return BoardFactory.fromFen("K7/8/8/8/8/8/R3k3/Q7 b - - 0 1");
    }


}
