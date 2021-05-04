package store.model;

import java.util.HashMap;
import java.util.Map;

public class Nomenclature implements Model {
    private int id;
    private String name;
    private int price;
    private int balance;
    private int count;

    public Nomenclature() {
    }

    public Nomenclature(String name, int price, int balance, int count) {
        this.name = name;
        this.price = price;
        this.balance = balance;
        this.count = count;
    }

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

    public int getCount() {
        return count;
    }

    public String getName() {
        return name;
    }
}
