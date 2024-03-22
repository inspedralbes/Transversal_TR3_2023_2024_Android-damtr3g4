package helpers;

public class DataGameJoinSend {
    private String passwordGame;
    private int idUser;

    public DataGameJoinSend(String passwordGame, int idUser) {
        this.passwordGame = passwordGame;
        this.idUser = idUser;
    }

    public String getPasswordGame() {
        return passwordGame;
    }

    public int getIdUser() {
        return idUser;
    }
}
