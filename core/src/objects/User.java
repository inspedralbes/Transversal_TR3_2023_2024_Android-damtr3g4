package objects;

public class User extends Character{
    private String correo;
    private String usuario;
    private int id;

    public User(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    public User(float x, float y, float width, float height, String correo, String usuario, int id) {
        super(x, y, width, height);
        this.correo = correo;
        this.usuario = usuario;
        this.id = id;
    }


    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
