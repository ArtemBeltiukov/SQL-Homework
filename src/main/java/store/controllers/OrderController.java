package store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import store.db.services.OrderService;
import store.model.Model;
import store.model.Order;

import java.util.List;

//@RestController
//@RequestMapping("/api/orders")
public class OrderController implements ApiController {

    @Autowired
    OrderService orderService;

    @Override
    @GetMapping("/")
    public ResponseEntity<List<Model>> getAll() {
        return null;
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Model> getById(@PathVariable(name = "id") int id) {
        Order order = new Order();
        order.setId(id);
        try {
            Model response = orderService.read(order);
            if (response == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            else
                return new ResponseEntity<>(order, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @PostMapping("/new")
    public ResponseEntity<?> create() {

        return null;
    }

}
