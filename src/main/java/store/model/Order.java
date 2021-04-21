package store.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int id;
    private int userID;
    private int counteragent;

    public Order(int userID, int counteragent) {
        this.userID = userID;
        this.counteragent = counteragent;
    }

    public String getQuery() {
        String query;
        if (counteragent != 0)
            query = "INSERT INTO orders(userID,counteragent) VALUES (" + userID + ", " + counteragent + ")";
        else
            query = "INSERT INTO orders(userID,counteragent) VALUES (" + userID + ", null)";

        return query;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setCounteragent(int counteragent) {
        this.counteragent = counteragent;
    }

    public int getId() {
        return id;
    }

    public int getUserID() {
        return userID;
    }

    public int getCounteragent() {
        return counteragent;
    }


}
