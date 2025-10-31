public class Pawn extends Piece {
    public Pawn(Coordinate c, Boolean isWhite,Table table) {
        super(c, isWhite,table);
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
            return true;
        }
        else if(table.isTherePieceAt(toX,toY) && toY-c.getY() == direction && Math.abs(toX-c.getX()) == 1 && isWhite != table.getPieceAt(toX,toY).isWhite) {
            return true;
        }
        return false;
    }
    @Override
    public void drawPiece() {
        System.out.print(isWhite ? "♙" : "♟");
    }
}
