package store.model;

import java.util.HashMap;
import java.util.Map;

public class Nomenclature {
    private int id;
    private int bouquet;
    private String name;
    private int price;
    private int balance;
    private Map<String, String> specifications = new HashMap<>();

    public int getPrice() {
        return price;
    }

    public int getBalance() {
        return balance;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBouquet(int bouquet) {
        this.bouquet = bouquet;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setSpecifications(Map<String, String> specifications) {
        this.specifications = specifications;
    }

    public Nomenclature(int bouquet, String name, int price, int balance, Map<String, String> specifications) {
        this.bouquet = bouquet;
        this.name = name;
        this.price = price;
        this.balance = balance;
        this.specifications = specifications;
    }

    public String getQuery() {
        return "INSERT INTO nomenclature(name, bouquetID) VALUES('" + name + "'," + bouquet + ")";
    }

    public String getName() {
        return name;
    }

    public int getBouquet() {
        return bouquet;
    }
}
