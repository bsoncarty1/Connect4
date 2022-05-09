package Connect4;

import javax.swing.*;
import java.awt.*;

public class PlayerVComputer extends JFrame{
    private JPanel pvcPanel;
    private JLabel playerNameLabel;
    private JTextField playerName;
    private JButton playButton;


    public PlayerVComputer(){
        JFrame frame = new JFrame("Player vs. Computer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(600,400));

        frame.add(pvcPanel);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
