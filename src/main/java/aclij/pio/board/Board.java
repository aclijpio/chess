package aclij.pio.board;

import aclij.pio.Color;
import aclij.pio.Coordinates;
import aclij.pio.File;
import aclij.pio.exceptions.PieceNotFoundException;
import aclij.pio.pieces.*;

import java.util.HashMap;

public class Board {
    HashMap<Coordinates, Piece> pieces = new HashMap<Coordinates, Piece>();
    private Piece whiteQueen;
    private Piece blackQueen;

    public boolean isSquareEmpty(Coordinates coordinates){
        return !isSquareOccupied(coordinates);
    }
    public boolean isSquareOccupied(Coordinates coordinates){
        return this.pieces.containsKey(coordinates);
    }
    private boolean isPieceJump(Piece piece, Coordinates coordinates){
        if (piece instanceof Knight) return true;
        int file = piece.coordinates.file.ordinal();
        int rank = piece.coordinates.rank;
        int dFile = piece.coordinates.file == coordinates.file ? 0 :
                (coordinates.file.ordinal() - file > 0 ? 1 : -1);
        int dRank = piece.coordinates.rank.equals(coordinates.rank) ? 0 :
                (coordinates.rank - rank > 0 ? 1 : -1);
        Coordinates start = new Coordinates(File.values()[file+=dFile], rank+=dRank);
        while (!start.equals(coordinates)){
            if (!isSquareEmpty(start)) return false;
            start = new Coordinates(File.values()[file+=dFile], rank+=dRank);
        }
        return true;
    }
    public boolean isCheckmate(Piece piece){
        System.out.println(piece.isQueen());
        System.out.println(piece.isUnderAttack(this));
        if(piece.isQueen() && !(piece.isUnderAttack(this)))
            return false;
        return ( whiteQueen.isUnderAttack(this) ||
                 blackQueen.isUnderAttack(this) );
    }
    public boolean pieceMoveTo(Coordinates selectedCoordinates, Coordinates coordinates, boolean isWhiteMove){
        Piece piece = this.getPiece(selectedCoordinates);
        if (conditionForMove(
                piece,
                this.tryGetPiece(coordinates)
                )) {
            pieces.remove(piece.coordinates);
            this.setPiece(coordinates, piece.moveTo(coordinates));
            if (piece.isQueen()){
                if (piece.color.equals(Color.WHITE))
                    whiteQueen = piece;
                else
                    blackQueen = piece;
            }
            return true;
        }
        return false;
    }

    private boolean conditionForMove(Piece piece, Piece targetSquare){
        return !isCheckmate(piece) &&
                piece.checkAvailableMove(targetSquare) &&
                (piece.isEnemy(targetSquare) || isSquareEmpty(targetSquare.coordinates)) &&
                isPieceJump(piece, targetSquare.coordinates);
    }
    public Piece getPiece(Coordinates coordinates) throws PieceNotFoundException {
        if (isSquareEmpty(coordinates))
            throw new PieceNotFoundException(
                    String.format("Piece with coordinates: ( file - %s, rank - %d ) not found.",
                            coordinates.file,
                            coordinates.rank
                    )
            );
        return pieces.get(coordinates);
    }
    public Piece tryGetPiece(Coordinates coordinates){
        try {
            return getPiece(coordinates);
        } catch (PieceNotFoundException e){
            return new King(null, coordinates);
        }
    }
    private void setPiece (Coordinates coordinates, Piece piece){
        piece.coordinates = coordinates;
        pieces.put(coordinates, piece);
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
            this.whiteQueen = whiteQueen;
            this.blackQueen = blackQueen;
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
