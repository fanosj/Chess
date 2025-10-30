import javax.naming.PartialResultException;
import java.util.ArrayList;
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
        List<Piece> tempPieces = new ArrayList<>(pieces);
        if(!piece.canMove(toX, toY)) {
            return;
        }
        int oldx = piece.getC().getX();
        int oldy = piece.getC().getY();
        boolean deleted = deleteAt(toX, toY);
        piece.setC(new Coordinate(toX, toY));
        if(isInCheck(piece.isWhite)) {
            pieces = tempPieces;
            piece.setC(new Coordinate(oldx, oldy));
            return;
        }
        if(isInCheck(!piece.isWhite)) {
            if(isCheckMate(!piece.isWhite)) {
                System.out.println((piece.isWhite ? "White" : "Black") + " wins by checkmate!");
            }
        }
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

    public boolean isCheckMate(boolean isWhite){
        for(Piece piece :pieces) {
            if(piece.isWhite == isWhite) {
                for(int x=0; x<column; x++) {
                    for(int y=0; y<row; y++) {
                        List<Piece> tempPieces = new ArrayList<>(pieces);
                        int oldx = piece.getC().getX();
                        int oldy = piece.getC().getY();
                        if(piece.canMove(x,y)) {
                            boolean deleted = deleteAt(x, y);
                            piece.setC(new Coordinate(x, y));
                            if(!isInCheck(isWhite)) {
                                pieces = tempPieces;
                                piece.setC(new Coordinate(oldx, oldy));
                                return false;
                            }
                            pieces = tempPieces;
                            piece.setC(new Coordinate(oldx, oldy));
                        }
                    }
                }
            }
        }
        return true;
    }
}
