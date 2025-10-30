public class Knight extends  Piece {

    public Knight(Coordinate c, Boolean isWhite,Table table) {
        super(c, isWhite,table);
    }
    @Override
    public boolean canMove(int toX, int toY) {
        return ((Math.abs(toX - c.getX()) == 2 && Math.abs(toY - c.getY()) == 1) || (Math.abs(toX - c.getX()) == 1 && Math.abs(toY - c.getY()) == 2)) && !isSameColor(table.getPieceAt(toX,toY));
    }

    @Override
    public void drawPiece() {
        System.out.print(isWhite ? "♘" : "♞");
    }
}
