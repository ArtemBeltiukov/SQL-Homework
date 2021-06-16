package store.model;

import javax.persistence.*;

@Entity
@Table(name = "nomenclature")
public class Nomenclature implements Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int bouquetId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "price", referencedColumnName = "nomenclature_id")
    private Price price;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "balance", referencedColumnName = "nomenclature_id")
    private Balance balance;

    public Nomenclature() {
    }

    public Nomenclature(String name, int price, int balance) {
        this.name = name;
        this.price = new Price(price);
        this.balance = new Balance(balance);
    }

    public Price getPrice() {
        return price;
    }

    public Balance getBalance() {
        return balance;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public void setBalance(Balance balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public int getBouquetId() {
        return bouquetId;
    }

    public void setBouquetId(int bouquetId) {
        this.bouquetId = bouquetId;
    }

}

