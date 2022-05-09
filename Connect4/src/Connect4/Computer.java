package Connect4;

public class Computer {
    private String playerName;
    private String computerName = "Computer";



    public Computer(String p){
        this.playerName = p;
    }

    public String getComputerName() {
        return computerName;
    }

    public String getPlayerName() {
        return playerName;
    }
}
