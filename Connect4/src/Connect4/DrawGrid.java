package Connect4;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class DrawGrid {
    private JFrame frame;
    private Players players;
    private Computer computer;

    public DrawGrid(Players players) {
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

    public DrawGrid(Computer computer){
        this.computer = computer;
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
