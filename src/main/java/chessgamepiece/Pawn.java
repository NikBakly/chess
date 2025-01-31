package chessgamepiece;


import static java.lang.Math.abs;
import chessgame.Cell;

public class Pawn extends ChessPiece {

    public Pawn(String color, Cell pos) {
        super(color, pos);
    }

    @Override
    protected boolean canMoveToPosition(int dX, int dY) {
        return (WHITE_COLOR.equals(getColor()) && dY > 0 || BLACK_COLOR.equals(getColor()) && dY < 0)
                && isFirstPos()
                ? (abs(dY) == 2 && dX == 0) || (abs(dY) == 1 && abs(dX) <= 1)
                : abs(dY) == 1 && abs(dX) <= 1;
    }
}
