package store.model;

import javax.persistence.*;

@Entity
@Table(name = "counteragents")
public class Counteragent implements Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

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

    public Counteragent(String name) {
        this.name = name;
    }
}
