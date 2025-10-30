public class Rook extends Piece {

    public Rook(Coordinate c, Boolean isWhite, Table table) {
        super(c, isWhite, table);
    }

    @Override
    public boolean canMove(int toX, int toY) {
        if(toX == c.getX() || toY == c.getY()){
            int xdir =  Integer.signum(toX-c.getX());
            int ydir =  Integer.signum(toY-c.getY());
            int Xcopy = c.getX();
            int Ycopy = c.getY();
            while(Xcopy +xdir != toX || Ycopy + ydir != toY) {
                Xcopy += xdir;
                Ycopy += ydir;
                if(table.isTherePieceAt(Xcopy, Ycopy)) return false;
            }
            return !table.isTherePieceAt(toX, toY) || !isSameColor(table.getPieceAt(toX, toY));
        }
        return false;
    }

    @Override
    public void drawPiece() {
        System.out.print(isWhite ? "♖" : "♜");
    }
}
