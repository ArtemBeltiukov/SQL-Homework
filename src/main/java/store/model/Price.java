package store.model;

import javax.persistence.*;

@Entity
@Table(name = "price")
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int nomenclature_id;

    @Column(name = "price")
    private int price;

    public Price(int price) {
        this.price = price;
    }

    public Price() {
    }

    public int getPrice() {
        return price;
    }
}
