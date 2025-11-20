

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;

public abstract class MazeGame {
    protected JFrame mainFrame;
    protected JButton basic, normal, difficult;
    protected boolean exitFound = false;

    public MazeGame() {
        initializeComponents();
        showMaze();
    }



    private void initializeComponents() {
        mainFrame = new JFrame("Maze Program");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setMinimumSize(new Dimension(800, 700));


        // panel for buttons and text
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.PINK);

        Dimension buttonSize = new Dimension(200, 80);
        Font buttonFont = new Font("Arial", Font.BOLD, 18);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 10, 0);

        // Text
        JLabel f = new JLabel("Maze Team-28");
        f.setHorizontalAlignment(JLabel.CENTER);
        f.setFont(new Font("Arial", Font.BOLD, 24));
        f.setPreferredSize(new Dimension(300, 100));
        gbc.gridy = 0;
        mainPanel.add(f, gbc);

        JLabel f_description = new JLabel("Choose your difficulty");
        f_description.setAlignmentX(JLabel.CENTER);
        f_description.setFont(new Font("Arial", Font.BOLD, 18));
        f_description.setPreferredSize(new Dimension(200,50));
        gbc.gridy = 1;
        mainPanel.add(f_description,gbc);




        // buttons
        basic = new JButton("Basic");
        normal = new JButton("Normal");
        difficult = new JButton("Difficult");



        basic.setBackground(Color.LIGHT_GRAY);
        normal.setBackground(Color.YELLOW);
        difficult.setBackground(Color.RED);
        for (JButton btn : new JButton[]{basic, normal, difficult}) {
            btn.setPreferredSize(buttonSize);
            btn.setFont(buttonFont);

            btn.setFocusPainted(false);
        }

        gbc.gridy = 2;
        mainPanel.add(basic, gbc);

        gbc.gridy = 3;
        mainPanel.add(normal, gbc);

        gbc.gridy = 4;
        mainPanel.add(difficult, gbc);

        mainFrame.add(mainPanel, BorderLayout.CENTER);

        basic.addActionListener(e -> new MazeFrame(mainFrame.getSize(),mainFrame.getLocation(), "Basic"));
        normal.addActionListener(e -> new MazeFrame(mainFrame.getSize(),mainFrame.getLocation(), "Normal"));
        difficult.addActionListener(e -> new MazeFrame(mainFrame.getSize(),mainFrame.getLocation(), "Difficult"));
    }


    private void showMaze() {
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    public abstract void startMaze();

    public abstract void propertyChange(PropertyChangeEvent evt);
}