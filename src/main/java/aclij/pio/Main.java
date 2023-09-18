package aclij.pio;

import aclij.pio.board.BoardFactory;
import aclij.pio.game.ChessGame;
import aclij.pio.renderer.BoardConsoleRenderer;
import aclij.pio.waitForAnswer.ConsoleResponse;

public class Main {
    public static void main(String[] args) {
        ChessGame chessGame = new ChessGame(BoardFactory.fromFen(
                "r3kb1r/pp1pp1pp/3n1q2/1p2p3/nP2P3/3b2Q1/PPPP2PP/RNB1KBNR w KQkq - 0 1"),
                new BoardConsoleRenderer());
        System.out.println(chessGame.getFen());
        System.out.println(chessGame.getState());
        chessGame.initBoard();
        chessGame.start(new ConsoleResponse());
        System.out.println(chessGame.getState());
        System.out.println(chessGame.getFen());
    }
}
