package mx.uach.srtm.models;

/**
 * Created by dani on 5/07/16.
 */
public class Usuario {

    private Integer id;
    private String name;
    private String username;
    private String email;

    public Usuario(){

    }

    public Usuario(Integer id, String name, String username, String email){
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString(){
        return String.format("%s - %s", this.getName(), this.getUsername());
    }

}
