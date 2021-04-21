package store.model;

public class Bouquet {
    private int id;
    private int orderID;
    private int amount;
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    private int price;

    public Bouquet(int orderID, int amount, int price) {
        this.orderID = orderID;
        this.amount = amount;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public int getOrderID() {
        return orderID;
    }

    public int getAmount() {
        return amount;
    }

    public int getPrice() {
        return price;
    }

    public String getQuery() {
        return "INSERT INTO bouquets(orderID) VALUES(" + orderID + ")";
    }

    public String getQueryInOrder() {
        return "INSERT INTO orders_bouquets(orderID, bouquet, price, count) VALUES(" + orderID + "," + id + "," + price + ", " + count + ")";
    }
}
