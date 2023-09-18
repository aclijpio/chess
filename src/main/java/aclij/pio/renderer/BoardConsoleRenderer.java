package aclij.pio.renderer;

import aclij.pio.board.Board;
import aclij.pio.board.pieces.coordinates.Color;
import aclij.pio.board.pieces.coordinates.Coordinates;
import aclij.pio.board.pieces.coordinates.File;
import aclij.pio.board.pieces.Piece;

public class BoardConsoleRenderer implements Render {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_WHITE_PIECE_COLOR = "\u001B[97m";
    private static final String ANSI_BLACK_PIECE_COLOR = "\u001B[30m";
    private static final String ANSI_WHITE_SQUARE_BACKGROUND = "\u001B[47m";
    private static final String ANSI_BLACK_SQUARE_BACKGROUND = "\u001B[0;100m";

    public void render(Board board){
        for (int rank = 8; rank > 0; rank--){
            StringBuilder line = new StringBuilder();

            for (File file : File.values()){
                Coordinates coordinates = new Coordinates(file, rank);
                if (board.isSquareEmpty(coordinates))
                    line.append(getSpriteForEmptySquare(coordinates));
                else
                    line.append(getPieceSprite(board.getPiece(coordinates)));
            }
            line.append(ANSI_RESET);
            System.out.println(line);
        }
    }

    private String colorizeSprite(String sprite, Color pieceColor, boolean  isSquareDark){
        String result = sprite;
         if(pieceColor == Color.WHITE)
            result = ANSI_WHITE_PIECE_COLOR + result;
        else
            result = ANSI_BLACK_PIECE_COLOR + result;
        if (isSquareDark)
            result = ANSI_BLACK_SQUARE_BACKGROUND + result;
        else
            result = ANSI_WHITE_SQUARE_BACKGROUND + result;
        return result;
    }
    private String getSpriteForEmptySquare(Coordinates coordinates){
        return colorizeSprite("   ", Color.WHITE, coordinates.isSquareDark());
    }
    private String selectUnicodeSpriteForPiece(Piece piece){
        switch (piece.getClass().getSimpleName()){
            case "Pawn":
                return "P";
            case "Bishop":
                return "B";
            case "Queen":
                return "Q";
            case "Knight":
                return "L";
            case "King":
                return "K";
            case "Rook":
                return "R";
        }
        return "";
    }
    private String getPieceSprite(Piece piece) {
        return colorizeSprite(" "+ selectUnicodeSpriteForPiece(piece) + " ", piece.color, piece.coordinates.isSquareDark());
    }
}
