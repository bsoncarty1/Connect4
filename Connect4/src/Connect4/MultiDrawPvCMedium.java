

    package Connect4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

    public class MultiDrawPvCMedium extends JPanel implements MouseListener {
        int startX =  10;
        int startY = 10;
        int cellSize = 60;
        int turn = 2;
        int rows = 6;
        int cols = 7;
        boolean winner=false;
        Players players;
        String color = "";
        Color[][] grid = new Color[rows][cols];//6 rows, 7 columns of colors
        int[] yValues = {0,0,0,0,0,0,0};


        public MultiDrawPvCMedium(Dimension dimension, Players players) {
            this.players = players;
            setSize(dimension);
            setPreferredSize(dimension);
            addMouseListener(this);
            //1. initialize array here
            int x = 0;
            for (int row = 0; row < grid.length; row++) {
                for (int col = 0; col < grid[0].length; col++) {
                    Color c;
                    if(x%2==0){
                        grid[row][col] = Color.white;
                    }else{
                        grid[row][col] = Color.white;
                    }
                    x++;

                }

            }
        }

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
            if(!winner){
                if(turn%2==0)
                    g2.drawString(players.getPlayer1Name() +"'s Turn",475,20);
                else
                    g2.drawString( players.getPlayer2Name() +"'s Turn",475,20);
            }else{
                if(color.equals("RED")) {
                    g2.drawString("WINNER - " + players.getPlayer1Name(), 475, 20);
                }else{
                    g2.drawString("WINNER - " + players.getPlayer2Name(), 475, 20);
                }
            }

        }

        public void mousePressed(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            if(winner==false){
                if(x<(cellSize*grid[0].length) && y<(cellSize*grid.length)){
                    int clickedRow = y/cellSize;
                    int clickedCol = x/cellSize;

                    clickedRow = dropP(clickedCol);

                    if(clickedRow!=-1){

                        if(turn%2==0){
                            grid[clickedRow][clickedCol]= Color.red;
                            color =  "RED";
                            this.yValues[clickedCol] += 1;
                        } else{
                            grid[clickedRow][clickedCol]= Color.yellow;
                            color =  "Yellow";
                            this.yValues[clickedCol] += 1;
                        }
                        turn++;
                        if(checkForWinner(clickedCol,clickedRow, grid[clickedRow][clickedCol])){
                            winner=true;

                        }
                    }
                }
                repaint();
            }
        }

        public int dropP(int cc){
            int cr = grid.length-1;

            while(cr>=0){

                if(grid[cr][cc].equals(Color.white)){
                    return cr;
                }
                cr--;
            }

            return -1;

        }

        public void mouseReleased(MouseEvent e) {

        }

        public void mouseEntered(MouseEvent e) {

        }

        public void mouseExited(MouseEvent e) {
            reset();
        }

        public void mouseClicked(MouseEvent e) {

        }

        //Marvel


        public boolean checkForWinner(int cc,int cr, Color c){
            //search west and east
            int xStart = cc;
            int count = 1;
            //check west
            xStart--;
            while(xStart>=0){
                if(grid[cr][xStart].equals(c)){
                    count++;
                }else{
                    break;
                }
                if(count==4)
                    return true;

                xStart--;
            }

            //check east
            xStart = cc;
            xStart++;
            while(xStart<grid[0].length){

                if(grid[cr][xStart].equals(c)){

                    count++;
                }else{
                    break;
                }
                if(count==4)
                    return true;

                xStart++;
            }

            //check North
            count = 1;
            int yStart = cr;
            yStart--;
            while(yStart>0){
                if(grid[yStart][cc].equals(c)){
                    count++;
                }else{
                    break;
                }
                if(count==4)
                    return true;

                yStart--;
            }

            //check east
            yStart = cr;
            yStart++;
            while(yStart<grid.length){

                if(grid[yStart][cc].equals(c)){

                    count++;
                }else{
                    break;
                }
                if(count==4)
                    return true;

                yStart++;
            }

            //check NorthWest
            count = 1;
            yStart = cr;
            xStart = cc;
            xStart--;
            yStart--;
            while(yStart>0 && xStart>0){
                if(grid[yStart][xStart].equals(c)){
                    count++;
                }else{
                    break;
                }
                if(count==4)
                    return true;

                yStart--;
                xStart--;
            }

            //check Southeast
            yStart = cr;
            yStart++;
            xStart = cc;
            xStart++;
            while(yStart<grid.length && xStart<grid.length){

                if(grid[yStart][xStart].equals(c)){

                    count++;
                }else{
                    break;
                }
                if(count==4)
                    return true;

                yStart++;
                xStart++;
            }


            //check southWest
            count = 1;
            yStart = cr;
            xStart = cc;
            xStart--;
            yStart++;
            while(yStart<grid.length && xStart>0){
                if(grid[yStart][xStart].equals(c)){
                    count++;
                }else{
                    break;
                }
                if(count==4)
                    return true;

                yStart++;
                xStart--;
            }

            //check Northeast
            yStart = cr;
            yStart--;
            xStart = cc;
            xStart++;
            while(yStart>0 && xStart<grid.length){

                if(grid[yStart][xStart].equals(c)){

                    count++;
                }else{
                    break;
                }
                if(count==4)
                    return true;

                yStart--;
                xStart++;
            }

            return false;
        }

        public void reset(){
            winner=false;
            turn=2;
            for (int row = 0; row < grid.length; row++) {
                for (int col = 0; col < grid[0].length; col++) {
                    grid[row][col] = Color.white;

                }
            }
        }


        public int scorePosition(int x, int y, Color color){
            int score = 0;
            int row, column;
            Color[] rowColors, columnColors;
            Color[] window;

            //Horizontal Check
            for (row = 0; row < 6; row ++){
                rowColors = this.grid[row];
                for(column = 0; column < 3; column++){
                    window = new Color[]{rowColors[column], rowColors[column + 1], rowColors[column + 2], rowColors[column + 3]};
                    score += evaluate_Window(window,color);
                }
            }

            //Vertical check
            for(column = 0; column < 7; column++){
                columnColors = new Color[6];
                for (int i = 0; i < columnColors.length; i++)
                    columnColors[i] = this.grid[i][column];

                for(row = 0; row < 2; row++ ){
                    window = new Color[]{columnColors[row],columnColors[row+1],columnColors[row+2],columnColors[row+3]};
                    score += evaluate_Window(window,color);
                }
            }

            return score;
        }

        public int evaluate_Window(Color[] window, Color piece){
            int score = 0;
            int countPiece = 0;
            int countOpposite = 0;
            int countEmpty = 0;

            for(int i = 0; i < window.length ; i++){
                if(piece.equals(window[i])) countPiece++;

                else if(window[i].equals(Color.white)) countEmpty++;

                else countOpposite++;
            }

            if(countPiece == 4) score += 1000;

            else if(countPiece == 3 && countEmpty == 1) score += 100;

            else if(countPiece == 2 && countEmpty == 2) score += 10;

            if(countOpposite == 3 && countEmpty == 1) score -= 100;

            return score;


        }



    }//end of class



