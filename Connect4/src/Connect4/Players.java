package Connect4;

public class Players {
    private String player1Name;
    private String player2Name;


    public Players(String p1, String p2){
        this.player1Name = p1;
        this.player2Name = p2;
    }

    public String getPlayer1Name(){
        return this.player1Name;
    }

    public String getPlayer2Name(){
        return this.player2Name;
    }


}
