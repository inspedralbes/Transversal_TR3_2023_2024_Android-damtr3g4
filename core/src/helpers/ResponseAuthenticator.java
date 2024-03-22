package helpers;

public class ResponseAuthenticator {
    private boolean authorization;
    private String name;
    private int id;

    public ResponseAuthenticator() {
    }

    public ResponseAuthenticator(boolean authorization, String name, int id) {
        this.authorization = authorization;
        this.name = name;
        this.id = id;
    }

    public boolean isAuthorization() {
        return authorization;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
