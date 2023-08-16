package aclij.pio;

import aclij.pio.board.Board;
import aclij.pio.renderer.Render;
import aclij.pio.waitForAnswer.ConsoleResponse;
import aclij.pio.waitForAnswer.WaitForResponse;

class Chess {
        Board board;
        Render render;

    public Chess(Board board, Render render) {
        this.board = board;
        this.render = render;
    }

    public void StartLocalConsole(){
        WaitForResponse waitForResponse = new ConsoleResponse();
        boolean isWhiteToMove;
        while (true) {
            render.render(board);

            board.pieceMoveTo(waitForResponse.getNextStep(), waitForResponse.getNextStep());
            isWhiteToMove=!;
        }
    }

}