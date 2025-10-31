public class King extends Piece{
    protected King(Coordinate c, Boolean isWhite, Table table) {
        super(c, isWhite, table);
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
