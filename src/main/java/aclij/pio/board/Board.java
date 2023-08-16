package aclij.pio.board;

import aclij.pio.Color;
import aclij.pio.Coordinates;
import aclij.pio.File;
import aclij.pio.pieces.*;

import java.util.HashMap;
import java.util.Set;

public class Board {
    HashMap<Coordinates, Piece> pieces = new HashMap<Coordinates, Piece>();
    public void setPiece (Coordinates coordinates, Piece piece){
        piece.coordinates = coordinates;
        pieces.put(coordinates, piece);
    }

    public boolean isSquareEmpty(Coordinates coordinates){
        return !this.pieces.containsKey(coordinates);
    }
    private boolean isEnemy(Piece piece, Coordinates coordinates){
        return !isSquareEmpty(coordinates) &&
                this.getPiece(coordinates).color != piece.color;
    }
    private boolean isPieceJump(Piece piece, Coordinates coordinates){
        if (piece instanceof Knight) return true;
        System.out.println(1);
        int file = piece.coordinates.file.ordinal();
        int rank = piece.coordinates.rank;
        int dFile = piece.coordinates.file == coordinates.file ? 0 :
                (coordinates.file.ordinal() - file > 0 ? 1 : -1);
        int dRank = piece.coordinates.rank.equals(coordinates.rank) ? 0 :
                (coordinates.rank - rank > 0 ? 1 : -1);
        Coordinates start = new Coordinates(File.values()[file+=dFile], rank+=dRank);
        while (!start.equals(coordinates)){
            System.out.println(" result : " +
                    !isSquareEmpty(start) + " / " +
                    start.equals(coordinates));
            if (!isSquareEmpty(start)) return false;
            start = new Coordinates(File.values()[file+=dFile], rank+=dRank);
        }

        return true;
    }
    public void pieceMoveTo(Coordinates selectedCoordinates, Coordinates coordinates){
        System.out.println(1);
        Piece piece = this.getPiece(selectedCoordinates);
        System.out.println(selectedCoordinates.toString());
        System.out.println(selectedCoordinates.file + String.valueOf(selectedCoordinates.rank));
        if (piece != null && conditionForMove(piece, coordinates)) {
            System.out.println(2);
            pieces.remove(piece.coordinates);
            this.setPiece(coordinates, piece.moveTo(coordinates));
        }
    }
    private boolean conditionForMove(Piece piece, Coordinates coordinates){
        boolean isEnemy = isEnemy(piece, coordinates);
        System.out.println(isPieceJump(piece, coordinates));
        return piece.checkAvailableMove(coordinates, isEnemy) &&
                (isEnemy || isSquareEmpty(coordinates)) &&
                isPieceJump(piece, coordinates);
    }
    public Set<Coordinates> getAllPossibleMoveCoordinates(Coordinates coordinates){
        return getPiece(coordinates).getPossibleMoveCoordinates();
    }
    public Piece getPiece(Coordinates coordinates){
        return pieces.get(coordinates);
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
            this.setPiece(coordinatesWhite, new Queen(Color.WHITE, coordinatesWhite));
            this.setPiece(coordinatesBlack, new Queen(Color.BLACK, coordinatesBlack));
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
