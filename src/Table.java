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
    //Check if there is a piece at the given coordinate
    public boolean isTherePieceAt(int x, int y) {
        for (Piece piece : pieces) {
            if (piece.getC().getX() == x && piece.getC().getY() == y) {
                return true;
            }
        }
        return false;
    }
    //Get the piece at the given coordinate, if there is no piece, return null
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
    //Delete the piece at the given coordinate, if it does not exist, do nothing, could be prettier
    //but for now it doesn't need to check it it actually deleted anything
    public void deleteAt(int x, int y) {
        for (Piece piece : pieces) {
            if (piece.getC().getX() == x && piece.getC().getY() == y) {
                pieces.remove(piece);
                return;
            }
        }
    }
    //Move the piece to the given coordinate if the move is valid, also checks for check and checkmate
    //and switches the turn
    public void movePieceTo(Piece piece, int toX, int toY) {
        if (!moveCheck(piece, toX, toY,true)) return;
        isWhiteTurn = !isWhiteTurn;
        piece.setMoved(true);
        if(isInCheck(!piece.isWhite)) {
            if(isCheckMate(!piece.isWhite)) {
                System.out.println((piece.isWhite ? "White" : "Black") + " wins by checkmate!");
            }
        }
    }
    //checks if the move is valid, if realMove is false, it only checks if the move is valid without
    //actually moving the piece, otherwise it moves the piece and returns if the move is valid
    private boolean moveCheck(Piece piece, int toX, int toY,boolean realMove) {
        //needed, so that a piece can't move to the same spot
        if(piece.getC().getX() == toX && piece.getC().getY() == toY) return false;
        //store current pieces to revert back if move is invalid, not the prettiest :(
        List<Piece> tempPieces = new ArrayList<>(pieces);
        if(!piece.canMove(toX, toY)) return false;
        int oldx = piece.getC().getX();
        int oldy = piece.getC().getY();
        deleteAt(toX, toY);
        piece.setC(new Coordinate(toX, toY));
        boolean moveValid = isInCheck(piece.isWhite);
        //both outcomes need move revert, so it was easier to do it this way
        if(!moveValid || !realMove) {
            piece.setC(new Coordinate(oldx, oldy));
            pieces = tempPieces;
            return moveValid;
        }
        return true;
    }
    //it only exists for testing purposes
    public void listPieces() {
        for (Piece piece : pieces) {
            System.out.println("Piece at (" + piece.getC().getX() + ", " + piece.getC().getY() + ")");
        }
    }
    //self-explanatory, draws the table using Swing
    public void drawTable() {
        javax.swing.SwingUtilities.invokeLater(() -> {
            javax.swing.JFrame frame = new javax.swing.JFrame("Chess Board");
            frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 800);
            frame.add(new ChessBoardPanel(this));
            frame.setVisible(true);
        });
    }
    //checks if the king of the given color is in check
    public boolean isInCheck(boolean isWhite) {
        Piece king = null;
        //find the king of the given color
        for(Piece piece : pieces) {
            if(piece instanceof King && piece.getIsWhite() == isWhite) {
                king = piece;
                break;
            }
        }
        //if the king doesnt't exist, return false (shouldn't happen in a real game, but who knows)
        if(king == null) return false;
        //check if any of the opponent's pieces can move to the king's position
        for(Piece piece : pieces) {
            if(piece.getIsWhite() != isWhite && piece.canMove(king.getC().getX(), king.getC().getY()))
                return true;
        }
        return false;
    }
    //get all possible moves for the given piece, used for checkmate detection, and showing all
    //possible moves on the table, when a piece is selected
    public List<Coordinate> getAllMoves(Piece piece){
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
    //checks if the given color is in checkmate
    public boolean isCheckMate(boolean isWhite){
        //goes through all pieces
        for(Piece piece :pieces) {
            //if the piece is of the given color
            if(piece.isWhite == isWhite) {
                //get all possible moves for the piece
                List<Coordinate> moves = getAllMoves(piece);
                for(Coordinate move : moves) {
                    List<Piece> tempPieces = new ArrayList<>(pieces);
                    int oldx = piece.getC().getX();
                    int oldy = piece.getC().getY();
                    deleteAt(move.getX(), move.getY());
                    piece.setC(new Coordinate(move.getX(), move.getY()));
                    //if any move gets the king out of check, it's not checkmate
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
