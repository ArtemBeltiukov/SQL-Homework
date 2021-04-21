package store.initializers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import store.db.*;
import store.model.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataInitializer {

    private final Logger logger = LoggerFactory.getLogger(getClass().getName());

    public void init() {
        OrderService orderService = new OrderService();
        NomenclatureService nomenclatureService = new NomenclatureService();
        UserService userService = new UserService();
        BouquetService bouquetService = new BouquetService();
        OrderBouquetsService orderBouquetsService = new OrderBouquetsService();
        PriceService priceService = new PriceService();
        BalanceService balanceService = new BalanceService();
        List<Counteragent> counteragentList = new ArrayList<>();
        counteragentList.add(new Counteragent("'OOO \"IzhMoloko\"'"));
        counteragentList.add(new Counteragent("'OOO \"My defence\"'"));
        counteragentList.add(new Counteragent("'individual'"));
        counteragentList.add(new Counteragent("'OOO \"Battery\"'"));

        CounteragentService counteragentService = new CounteragentService();
        counteragentList.forEach(x -> {
            try {
                x.setId(counteragentService.createCounteragent(x));
            } catch (SQLException throwables) {
                logger.info("Error creating counteragent." + throwables);
            }
        });

        List<User> users = new ArrayList<>();
        users.add(new User("Artem", "123"));
        users.add(new User("Andrew", "123"));
        users.forEach(x -> {
            try {
                x.setId(userService.createUser(x));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        List<Order> orders = new ArrayList<>();
        orders.add(new Order(users.get(0).getId(), counteragentList.get(0).getId()));
        orders.add(new Order(users.get(1).getId(), counteragentList.get(1).getId()));
        orders.forEach(x -> {
            try {
                x.setId(orderService.createOrder(x));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        List<Bouquet> bouquets = new ArrayList<>();
        bouquets.add(new Bouquet(orders.get(0).getId(), 1, 100));
        bouquets.add(new Bouquet(orders.get(1).getId(), 3, 300));
        bouquets.add(new Bouquet(orders.get(0).getId(), 4, 400));
        bouquets.add(new Bouquet(orders.get(1).getId(), 5, 500));
        bouquets.forEach(x -> {
            try {
                x.setId(bouquetService.createBouquet(x));
                x.setOrderID(orderBouquetsService.createOrderBouquets(x));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        List<Nomenclature> nomenclatures = new ArrayList<>();
        Map<String, String> specs = new HashMap<>();
        specs.put("Color", "Yellow");
        nomenclatures.add(new Nomenclature(bouquets.get(0).getId(), "Sunflower", bouquets.get(0).getPrice(), 100, specs));
        nomenclatures.add(new Nomenclature(bouquets.get(0).getId(), "Rose", bouquets.get(1).getPrice(), 200, specs));

        nomenclatures.forEach(x -> {
            try {
                x.setId(nomenclatureService.createNomenclature(x));
                priceService.createPrice(x);
                balanceService.createBalance(x);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        try {
            orderService.realizeOrder(orders.get(0));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
