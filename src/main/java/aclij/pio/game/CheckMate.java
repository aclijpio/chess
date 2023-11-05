package aclij.pio.game;

import aclij.pio.board.Board;
import aclij.pio.board.BoardFactory;
import aclij.pio.board.exceptions.PieceNotFoundException;
import aclij.pio.board.pieces.Piece;
import aclij.pio.board.pieces.King;
import aclij.pio.board.pieces.coordinates.Color;
import aclij.pio.board.pieces.coordinates.Coordinates;
import aclij.pio.game.dto.CheckMateResult;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class CheckMate {
    private final Board board;
    State state;
    public CheckMate(Board board) {
        this.board = board;
    }

    public CheckMateResult isCheckMate() {
        Set<Piece> attackingPieces = getPiecesClassUnderAttack(King.class);
        boolean isCheck = isCheck(attackingPieces);
        boolean isMate = false;
        Optional<Piece> king = Optional.empty();
        if (!attackingPieces.isEmpty()){
            Color color = attackingPieces.iterator().next().color.negate();
            Piece curKing = board.getKing(color).orElseThrow(
                    () -> new PieceNotFoundException("King not found for color " + color)
            );
            king = Optional.of(curKing);
            isMate = isMate(curKing);
        }
        if (isCheck) {
            if (isMate)
                return new CheckMateResult(State.MATE, king);
            return new CheckMateResult(State.CHECK, king);
        }
        if (isMate)
            return new CheckMateResult(State.DRAW);
        return new CheckMateResult(State.ACTIVE);
    }

    private boolean isCheck(Set<Piece> attackingPieces) {
        return !attackingPieces.isEmpty();
    }

    private boolean isMate(Piece king) {
        for (Piece piece : board.getPieces().values())
            if (piece.color == king.color)
                for (List<Coordinates> coordinatesList :
                        piece.getAllPossibleMoveCoordinatesUntilColor(board))
                    for (Coordinates coordinates : coordinatesList)
                        if (!checkMovement(piece, coordinates, king))
                            return false;
        return true;
    }

    private boolean kingCantMove(Piece king) {
        return !pieceCanMove(king);
    }
    private boolean pieceCanMove(Piece piece) {
        return piece.getAllPossibleMoveCoordinatesUntilColor(board).stream()
                .flatMap(List::stream)
                .anyMatch(coordinates -> attackMovement(piece, coordinates));
    }

    //Handlers
    private Set<Piece> getPiecesClassUnderAttack(Class<? extends Piece> pieceClass) {
        Set<Piece> piecesAttackingPieceClass = new HashSet<>();
        for (Piece piece :
                board.getPieces().values()) {
            if (piece.isAttacksPieceClass(board, pieceClass)) {
                piecesAttackingPieceClass.add(piece);
            }
        }
        return piecesAttackingPieceClass;
    }

    private boolean pieceClassIsUnderAttack(Class<? extends Piece> pieceClass) {
        for (Piece piece :
                board.getPieces().values()) {
            if (piece.isAttacksPieceClass(board, pieceClass)) {
                return true;
            }
        }
        return false;
    }

    private boolean pieceUnderAttack(Board board, Piece targetPiece) {
        return board.getPieces().values().stream()
                .anyMatch(piece -> piece.isAttacksCoordinates(board, targetPiece.coordinates));

    }

    private boolean attackMovement(Piece piece, Coordinates coordinates) {
        Coordinates oldCoordinates = piece.coordinates;
        piece.coordinates = coordinates;
        boolean isUnderAttack = pieceUnderAttack(board, piece);
        piece.coordinates = oldCoordinates;
        return !isUnderAttack;
    }

    private boolean checkMovement(Piece piece, Coordinates coordinates, Piece king) {
        Coordinates oldCoordinates = piece.coordinates;
        piece.coordinates = coordinates;
        boolean isUnderAttack = pieceUnderAttack(
                BoardFactory.fromPieceCollection(board.getPieces().values()),
                king);
        piece.coordinates = oldCoordinates;
        return isUnderAttack;
    }
}
