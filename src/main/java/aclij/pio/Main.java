package aclij.pio;

import aclij.pio.board.BoardFactory;
import aclij.pio.game.ChessGame;
import aclij.pio.game.custom.ConsoleCustomGame;
import aclij.pio.renderer.BoardConsoleRenderer;
import aclij.pio.waitForAnswer.ConsoleResponse;

public class Main {
    public static void main(String[] args) {
        String startPosition = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
        String testPos = "1nb1kbnr/pppppppp/8/4P1RP/5PQB/3P3N/r3PPPP/q3KBNR b";
        ConsoleCustomGame consoleCustomGame = new ConsoleCustomGame();

        consoleCustomGame.start(new ConsoleResponse());
    }
}
