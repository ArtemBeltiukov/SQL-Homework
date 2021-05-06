package store.initializers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import store.db.services.CounteragentService;
import store.db.services.NomenclatureService;
import store.db.services.OrderService;
import store.db.services.UserService;
import store.model.Counteragent;
import store.model.Nomenclature;
import store.model.Order;
import store.model.User;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DataInitializer {
    @Autowired
    private OrderService orderService;
    @Autowired
    private NomenclatureService nomenclatureService;
    @Autowired
    private UserService userService;
    @Autowired
    private CounteragentService counteragentService;

    @PostConstruct
    public void initData() {

        List<Counteragent> counteragentList = new ArrayList<>();
        counteragentList.add(new Counteragent("OOO \"IzhMoloko\""));
        counteragentList.add(new Counteragent("OOO \"My defence\""));
        counteragentList.add(new Counteragent("individual"));
        counteragentList.add(new Counteragent("OOO \"Battery\""));
        counteragentList.forEach(x -> x.setId(counteragentService.create(x)));

        List<User> users = new ArrayList<>();
        users.add(new User("Artem", "123", "ADMIN"));
        users.add(new User("Andrew", "123", "USER"));
        users.forEach(x -> x.setId(userService.create(x)));

        List<Nomenclature> nomenclatures = new ArrayList<>();
        nomenclatures.add(new Nomenclature("Sunflower", 10, 100, 10));
        nomenclatures.add(new Nomenclature("Rose", 20, 200, 10));
        nomenclatures.forEach(x -> x.setId(nomenclatureService.create(x)));

        List<Order> orders = new ArrayList<>();
        orders.add(new Order(users.get(0).getId(), counteragentList.get(0).getId()));
        orders.add(new Order(users.get(1).getId(), counteragentList.get(1).getId()));
        orders.forEach(x -> x.setId(orderService.create(x)));

    }
}
