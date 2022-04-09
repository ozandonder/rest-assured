import lombok.Data;

@Data
public class User {
    private int id;
    private String userName;
    private String password;

    void setUser(int id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }
}
