import java.util.List;

public class Table {
    List<Piece> pieces;
    private int row;
    private int column;
    public boolean isWhiteTurn = true;
    public Table(List<Piece> pieces, int row, int column, boolean whiteTurn) {
        this.pieces = pieces;
        this.row = row;
        this.column = column;
        this.isWhiteTurn = whiteTurn;
    }
    int getRow() {
        return row;
    }
    int getColumn() {
        return column;
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
    public void movePieceTo(Piece piece, int toX, int toY) {
        List<Piece> tempPieces = List.copyOf(pieces);
        if(!piece.canMove(toX, toY)) {
            return;
        }
        if(isInCheck(piece.isWhite)) {
            pieces = tempPieces;
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

    public void drawTable() {
        javax.swing.SwingUtilities.invokeLater(() -> {
            javax.swing.JFrame frame = new javax.swing.JFrame("Chess Board");
            frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 800);
            frame.add(new ChessBoardPanel(this));
            frame.setVisible(true);
        });
    }

    public boolean isInCheck(boolean isWhite) {
        Piece king = null;
        for(Piece piece : pieces) {
            if(piece instanceof King && piece.getIsWhite() == isWhite) {
                king = piece;
                break;
            }
        }
        if(king == null) return false;
        for(Piece piece : pieces) {
            if(piece.getIsWhite() != isWhite) {
                if(piece.canMove(king.getC().getX(), king.getC().getY())) {
                    return true;
                }
            }
        }
        return false;
    }
}
