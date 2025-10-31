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

    public void deleteAt(int x, int y) {
        for (Piece piece : pieces) {
            if (piece.getC().getX() == x && piece.getC().getY() == y) {
                pieces.remove(piece);
                return;
            }
        }
    }
    public void movePieceTo(Piece piece, int toX, int toY) {
        if (!moveCheck(piece, toX, toY,true)) return;
        isWhiteTurn = !isWhiteTurn;
        piece.setMoved(true);
        /*if(isInCheck(!piece.isWhite)) {
            if(isCheckMate(!piece.isWhite)) {
                System.out.println((piece.isWhite ? "White" : "Black") + " wins by checkmate!");
            }
        }*/
    }

    private boolean moveCheck(Piece piece, int toX, int toY,boolean realMove) {
        System.out.println("itt a baj");
        List<Piece> tempPieces = new ArrayList<>(pieces);
        if(!piece.canMove(toX, toY)) {
            return false;
        }
        int oldx = piece.getC().getX();
        int oldy = piece.getC().getY();
        deleteAt(toX, toY);
        piece.setC(new Coordinate(toX, toY));
        if(isInCheck(piece.isWhite)) {
            piece.setC(new Coordinate(oldx, oldy));
            pieces = tempPieces;
            return false;
        }
        if(!realMove) {
            piece.setC(new Coordinate(oldx, oldy));
            pieces = tempPieces;
        }
        return true;
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
        System.out.println("check teszt");
        Piece king = null;
        for(Piece piece : pieces) {
            if(piece instanceof King && piece.getIsWhite() == isWhite) {
                king = piece;
                break;
            }
        }
        if(king == null) return false;
        for(Piece piece : pieces) {
            if(piece.getIsWhite() != isWhite && piece.canMove(king.getC().getX(), king.getC().getY()))
                return true;
        }
        return false;
    }
    public List<Coordinate> getAllMoves(Piece piece){
        System.out.println("moves teszt");
        List<Coordinate> moves = new ArrayList<>();
        for(int x=0; x<column; x++) {
            for(int y=0; y<row; y++) {
                if(moveCheck(piece,x,y,false)) {
                    moves.add(new Coordinate(x,y));
                }
            }
        }
        return moves;
    }
    public boolean isCheckMate(boolean isWhite){
        System.out.println("checkmate teszt");
        for(Piece piece :pieces) {
            if(piece.isWhite == isWhite) {
                List<Coordinate> moves = getAllMoves(piece);
                for(Coordinate move : moves) {
                    List<Piece> tempPieces = new ArrayList<>(pieces);
                    int oldx = piece.getC().getX();
                    int oldy = piece.getC().getY();
                    deleteAt(move.getX(), move.getY());
                    piece.setC(new Coordinate(move.getX(), move.getY()));
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
        return true;
    }
}
