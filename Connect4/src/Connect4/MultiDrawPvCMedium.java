

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
        int[] yValues = {5,5,5,5,5,5,5};


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
                    g2.drawString("WINNER - Computer", 475, 20);
                }
            }

        }

        public void mousePressed(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            if(!winner){
                if(x<(cellSize*grid[0].length) && y<(cellSize*grid.length)){
                    int clickedRow = y/cellSize;
                    int clickedCol = x/cellSize;

                    clickedRow = dropP(clickedCol);

                    if(clickedRow!=-1){


                            grid[clickedRow][clickedCol]= Color.red;
                            color =  "RED";
                            this.yValues[clickedCol] -= 1;

                        /*else{
                            grid[clickedRow][clickedCol]= Color.yellow;
                            color =  "Yellow";
                            this.yValues[clickedCol] += 1;
                        }*/
                        //turn++;
                        if(checkForWinner(clickedCol,clickedRow, grid[clickedRow][clickedCol])){
                            winner=true;

                        }
                        else{
                            int ret = AI_move(Color.yellow);
                            grid[this.yValues[ret]][ret] = Color.yellow;
                            color = "Yellow";


                            if(checkForWinner(this.yValues[ret],ret, grid[this.yValues[ret]][ret])){
                                winner=true;

                            }
                            this.yValues[ret] -= 1;
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

        }

        public void mouseClicked(MouseEvent e) {

        }

        public boolean checkForWinner(int cc,int cr, Color c){
            return checkOccurence(cr,cc,c,4) > 0;
        }


        public int checkOccurence(int cr,int cc, Color c, int target_count){
            //search west and east

            int count_of_occurences = 0;
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
                if(count==target_count)
                    count_of_occurences++;

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
                if(count==target_count)
                    count_of_occurences++;

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
                if(count==target_count)
                    count_of_occurences++;

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
                if(count==target_count)
                    count_of_occurences++;

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
                if(count==target_count)
                    count_of_occurences++;

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
                if(count==target_count)
                    count_of_occurences++;

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
                if(count==target_count)
                    count_of_occurences++;

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
                if(count==target_count)
                    count_of_occurences++;

                yStart--;
                xStart++;
            }

            return count_of_occurences;
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

        public int AI_move(Color color){
            Color opposite = null;
                if(color.equals(Color.red))
                    opposite = Color.yellow;

                else opposite = Color.red;


            int count = 0;
            int test = 0;
            int[] scored_moves = {0,1,2,3,2,1,0};
            //For each turn, there can only be 7 possible moves, 1 for each coordinate
            //The index of scored_moves represents the x-coordinate for the board.
            //The reason each index is not set to zero is because we want the AI to prioritize the center
            //columns, so if there are 2 turns that have the same strategic value, the AI will chose the
            // The most central of the 2 turns.


            for(int i = 0; i< this.yValues.length;i++){
                //I added a global array yValues to keep track of which y coordinates are open to use
                //To get a valid coordinate, pick the x value, then the y value is yValue[x] and thats where the
                //piece would go on the board.

                count = 0;
                test = checkOccurence(this.yValues[i],i,color,3);
                //test represents how many "3 in a row" segments are connected to this specific coordinate
                // I modified the check for winner method to have a 4th parameter and that's the "# in a row" you
                //want that method to find.



                //The Logic for this goes as follows:
                    //Check if theres an offensive winning move
                    //If not, check if theres a deffensive move to save the game
                    //If not, check if theres an offensive move to set up 3 in a row
                    //If not, check if theres a defensive move to prevent a 3 in a row
                    //If not, check for offensive 2 in a row
                    //If not, check for preventing a 2 in a row



                //*******************************************************************************
                //Here starts the trial an error of a successful algorithm
                // Here I have a winning move valued at 10,000 times the amount of 3 in a rows the move
                // Is connected to, so for example, a move that simultaniously connects 2 "3 in a row"s
                //Is worth twice as valuable as a move that just connects 1 "3 in a row"
                // What needs to be refined is the "count += test*..." lines, I dont know what values
                //Would make the algo decide the best move everytime.

                 if(test > 0)
                     //if there is
                     count += test*10000;


                 //If theres no winning move, check if theres a winning move can be blocked
                 else {
                     test = checkOccurence(this.yValues[i], i, opposite, 3);
                     //Now we check the same peice but it's value for the opponent, call to the same
                     //Method but with the opposite color

                     if(test > 0)
                         count += test*9000;
                        //Right now I have a defensive move worth 10% less than its offensive counterpart
                        //This would prevent the problem skipping over a winning move to play defense

                     else{
                         //If theres no "3 in a row" to block, check for an offensive "2 in a row" (2iar)
                         // No offensive 2iar, check for defensive 2iar
                         test = checkOccurence(this.yValues[i],i,color,2);
                         if(test > 0)
                             count += test*100;

                         else{
                             test = checkOccurence(this.yValues[i], i, opposite, 2);

                             if(test > 0)
                                 count += test*90;

                             else{
                                 test = checkOccurence(this.yValues[i],i,color,1);
                                 if(test > 0)
                                     count += test*10;
                                 else{
                                     test = checkOccurence(this.yValues[i], i, opposite, 1);

                                     if(test > 0)
                                         count += test*9;
                                 }
                             }
                         }
                     }
                 }

                scored_moves[i] += count;
            }

            //By here, scored_moves[] is filled with the strategic value for each of the 7 possible moves
            //Find the index of the max of scored_moves and then we have the x coordinate of the best move.
            //It is crucial that the values of scored_moves[]s do not get rearranged as the index decides what
            //move the computer makes.


            int max = 0;
            int index = 0;
            for(int i = 0; i < scored_moves.length;i++){
                if(max < scored_moves[i]) {
                    max = scored_moves[i];
                    index = i;
                }
            }


            return index;
        }



        //Everything past this point is the python code I tried to convert to java but it didn't work
        public int scorePosition(int y, int x, Color color){
            int score = 0;
            int countPiece = 0;
            int countOpposite = 0;
            int  countEmpty = 0;

            int row, column;
            Color[] rowColors, columnColors;
            Color[] window;

            //Horizontal Check
            for (row = 0; row < 6; row ++){
                rowColors = this.grid[row];
                for(column = 0; column < 3; column++){
                    window = new Color[]{rowColors[column], rowColors[column + 1], rowColors[column + 2], rowColors[column + 3]};
                    score += evaluate_Window(window,color);



                    countPiece = 0;
                    countOpposite = 0;
                    countEmpty = 0;


                    for(int i = 0; i < window.length ; i++){
                        Color test = window[i];
                        if(test.equals(color)) countPiece++;

                        else if(test.equals(Color.white)) countEmpty++;

                        else countOpposite++;
                    }

                    if(countPiece == 4) score = 1000;

                    else if(countPiece == 3 && countEmpty == 1) score = 100;

                    else if(countPiece == 2 && countEmpty == 2) score = 10;

                    else if(countOpposite > countPiece) score = 1000;

                    if(countOpposite == 3 && countEmpty == 1) score = 1000000;

                }
            }

            //Vertical  check
            for(column = 0; column < 7; column++){
                columnColors = new Color[6];
                for (int i = 0; i < columnColors.length-4; i++) {
                    columnColors[i] = this.grid[i][column];
                    window = new Color[]{this.grid[i][column], this.grid[i+1][column],
                            this.grid[i+2][column], this.grid[i+3][column]};


                    countPiece = 0;
                    countOpposite = 0;
                    countEmpty = 0;


                    for(i = 0; i < window.length ; i++){
                        Color test = window[i];
                        if(test.equals(color)) countPiece++;

                        else if(test.equals(Color.white)) countEmpty++;

                        else countOpposite++;
                    }

                    if(countPiece == 4) score+= 1000;

                    else if(countPiece == 3 && countEmpty == 1) score = 100;

                    else if(countPiece == 2 && countEmpty == 2) score = 10;

                    else if(countOpposite > countPiece) score = 1000;

                    if(countOpposite == 3 && countEmpty == 1) score = 1000000;

                }

                /*for(row = 0; row <= 2; row++ ){
                    window = new Color[]{columnColors[row],columnColors[row+1],
                            columnColors[row+2],columnColors[row+3]};
                    score += evaluate_Window(window,color);
                }*/
            }

            //positive sloped diagonal check
            for(row = 0; row < 3; row++){
                for(column = 0; column < 4; column++){
                        window = new Color[]{this.grid[row][column],this.grid[row+1][column+1],
                                this.grid[row+2][column+2],this.grid[row+3][column+3]};

                    countPiece = 0;
                    countOpposite = 0;
                    countEmpty = 0;


                    for(int i = 0; i < window.length ; i++){
                        Color test = window[i];
                        if(test.equals(color)) countPiece++;

                        else if(test.equals(Color.white)) countEmpty++;

                        else countOpposite++;
                    }

                    if(countPiece == 4) score+= 1000;

                    else if(countPiece == 3 && countEmpty == 1) score = 100;

                    else if(countPiece == 2 && countEmpty == 2) score = 10;

                    else if(countOpposite > countPiece) score = 1000;

                    if(countOpposite == 3 && countEmpty == 1) score = 1000000;

                }
            }
            //negative slope diagonal check
            for(row = 0; row > 2; row--){
                for(column = 0; column < 3; column++){
                    window = new Color[]{this.grid[row][column],this.grid[row-1][column+1],
                            this.grid[row-2][column+2],this.grid[row-3][column+3]};

                    countPiece = 0;
                    countOpposite = 0;
                    countEmpty = 0;


                    for(int i = 0; i < window.length ; i++){
                        Color test = window[i];
                        if(test.equals(color)) countPiece++;

                        else if(test.equals(Color.white)) countEmpty++;

                        else countOpposite++;
                    }

                    if(countPiece == 4) score+= 1000;

                    else if(countPiece == 3 && countEmpty == 1) score = 100;

                    else if(countPiece == 2 && countEmpty == 2) score = 10;

                    else if(countOpposite > countPiece) score = 1000;

                    if(countOpposite == 3 && countEmpty == 1) score = 1000000;

                }
            }

            return score;
        }

        public int evaluate_Window(Color[] window, Color piece){
            int score =  0;
            int countPiece = 0;
            int countOpposite = 0;
            int  countEmpty = 0;


            for(int i = 0; i < window.length ; i++){
                Color test = window[i];
                if(test.equals(piece)) countPiece++;

                else if(test.equals(Color.white)) countEmpty++;

                else countOpposite++;
            }

            if(countPiece == 4) score+= 1000;

            else if(countPiece == 3 && countEmpty == 1) score += 100;

            else if(countPiece == 2 && countEmpty == 2) score += 10;

            else if(countOpposite > countPiece) score += 1000;

            if(countOpposite == 3 && countEmpty == 1) score += 1000000;

            return score;


        }



    }//end of class



