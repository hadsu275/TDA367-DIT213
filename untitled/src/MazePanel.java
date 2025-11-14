// MazePanel.java
import javax.swing.*;
import java.awt.*;

public class MazePanel extends JPanel {
    private MazeGenerator maze;
    private int cellSize = 80; // Anpassa storlek efter fönster

    public MazePanel(MazeGenerator maze) {
        this.maze = maze;
        setPreferredSize(new Dimension(maze.getWidth() * cellSize, maze.getHeight() * cellSize));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);

        for (int y = 0; y < maze.getHeight(); y++) {
            for (int x = 0; x < maze.getWidth(); x++) {
                int px = x * cellSize;
                int py = y * cellSize;

                // Rita väggar
                if (maze.getCell(y, x).walls[0]) g.drawLine(px, py, px + cellSize, py); // N
                if (maze.getCell(y, x).walls[1]) g.drawLine(px, py + cellSize, px + cellSize, py + cellSize); // S
                if (maze.getCell(y, x).walls[2]) g.drawLine(px + cellSize, py, px + cellSize, py + cellSize); // E
                if (maze.getCell(y, x).walls[3]) g.drawLine(px, py, px, py + cellSize); // W
            }
        }
    }
}
