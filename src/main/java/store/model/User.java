package store.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User implements Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String password;
    private String role;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User(String name, String password, String role) {
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            return ((User) obj).name.equals(this.name) && ((User) obj).password.equals(this.password);
        } else
            return false;
    }
}
