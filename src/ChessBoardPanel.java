import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChessBoardPanel extends JPanel {
    private Table table;
    private int squareSize;
    private int selectedX = -1;
    private int selectedY = -1;

    public ChessBoardPanel(Table table) {
        this.table = table;
        this.squareSize = getWidth() / table.getColumn();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX() / squareSize;
                int y = e.getY() / squareSize;

                if(selectedX == -1) {
                    if(table.isTherePieceAt(x, y)) {
                        selectedX = x;
                        selectedY = y;
                    }
                } else {
                    table.movePieceTo(table.getPieceAt(selectedX, selectedY), x, y);
                    selectedX = -1;
                    selectedY = -1;
                }
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        squareSize = getWidth() / table.getColumn();

        for(int i = 0; i < table.getRow(); i++) {
            for(int j = 0; j < table.getColumn(); j++) {
                int x = i * squareSize;
                int y = j * squareSize;

                g.setColor((i + j) % 2 == 0 ? Color.WHITE : Color.GRAY);
                g.fillRect(x, y, squareSize, squareSize);
                g.setColor(Color.BLACK);
                g.drawRect(x, y, squareSize, squareSize);

                Piece piece = table.getPieceAt(i, j);
                if(piece != null) {
                    g.setColor(piece.getIsWhite() ? Color.WHITE : Color.BLACK);
                    g.fillOval(x + 5, y + 5, squareSize - 10, squareSize - 10);
                    g.setColor(piece.getIsWhite() ? Color.BLACK : Color.WHITE);
                    g.drawString(piece.getClass().getSimpleName().substring(0, 1), x + squareSize/2 - 5, y + squareSize/2 + 5);
                }

                if(selectedX == i && selectedY == j) {
                    g.setColor(new Color(0, 255, 0, 100));
                    g.fillRect(x, y, squareSize, squareSize);
                }
            }
        }
    }
}
