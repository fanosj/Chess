import java.util.List;

public class Table {
    List<Piece> pieces;
    public int row;
    public int column;
    public Table(List<Piece> pieces, int row, int column) {
        this.pieces = pieces;
        this.row = row;
        this.column = column;
    }
    public boolean isTherePieceAt(int x, int y) {
        for (Piece piece : pieces) {
            if (piece.getC().getX() == x && piece.getC().getY() == y) {
                return true;
            }
        }
        return false;
    }
    public Piece getPieceAt(int x, int y) {
        if(x < 0 || x >= column || y < 0 || y >= row) {
            return null;
        }
        for (Piece piece : pieces) {
            if (piece.getC().getX() == x && piece.getC().getY() == y) {
                return piece;
            }
        }
        return null;
    }
    public boolean deleteAt(int x, int y) {
        for (Piece piece : pieces) {
            if (piece.getC().getX() == x && piece.getC().getY() == y) {
                pieces.remove(piece);
                return true;
            }
        }
        return false;
    }
    public void MovePiece(Piece piece, int toX, int toY) {
        if(!piece.canMove(toX, toY)) {
            return;
        }
        boolean deleted = deleteAt(toX, toY);
        piece.setC(new Coordinate(toX, toY));
    }
    public void listPieces() {
        for (Piece piece : pieces) {
            System.out.println("Piece at (" + piece.getC().getX() + ", " + piece.getC().getY() + ")");
        }
    }
    public void drawTable(){
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < column; j++) {
                Piece piece = getPieceAt(i, j);
                if(piece != null) {
                    piece.drawPiece();
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }

}
