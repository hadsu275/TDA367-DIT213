package mvc_package;

import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {

    public JButton basicButton = new JButton("Basic");
    public JButton normalButton = new JButton("Normal");
    public JButton difficultButton = new JButton("Difficult");

    public GameView (){
        super("Maze Program");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMaximumSize(new Dimension(800,700));
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.PINK);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(10, 0, 10, 0);

        JLabel title = new JLabel("Maze Team-28");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridy = 0;
        panel.add(title, gbc);

        JLabel description = new JLabel("Choose your difficulty");
        description.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridy = 1;
        panel.add(description, gbc);

        basicButton.setBackground(Color.LIGHT_GRAY);
        normalButton.setBackground(Color.YELLOW);
        difficultButton.setBackground(Color.RED);

        gbc.gridy = 2; panel.add(basicButton, gbc);
        gbc.gridy = 3; panel.add(normalButton, gbc);
        gbc.gridy = 4; panel.add(difficultButton, gbc);

        add(panel);
    }

    public void showView() {
        setLocationRelativeTo(null);
        setVisible(true);
    }


}
