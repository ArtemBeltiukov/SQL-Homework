package store.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "balance")
public class Balance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int nomenclature_id;

    @Column(name = "amount")
    private int amount;

    public Balance(int amount) {
        this.amount = amount;
    }

    public Balance() {
    }

    public int getAmount() {
        return amount;
    }
}
