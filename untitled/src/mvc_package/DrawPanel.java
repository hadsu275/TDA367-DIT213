package mvc_package;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class DrawPanel extends JPanel {

    BufferedImage playerImage, enemiesImage;

    Point playerImagePoint= new Point(300, 0);

    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);

        try {
            // You can remove the "pics" part if running outside of IntelliJ and
            // everything is in the same main folder.
            // volvoImage = ImageIO.read(new File("Volvo240.jpg"));

            // Rememember to rightclick src New -> Package -> name: pics -> MOVE *.jpg to pics.
            // if you are starting in IntelliJ.

            playerImage = ImageIO.read(DrawPanel.class.getResourceAsStream("../pics/Volvo240.jpg"));
            enemiesImage = ImageIO.read(DrawPanel.class.getResourceAsStream("../pics/VolvoBrand.jpg"));

        } catch (IOException ex)
        {
            ex.printStackTrace();
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(playerImage, playerImagePoint.x, playerImagePoint.y,null);

    }
}
