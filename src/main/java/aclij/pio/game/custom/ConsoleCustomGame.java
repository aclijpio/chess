package aclij.pio.game.custom;

import aclij.pio.board.Board;
import aclij.pio.board.BoardFactory;
import aclij.pio.board.pieces.Piece;
import aclij.pio.board.pieces.coordinates.Color;
import aclij.pio.game.ChessGame;
import aclij.pio.game.State;
import aclij.pio.game.dto.ChessMove;
import aclij.pio.renderer.BoardConsoleRenderer;
import aclij.pio.renderer.Render;
import aclij.pio.waitForAnswer.WaitForResponse;

public class ConsoleCustomGame {
    Render render = new BoardConsoleRenderer();
    ChessGame chessGame = new ChessGame(
            BoardFactory.fromFen("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1")
    );

    public void start(WaitForResponse response) {
        chessGame.setState(State.ACTIVE);
        do {
            Board board = chessGame.getBoard();
            render.render(board);
            ChessMove chessMove = new ChessMove(response.getMove(), response.getMove());
                chessGame.move(chessMove);
        } while (chessGame.getState() == State.ACTIVE);
    }

}
