package Connect4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MultiDraw extends JPanel implements MouseListener {
    int startX = 10;
    int startY = 10;
    int cellSize = 60;
    int turn = 2;
    int rows = 6;
    int cols = 7;
    boolean winner=false;
    Players players;
    String color = "";
    Color[][] grid = new Color[rows][cols];//6 rows, 7 columns of colors


    public MultiDraw(Dimension dimension, Players players) {
        this.players = players;
        setSize(dimension);
        setPreferredSize(dimension);
        addMouseListener(this);
        //1. initialize array here
        int x = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                Color c;
                if (x%2 == 0) {
                    grid[row][col] = Color.white;
                }
                else {
                    grid[row][col] = Color.white;
                }
                x++;

            }

        }
    } //end MultiDraw

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        Dimension d = getSize();
        g2.setColor(Color.black);
        g2.fillRect(0,0,d.width,d.height);
        startX = 0;
        startY = 0;

        //2) draw grid here
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {

                g2.setColor(grid[row][col]);
                g2.fillOval(startX,startY,cellSize,cellSize);
                g2.setColor(Color.black);
                g2.drawOval(startX,startY,cellSize,cellSize);
                startX += cellSize;
            }
            startY += cellSize;
            startX = 0;
        }

        g2.setColor(new Color(255, 255, 255));
        if (!winner) {
            if (turn%2 == 0)
                g2.drawString(players.getPlayer1Name() +"'s Turn",475,20);
            else
                g2.drawString( players.getPlayer2Name() +"'s Turn",475,20);
        }
        else {
            if (color.equals("RED")) {
                g2.drawString("WINNER - " + players.getPlayer1Name(), 475, 20);
            }
            else {
                g2.drawString("WINNER - " + players.getPlayer2Name(), 475, 20);
            }
        }
    } //end paintComponent

    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if (winner == false) {
            if (x < (cellSize * grid[0].length) && y < (cellSize * grid.length)) {
                int clickedRow = y/cellSize;
                int clickedCol = x/cellSize;

                clickedRow = dropP(clickedCol);

                if (clickedRow != -1) {

                    if (turn %2 == 0) {
                        grid[clickedRow][clickedCol]= Color.red;
                        color =  "RED";
                    }
                    else {
                        grid[clickedRow][clickedCol]= Color.yellow;
                        color = "Yellow";
                    }
                    turn++;
                    if (checkForWinner(clickedCol,clickedRow, grid[clickedRow][clickedCol])) {
                        winner = true;

                    }
                }
            }
            repaint();
        }
    } //end mousePressed

    public int dropP(int cc){
        int cr = grid.length - 1;

        while (cr>=0) {

            if (grid[cr][cc].equals(Color.white)) {
                return cr;
            }
            cr--;
        }

        return -1;

    } //end dropP

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mouseClicked(MouseEvent e) {

    }

    //Marvel

    public boolean checkForWinner(int cc,int cr, Color c){
        //search left and right
        int xStart = cc;
        int count = 1;
        //check left
        xStart--;
        while (xStart >= 0) {
            if (grid[cr][xStart].equals(c)) {
                count++;
            }
            else {
                break;
            }
            if (count==4)
                return true;

            xStart--;
        }

        //check right
        xStart = cc;
        xStart++;
        while (xStart < grid[0].length) {

            if(grid[cr][xStart].equals(c)) {

                count++;
            }
            else {
                break;
            }
            if(count == 4)
                return true;

            xStart++;
        }

        //check up
        count = 1;
        int yStart = cr;
        yStart--;
        while (yStart>0) {
            if (grid[yStart][cc].equals(c)) {
                count++;
            }
            else {
                break;
            }
            if (count == 4)
                return true;

            yStart--;
        }

        //check right
        yStart = cr;
        yStart++;
        while (yStart<grid.length) {

            if (grid[yStart][cc].equals(c)) {

                count++;
            }
            else {
                break;
            }
            if (count == 4)
                return true;

            yStart++;
        }

        //check up and right diagonally
        count = 1;
        yStart = cr;
        xStart = cc;
        xStart--;
        yStart--;
        while (yStart>0 && xStart>0) {
            if (grid[yStart][xStart].equals(c)) {
                count++;
            }
            else {
                break;
            }
            if (count == 4)
                return true;

            yStart--;
            xStart--;
        }

        //check down and right diagonally
        yStart = cr;
        yStart++;
        xStart = cc;
        xStart++;
        while(yStart < grid.length && xStart < grid.length) {

            if (grid[yStart][xStart].equals(c)) {

                count++;
            }
            else {
                break;
            }
            if (count == 4)
                return true;

            yStart++;
            xStart++;
        }


        //check down and left diagonally
        count = 1;
        yStart = cr;
        xStart = cc;
        xStart--;
        yStart++;
        while (yStart < grid.length && xStart > 0) {
            if (grid[yStart][xStart].equals(c)) {
                count++;
            }
            else {
                break;
            }
            if (count == 4)
                return true;

            yStart++;
            xStart--;
        }

        //check up and left diagonally
        yStart = cr;
        yStart--;
        xStart = cc;
        xStart++;
        while (yStart > 0 && xStart < grid.length) {

            if (grid[yStart][xStart].equals(c)) {

                count++;
            }
            else {
                break;
            }
            if (count == 4)
                return true;

            yStart--;
            xStart++;
        }

        return false;
    } //end checkForWinner

//    public void reset(){
//        winner = false;
//        turn = 2;
//        for (int row = 0; row < grid.length; row++) {
//            for (int col = 0; col < grid[0].length; col++) {
//                grid[row][col] = Color.white;
//
//            }
//        }
//    } //end reset
}//end of class

