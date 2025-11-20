
import javax.swing.*;
import java.awt.*;

public class MazeFrame extends JFrame {


    public MazeFrame(Dimension size, Point location, String difficulty) {
        MazeGenerator maze;
        switch(difficulty) {
            case "basic": maze = new MazeGenerator(10, 6, 5); break;
            case "normal": maze = new MazeGenerator(10, 6, 100); break;
            case "difficult": maze = new MazeGenerator(10, 6, 1000); break;
            default: maze = new MazeGenerator(800, 700, 0.5); break;
        }
        maze.generate();
        MazePanel mazePanel = new MazePanel(maze);
        add(mazePanel);

        setSize(size);
        setLocation(location);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }
}