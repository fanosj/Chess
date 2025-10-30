public class Bishop extends Piece {
    public Bishop(Coordinate c, Boolean isWhite, Table table) {
        super(c, isWhite, table);
    }
    @Override
    public boolean canMove(int toX, int toY) {
        if(Math.abs(toX-c.getX()) == Math.abs(toY-c.getY())) {
            int xdir = toX-c.getX() > 0 ? 1 : -1;
            int ydir = toY-c.getY() > 0 ? 1 : -1;
            while(toX + xdir != c.getX()) {
                if(toX != c.getX() && table.isTherePieceAt(toX, toY)) return false;
                toX -= xdir;
                toY -= ydir;
            }
            if(!table.isTherePieceAt(toX, toY) || !isSameColor(table.getPieceAt(toX, toY))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void drawPiece() {
        System.out.print(isWhite ? "♗" : "♝");
    }
}
