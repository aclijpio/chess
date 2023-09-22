package aclij.pio.game;

import aclij.pio.board.Board;
import aclij.pio.board.pieces.Piece;
import aclij.pio.board.pieces.Queen;
import aclij.pio.board.pieces.coordinates.Color;
import aclij.pio.board.pieces.coordinates.Coordinates;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CheckMate {

    public final Board board;
    public CheckMate(Board board) {
        this.board = board;
    }
    public boolean check(){
        Set<Piece> piecesAttackingQueen = getPiecesClassUnderAttack(Queen.class);

        // TODO: 22.09.2023  
        return false;
        
    }
    private Set<Piece> getPiecesClassUnderAttack(Class<? extends Piece> pieceClass){
        Set<Piece> piecesAttackingPieceClass = new HashSet<>();
        for (Piece piece:
                board.getPieces().values()) {
            if(piece.isAttacksPieceClass(board, pieceClass))
                piecesAttackingPieceClass.add(piece);
        }
        return piecesAttackingPieceClass;
    }
    private boolean pieceIsUnderAttack(Piece targetPiece){
        for (Piece piece:
                board.getPieces().values()){
            if (piece.isEnemy(targetPiece) && piece.isAttacksPiece(board, targetPiece))
                return true;
        }
        return false;
    }
    private boolean isCheck(Set<Piece> piecesAttackingQueen){
        return !piecesAttackingQueen.isEmpty();
    }
    private boolean isMate(Set<Piece> piecesAttackingQueen){
        int attackingPiecesSize = piecesAttackingQueen.size();
        Piece firstAttackingPiece = piecesAttackingQueen.iterator().next();
        Color enemyColor = firstAttackingPiece.color;
        boolean attackingPieceUnderAttack = attackingPiecesSize == 1 &&
                pieceIsUnderAttack(firstAttackingPiece);
        boolean onlyMove = attackingPiecesSize > 2;

        // TODO: 22.09.2023
        return false;
    }

    private boolean canBlockOrCapture(){
        return false;
    }

    private boolean isCheckMate(Piece piece){
        if (piece instanceof Queen)
                return piece.getAllPossibleMoveCoordinatesUntilColor(board)
                        .stream()
                        .flatMap(List::stream)
                        .noneMatch(coordinates -> attackMovement(piece, coordinates));
        return true;
    }
    private boolean attackMovement(Piece piece, Coordinates coordinates) {
        Coordinates oldCoordinates = piece.coordinates;
        piece.coordinates = coordinates;
        boolean isUnderAttack = pieceIsUnderAttack(piece);
        piece.coordinates = oldCoordinates;
        return !isUnderAttack;
    }
    private boolean attackingPieceUnderAttack(Piece piece){
        return pieceIsUnderAttack(piece);
    }
}
