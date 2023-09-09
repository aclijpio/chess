package aclij.pio;

import aclij.pio.board.Board;
import aclij.pio.renderer.Render;
import aclij.pio.waitForAnswer.WaitForResponse;

class Chess {
        Board board;
        Render render;
    public Chess(Board board, Render render) {
        this.board = board;
        this.render = render;
    }

    public void start(WaitForResponse response){
        boolean isWhiteToMove = true;
        while (true) {
            render.render(board);
            if (board.pieceMoveTo(
                    response.getNextStep(),
                    response.getNextStep(),
                    isWhiteToMove
            ))
                isWhiteToMove = !isWhiteToMove;
        }
    }

}
