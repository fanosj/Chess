import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Piece> pieces = new ArrayList<>();
        Table t = new Table(pieces, 8, 8);

        pieces.add(new Rook(new Coordinate(0, 0), false, t));
        pieces.add(new Knight(new Coordinate(0, 1), false, t));
        pieces.add(new Bishop(new Coordinate(0, 2), false, t));
        pieces.add(new Queen(new Coordinate(0, 3), false, t));
        //pieces.add(new King(new Coordinate(0, 4), false, t));
        pieces.add(new Bishop(new Coordinate(0, 5), false, t));
        pieces.add(new Knight(new Coordinate(0, 6), false, t));
        pieces.add(new Rook(new Coordinate(0, 7), false, t));
        for (int y = 0; y < 8; y++) {
            pieces.add(new Pawn(new Coordinate(1, y), false, t, false));
        }
        for (int y = 0; y < 8; y++) {
            pieces.add(new Pawn(new Coordinate(6, y), true, t, false));
        }
        pieces.add(new Rook(new Coordinate(7, 0), true, t));
        pieces.add(new Knight(new Coordinate(7, 1), true, t));
        pieces.add(new Bishop(new Coordinate(7, 2), true, t));
        pieces.add(new Queen(new Coordinate(7, 3), true, t));
        //pieces.add(new King(new Coordinate(7, 4), true, t));
        pieces.add(new Bishop(new Coordinate(7, 5), true, t));
        pieces.add(new Knight(new Coordinate(7, 6), true, t));
        pieces.add(new Rook(new Coordinate(7, 7), true, t));
        t.drawTable();
        t.listPieces();
    }
}