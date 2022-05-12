package Connect4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerVPlayer extends JFrame {
    private JButton playButton;
    private JTextField player1Name;
    private JLabel player2;
    private JLabel player1;
    private JTextField player2Name;
    private JPanel pvpPanel;
    private JPanel JPanel;

    public PlayerVPlayer(){
        JFrame frame = new JFrame("Player vs. Player");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(600,400));

        frame.add(pvpPanel);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == playButton){
                    Players players = new Players(getPlayer1Name(),getPlayer2Name());
                    new DrawGridPVP(players);
                    frame.dispose();
                }
            }
        });

    }

    public String getPlayer1Name(){
        return this.player1Name.getText();
    }

    public String getPlayer2Name(){
        return this.player2Name.getText();
    }


}


