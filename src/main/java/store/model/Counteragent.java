package store.model;

public class Counteragent {
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

    public String getQuery() {
        return "INSERT INTO counteragents(name) VALUES('" + name + "')";
    }
}
