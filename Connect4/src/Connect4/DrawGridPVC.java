package Connect4;

import javax.swing.*;

public class DrawGridPVC {

    private JFrame frame;
    private Players players;
    private Computer computer;

    public DrawGridPVC(Players players){
        this.players = players;
        frame = new JFrame("DrawGrid");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(frame.getSize());
        MultiDrawPvCMedium multiDraw = new MultiDrawPvCMedium(frame.getSize(), players); //edit when code is made
        frame.setLocationRelativeTo(null);
        frame.add(multiDraw);
        frame.pack();
        frame.setVisible(true);
    }
}
