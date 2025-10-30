public class King extends Piece{
    public boolean moved;
    protected King(Coordinate c, Boolean isWhite, Table table, boolean moved) {
        super(c, isWhite, table);
        this.moved = moved;
    }

    @Override
    public boolean canMove(int toX, int toY) {
        if(Math.abs(toX - c.getX()) <= 1 && Math.abs(toY - c.getY()) <= 1) {
            Piece targetPiece = table.getPieceAt(toX, toY);
            return targetPiece == null || !isSameColor(targetPiece);
        }
        return false;
    }

    @Override
    public void drawPiece() {
        System.out.print(isWhite ? "♔" : "♚");
    }
}
