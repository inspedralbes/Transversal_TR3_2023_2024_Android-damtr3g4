package helpers;

public class Game {
    private String numPlayers;
    private String state;
    private String password;

    public Game(String numPlayers, String state, String password) {
        this.numPlayers = numPlayers;
        this.state = state;
        this.password = password;
    }

    public Game(String state, String password) {
        this.state = state;
        this.password = password;
    }

    public String getNumPlayers() {
        return numPlayers;
    }

    public void setNumPlayers(String numPlayers) {
        this.numPlayers = numPlayers;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
