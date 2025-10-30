public class Bishop extends Piece {
    public Bishop(Coordinate c, Boolean isWhite, Table table) {
        super(c, isWhite, table);
    }
    @Override
    public boolean canMove(int toX, int toY) {
        if(Math.abs(toX-c.getX()) == Math.abs(toY-c.getY())) {
            int xdir = toX-c.getX() > 0 ? 1 : -1;
            int ydir = toY-c.getY() > 0 ? 1 : -1;
            int Xcopy = c.getX();
            int Ycopy = c.getY();
            while(Xcopy + xdir != toX) {
                Xcopy += xdir;
                Ycopy += ydir;
                if(table.isTherePieceAt(Xcopy, Ycopy)) return false;
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
