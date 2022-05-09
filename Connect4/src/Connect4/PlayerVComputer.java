package Connect4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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


        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == playButton){
                    Computer player = new Computer(playerName.getName());
                    new DrawGrid(player);
                    frame.dispose();
                }
            }
        });
    }

}
