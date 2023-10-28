package aclij.pio.game;

import aclij.pio.board.Board;
import aclij.pio.game.dto.ChessMove;

public class ChessGame {

    private Board board;
    private State state;
    MoveValidator validMove;

    public ChessGame(Board board) {
        this.board = board;
        this.validMove = new MoveValidator(board);
    }


    public void move(ChessMove chessMove) {
        if (chessMove == null)
            return;

           /* throw new CoordinatesFormatException("chess move is null");*/
        if (validMove.isValidMove(chessMove)) {
            this.board = validMove.board;
            this.state = validMove.getState();
        }
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Board getBoard() {
        return board;
    }
}
