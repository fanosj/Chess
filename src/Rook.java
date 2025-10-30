public class Rook extends Piece {

    public Rook(Coordinate c, Boolean isWhite, Table table) {
        super(c, isWhite, table);
    }

    @Override
    public boolean canMove(int toX, int toY) {
        if(toX == c.getX() && toY == c.getY()){
            int xdir =  toX-c.getX() > 0 ? 1 : toX-c.getX() < 0 ? -1 : 0;
            int ydir = toY-c.getY() > 0 ? 1 : toY-c.getY() < 0 ? -1 : 0;
            while(toX + xdir != c.getX() || toY + ydir != c.getY()) {
                if((toX != c.getX() || toY != c.getY()) && table.isTherePieceAt(toX, toY)) return false;
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
        System.out.print(isWhite ? "♖" : "♜");
    }
}
