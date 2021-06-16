package store.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.util.List;


//@XmlAccessorType(XmlAccessType.FIELD)
//@XmlType(name = "order", propOrder = {
//        "id",
//        "userID",
//        "counteragent"
//})
@Entity
@Table(name = "orders")
public class Order implements Model {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "userID")
    private int userID;
    @Column(name = "counteragent")
    private int counteragent;

    public Order(int userID, int counteragent) {
        this.userID = userID;
        this.counteragent = counteragent;
    }

    public Order() {

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
