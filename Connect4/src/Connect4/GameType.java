package Connect4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameType {
    private JPanel gameTypePanel;
    private JButton compButton;
    private JButton pvpButton;

    public GameType() {

        JFrame frame = new JFrame("Game Type");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(600, 400));

        frame.add(gameTypePanel);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


        pvpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == pvpButton){
                    new PlayerVPlayer();
                    frame.dispose();
                }
                if(e.getSource() == compButton){
                    new PlayerVComputer();
                    frame.dispose();
                }
                /*else{
                    new PlayerVProgram();
                    frame.dispose();
                }*/
            }
        });
    }
}

