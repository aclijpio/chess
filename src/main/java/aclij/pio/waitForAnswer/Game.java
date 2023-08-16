package aclij.pio.waitForAnswer;

import aclij.pio.board.Board;
import aclij.pio.renderer.Render;

public class Game {

    Render render;
    Board board;
    WaitForResponse waitForResponse;

    public Game(Render render, Board board, WaitForResponse waitForResponse) {
        this.render = render;
        this.board = board;
        this.waitForResponse = waitForResponse;
    }
    public void next(){
        while (true){
            render.render(board);

            board.pieceMoveTo(waitForResponse.getNextStep(), waitForResponse.getNextStep());


        }
    }
}
