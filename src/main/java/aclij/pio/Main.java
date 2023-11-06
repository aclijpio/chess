package aclij.pio;

import aclij.pio.board.Board;
import aclij.pio.board.BoardFactory;
import aclij.pio.board.pieces.coordinates.Coordinates;
import aclij.pio.board.pieces.coordinates.File;
import aclij.pio.game.CheckMate;
import aclij.pio.game.ChessGame;
import aclij.pio.game.MoveValidator;
import aclij.pio.game.State;
import aclij.pio.game.custom.ConsoleCustomGame;
import aclij.pio.game.dto.CheckEvent;
import aclij.pio.game.dto.ChessMove;
import aclij.pio.renderer.BoardConsoleRenderer;
import aclij.pio.waitForAnswer.ConsoleResponse;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String startPosition = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
        String testPos = "1nb1kbnr/pppppppp/8/4P1RP/5PQB/3P3N/r3PPPP/q3KBNR b";
/*        ConsoleCustomGame consoleCustomGame = new ConsoleCustomGame();

        consoleCustomGame.start(new ConsoleResponse());*/

            Map<String, State> fenCodes = Map.of(
                "R7/R4P1k/8/8/8/Q7/8/3K4 w - - 0 1", State.ACTIVE,
                "R7/R6k/5P2/8/8/Q7/8/3K4 b - - 0 1", State.CHECK,
                "R6k/R7/8/8/8/8/8/3K4 b - - 0 1", State.MATE,
                "R6k/R5p1/8/8/8/8/8/3K4 w - - 0 1", State.CHECK,
                    "8/8/8/1Q1K4/8/8/R5p1/R6k b - - 0 1", State.CHECK,
                    "1k6/8/8/8/8/r3K3/r2B4/r2P4 w - - 0 1", State.CHECK
        );

        CheckEvent checkEvent = new CheckEvent(fenCodes, new BoardConsoleRenderer());
        checkEvent.check();
/*
        BoardConsoleRenderer render =new BoardConsoleRenderer();
        Board board = BoardFactory.fromFen(startPosition);
        render.render(board);
        ChessMove chessMove = new ChessMove(new Coordinates(File.A, 2), new Coordinates(File.A, 4), board);
*/

/*        System.out.println(new CheckMate(board).isCheckMate().getState());
        if (MoveValidator.isValidMove(board, chessMove))
            board.pieceMoveTo(chessMove.source, chessMove.target);
        render.render(board);


        ChessGame chessGame = new ChessGame(board);
        ChessMove chessMoves = new ChessMove(new Coordinates(File.A, 7), new Coordinates(File.A, 5), board);
        if (chessGame.move(chessMoves)){
            render.render(board);
        }*/
    }
}
