package aclij.pio.board;
import aclij.pio.board.fen.FenHandler;
import aclij.pio.board.pieces.*;
import aclij.pio.board.pieces.coordinates.Color;
import aclij.pio.board.pieces.coordinates.Coordinates;
import aclij.pio.board.pieces.coordinates.File;
import aclij.pio.board.exceptions.PieceNotFoundException;

import java.util.HashMap;

public class Board{
    HashMap<Coordinates, Piece> pieces = new HashMap<Coordinates, Piece>();

    public boolean isSquareEmpty(Coordinates coordinates){
        return !isSquareOccupied(coordinates);
    }
    public boolean isSquareOccupied(Coordinates coordinates){
        return this.pieces.containsKey(coordinates);
    }

    public void pieceMoveTo(Piece selectedPiece, Coordinates coordinates){
        Piece piece = this.getPiece(selectedPiece.coordinates);
        pieces.remove(piece.coordinates);
        this.setPiece(coordinates, piece.moveTo(coordinates));
    }
    public Piece getPiece(Coordinates coordinates) throws PieceNotFoundException {
        if (isSquareEmpty(coordinates))
            throw new PieceNotFoundException(
                    String.format("Piece with coordinates: ( file - %s, rank - %d ) not found.",
                            coordinates.file.toString(),
                            coordinates.rank
                    )
             );
        return pieces.get(coordinates);
    }
    public Piece tryGetPiece(Coordinates coordinates){
        try {
            return getPiece(coordinates);
        } catch (PieceNotFoundException e){
            return new Pawn(null, coordinates);
        }
    }
    public void setPiece (Coordinates coordinates, Piece piece) {
        piece.coordinates = coordinates;
        pieces.put(coordinates, piece);
    }
    public String toFen(){
        return FenHandler.encode(this);
    }
    public HashMap<Coordinates, Piece> getPieces(){
        return pieces;
    }
    public void setupDefaultPiecesPositions(){
        // set pawns
        for(File file : File.values()){
            Coordinates coordinatesWhite = new Coordinates(file, 2);
            Coordinates coordinatesBlack = new Coordinates(file, 7);
            this.setPiece(coordinatesWhite, new Pawn(Color.WHITE, coordinatesWhite));
            this.setPiece(coordinatesBlack, new Pawn(Color.BLACK, coordinatesBlack));
        }
        //set rooks
        {
            Coordinates coordinatesWhiteFirst =  new Coordinates(File.A, 1);
            Coordinates coordinatesWhiteSecond =  new Coordinates(File.H, 1);
            Coordinates coordinatesBlackFirst = new Coordinates(File.A, 8);
            Coordinates coordinatesBlackSecond = new Coordinates(File.H, 8);
            this.setPiece(coordinatesWhiteFirst, new Rook(Color.WHITE, coordinatesWhiteFirst));
            this.setPiece(coordinatesWhiteSecond, new Rook(Color.WHITE, coordinatesWhiteSecond));
            this.setPiece(coordinatesBlackFirst, new Rook(Color.BLACK, coordinatesBlackFirst));
            this.setPiece(coordinatesBlackSecond, new Rook(Color.BLACK, coordinatesBlackSecond));
        }
        //set king
        {
            Coordinates coordinatesWhite = new Coordinates(File.D, 1);
            Coordinates coordinatesBlack = new Coordinates(File.E, 8);
            this.setPiece(coordinatesWhite, new King(Color.WHITE, coordinatesWhite));
            this.setPiece(coordinatesBlack, new King(Color.BLACK, coordinatesBlack));
        }
        //set queen
        {
            Coordinates coordinatesWhite = new Coordinates(File.E, 1);
            Coordinates coordinatesBlack = new Coordinates(File.D, 8);
            Piece whiteQueen = new Queen(Color.WHITE, coordinatesWhite);
            Piece blackQueen = new Queen(Color.BLACK, coordinatesWhite);
            this.setPiece(coordinatesWhite, whiteQueen);
            this.setPiece(coordinatesBlack, blackQueen);
        }
        //set bishop
        {
            Coordinates coordinatesWhiteFirst =  new Coordinates(File.C, 1);
            Coordinates coordinatesWhiteSecond =  new Coordinates(File.F, 1);
            Coordinates coordinatesBlackFirst = new Coordinates(File.C, 8);
            Coordinates coordinatesBlackSecond = new Coordinates(File.F, 8);
            this.setPiece(coordinatesWhiteFirst, new Bishop(Color.WHITE, coordinatesWhiteFirst));
            this.setPiece(coordinatesWhiteSecond, new Bishop(Color.WHITE, coordinatesWhiteSecond));
            this.setPiece(coordinatesBlackFirst, new Bishop(Color.BLACK, coordinatesBlackFirst));
            this.setPiece(coordinatesBlackSecond, new Bishop(Color.BLACK, coordinatesBlackSecond));
        }
        //set knight
        {
            Coordinates coordinatesWhiteFirst =  new Coordinates(File.B, 1);
            Coordinates coordinatesWhiteSecond =  new Coordinates(File.G, 1);
            Coordinates coordinatesBlackFirst = new Coordinates(File.B, 8);
            Coordinates coordinatesBlackSecond = new Coordinates(File.G, 8);
            this.setPiece(coordinatesWhiteFirst, new Knight(Color.WHITE, coordinatesWhiteFirst));
            this.setPiece(coordinatesWhiteSecond, new Knight(Color.WHITE, coordinatesWhiteSecond));
            this.setPiece(coordinatesBlackFirst, new Knight(Color.BLACK, coordinatesBlackFirst));
            this.setPiece(coordinatesBlackSecond, new Knight(Color.BLACK, coordinatesBlackSecond));
        }
    }
}
