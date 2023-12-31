package aclij.pio.board;
import aclij.pio.board.fen.FenHandler;
import aclij.pio.board.pieces.*;
import aclij.pio.board.pieces.coordinates.Color;
import aclij.pio.board.pieces.coordinates.Coordinates;
import aclij.pio.board.exceptions.PieceNotFoundException;
import aclij.pio.game.State;

import java.util.HashMap;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Board{
    HashMap<Coordinates, Piece> pieces = new HashMap<Coordinates, Piece>();
    public Color currentPlayerColor;
    State state;

    public boolean isSquareEmpty(Coordinates coordinates){
        return !isSquareOccupied(coordinates);
    }
    public boolean isSquareOccupied(Coordinates coordinates){
        return this.pieces.containsKey(coordinates);
    }
    public void currentColorPlayerNegate(){
        this.currentPlayerColor = currentPlayerColor.negate();
    }

    public void pieceMoveTo(Piece selectedPiece, Coordinates coordinates){
        pieces.remove(selectedPiece.coordinates);
        this.setPiece(selectedPiece.moveTo(coordinates));
    }
    public Optional<Piece> getKing(Color color){
        for (Piece piece:
                this.getPieces().values()) {
            if (piece.color == color && piece instanceof King)
                return Optional.of(piece);
        }
        return Optional.empty();
    }
    public Set<Piece> getPiece(Class<? extends Piece> pieceClass){
        return this.pieces.values().stream()
                .filter(piece -> piece.getClass().equals(pieceClass))
                .collect(Collectors.toSet());
    }
    public Optional<Piece> getPiece(Class<? extends Piece> pieceClass, Color pieceColor){
        return this.pieces.values().stream()
                .filter(piece -> piece.color == pieceColor && piece.getClass().equals(pieceClass))
                .findAny();

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
    public Piece wrapCoordinates(Coordinates coordinates){
        try {
            return getPiece(coordinates);
        } catch (PieceNotFoundException e){
            return new Pawn(null, coordinates);
        }
    }
    public void setPiece (Piece piece) {
        pieces.put(piece.coordinates, piece);
    }

    public String toFen(){
        return FenHandler.encode(this);
    }
    public HashMap<Coordinates, Piece> getPieces(){
        return pieces;
    }

    @Override
    public Board clone() {
        try {
            Board clone = (Board) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
