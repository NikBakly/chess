package chessgamepiece;

import chessgame.Cell;


public class Bishop extends ChessPiece {
    public Bishop(String color, Cell position) {
        super(color, position);
    }

    @Override
    protected boolean canMoveToPosition(int dX, int dY) {
        return Math.abs(dX) == Math.abs(dY);
    }
}
