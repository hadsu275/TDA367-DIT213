// MazeFrame.java
import javax.swing.*;
import java.awt.*;

public class MazeFrame extends JFrame {
    public MazeFrame(Dimension size, Point location, String difficulty) {
        setLocation(location);

        //setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        MazeGenerator maze;
        switch(difficulty) {
            case "basic": maze = new MazeGenerator(10, 6, 0.5); break;
            case "normal": maze = new MazeGenerator(10, 6, 0.8); break;
            case "difficult": maze = new MazeGenerator(10, 6, 1); break;
            default: maze = new MazeGenerator(800, 700, 0.5); break;
        }

        maze.generate();

        MazePanel mazePanel = new MazePanel(maze);
        add(mazePanel);

        pack(); // Anpassa fönster efter panel
        setVisible(true);
    }
}
