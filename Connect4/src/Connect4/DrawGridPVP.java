package Connect4;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class DrawGridPVP {
    private JFrame frame;
    private Players players;

    public DrawGridPVP(Players players) {
        this.players = players;
        frame = new JFrame("DrawGrid");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(frame.getSize());
        MultiDraw multiDraw = new MultiDraw(frame.getSize(), players);
        frame.setLocationRelativeTo(null);
        frame.add(multiDraw);
        frame.pack();
        frame.setVisible(true);
    }


}
