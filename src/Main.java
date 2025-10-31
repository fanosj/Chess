import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Piece> pieces = new ArrayList<>();
        Table t = new Table(pieces, 8, 8, true);

        pieces.add(new Rook(new Coordinate(0, 0), false, t));
        pieces.add(new Knight(new Coordinate(1, 0), false, t));
        pieces.add(new Bishop(new Coordinate(2, 0), false, t));
        pieces.add(new Queen(new Coordinate(3, 0), false, t));
        pieces.add(new King(new Coordinate(4, 0), false, t));
        pieces.add(new Bishop(new Coordinate(5, 0), false, t));
        pieces.add(new Knight(new Coordinate(6, 0), false, t));
        pieces.add(new Rook(new Coordinate(7, 0), false, t));
        for (int y = 0; y < 8; y++) {
            pieces.add(new Pawn(new Coordinate(y, 1), false, t));
        }
        for (int y = 0; y < 8; y++) {
            pieces.add(new Pawn(new Coordinate(y, 6), true, t));
        }
        pieces.add(new Rook(new Coordinate(0, 7), true, t));
        pieces.add(new Knight(new Coordinate(1, 7), true, t));
        pieces.add(new Bishop(new Coordinate(2, 7), true, t));
        pieces.add(new Queen(new Coordinate(3, 7), true, t));
        pieces.add(new King(new Coordinate(4, 7), true, t));
        pieces.add(new Bishop(new Coordinate(5, 7), true, t));
        pieces.add(new Knight(new Coordinate(6, 7), true, t));
        pieces.add(new Rook(new Coordinate(7, 7), true, t));

        t.drawTable();
        t.listPieces();
    }
}