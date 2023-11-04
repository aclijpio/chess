package aclij.pio;

import aclij.pio.board.BoardFactory;
import aclij.pio.game.ChessGame;
import aclij.pio.game.State;
import aclij.pio.game.custom.ConsoleCustomGame;
import aclij.pio.game.dto.CheckEvent;
import aclij.pio.renderer.BoardConsoleRenderer;
import aclij.pio.waitForAnswer.ConsoleResponse;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String startPosition = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
        String testPos = "1nb1kbnr/pppppppp/8/4P1RP/5PQB/3P3N/r3PPPP/q3KBNR b";
//        ConsoleCustomGame consoleCustomGame = new ConsoleCustomGame();
//
//        consoleCustomGame.start(new ConsoleResponse());

            Map<String, State> fenCodes = Map.of(
                "R7/R4P1k/8/8/8/Q7/8/3K4 w - - 0 1", State.ACTIVE,
                "R7/R6k/5P2/8/8/Q7/8/3K4 b - - 0 1", State.CHECK,
                "R6k/R7/8/8/8/8/8/3K4 b - - 0 1", State.MATE,
                "R6k/R5p1/8/8/8/8/8/3K4 w - - 0 1", State.CHECK,
                    "8/8/8/1Q1K4/8/8/R5p1/R6k b - - 0 1", State.CHECK
        );

        CheckEvent checkEvent = new CheckEvent(fenCodes, new BoardConsoleRenderer());
        checkEvent.check();

    }
}
