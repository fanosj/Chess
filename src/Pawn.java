public class Pawn extends Piece {
    private boolean moved;
    public Pawn(Coordinate c, Boolean isWhite,Table table, boolean moved) {
        super(c, isWhite,table);
        this.moved = moved;
    }
    public boolean getMoved() {return moved;}
    public void setMoved(boolean moved) {this.moved = moved;}
    @Override
    public boolean canMove(int toX, int toY) {
        int direction = isWhite ? -1 : 1;
        if(toX == c.getX() && (toY-c.getY() == direction || (toY - c.getY() == 2*direction && !moved))) {
            int toYcopy = toY;
            while(toYcopy != c.getY()) {
                if(table.isTherePieceAt(c.getX(), toYcopy)) return false;
                toYcopy -= direction;
            }
            moved = true;
            return true;
        }
        else if(table.isTherePieceAt(toX,toY) && toY-c.getY() == direction && Math.abs(toX-c.getX()) == 1 && isWhite != table.getPieceAt(toX,toY).isWhite) {
            moved = true;
            return true;
        }
        return false;
    }
    @Override
    public void drawPiece() {
        System.out.print(isWhite ? "♙" : "♟");
    }
}
