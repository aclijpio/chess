package aclij.pio;


import aclij.pio.board.Board;
import aclij.pio.renderer.BoardConsoleRenderer;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        board.setupDefaultPiecesPositions();

        Chess chess = new Chess(board, new BoardConsoleRenderer());

        chess.StartLocalConsole();
    }
}