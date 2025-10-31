public abstract class Piece {
    protected Coordinate c;
    protected boolean isWhite;
    protected boolean moved = false;
    Table table;
    protected Piece(Coordinate c, Boolean isWhite, Table table) {
        this.table = table;
        this.c = c;
        this.isWhite = isWhite;
    }
    public void setMoved(boolean moved) {this.moved = moved;}
    public Coordinate getC() {return c;}
    public Boolean getIsWhite() {return isWhite;}
    public void setC(Coordinate c) {this.c = c;}
    public abstract boolean canMove(int toX, int toY);
    public abstract void drawPiece();
    public boolean isSameColor(Piece other) {
        if(other == null) {
            return false;
        }
        return this.isWhite == other.isWhite;
    }
}
