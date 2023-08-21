package aclij.pio;


import aclij.pio.board.Board;
import aclij.pio.renderer.BoardConsoleRenderer;
import aclij.pio.waitForAnswer.ConsoleResponse;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        board.setupDefaultPiecesPositions();

        Chess chess = new Chess(board, new BoardConsoleRenderer());

        chess.StartLocalConsole(new ConsoleResponse());
    }
}