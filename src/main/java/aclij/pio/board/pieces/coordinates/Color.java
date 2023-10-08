package aclij.pio.board.pieces.coordinates;

public enum Color {
    BLACK,
    WHITE;

    public Color negate(){
        if (this == BLACK){
            return WHITE;
        } else {
            return BLACK;
        }
    }
}
