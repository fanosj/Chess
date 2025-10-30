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
        int direction = isWhite ? 1 : -1;
        if(toY == c.getY() && (toX-c.getX() == direction || (toX - c.getX() == 2*direction && !moved))) {
            while(toX != c.getX()) {
                if(table.isTherePieceAt(toX, c.getY())) return false;
                toX += direction;
            }
        }
        return true;
    }
    @Override
    public void drawPiece() {
        System.out.print(isWhite ? "♙" : "♟");
    }
}
