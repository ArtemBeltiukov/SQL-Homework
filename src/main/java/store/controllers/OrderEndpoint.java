package store.controllers;

import com.example.gen.GetOrdersRequest;
import com.example.gen.GetOrdersResponse;
import com.example.gen.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedCaseInsensitiveMap;
import org.springframework.ws.server.endpoint.PayloadEndpoint;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import store.db.services.OrderService;

import javax.xml.transform.Source;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;


//@Endpoint
public class OrderEndpoint implements PayloadEndpoint {
    private static final String NAMESPACE_URI = "http://www.example.com/gen";

    private OrderService orderService;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public OrderEndpoint(OrderService orderService) {
        this.orderService = orderService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getOrdersRequest")
    @ResponsePayload
    public GetOrdersResponse getOrders(@RequestPayload GetOrdersRequest request) {
        log.info("get orders request with userID = " + request.getUserID());
        GetOrdersResponse response = new GetOrdersResponse();
        LinkedHashMap<String, Object> all = (LinkedHashMap<String, Object>) orderService.getAll(request.getUserID());

        List<Order> orders = all.values().stream().map(o -> {
            LinkedCaseInsensitiveMap rs = ((LinkedCaseInsensitiveMap) ((ArrayList) o).get(0));
            Order order = new Order();
            String id = rs.get("id").toString();
            String userID = rs.get("userID").toString();
            String counteragent = rs.get("counteragent").toString();
            order.setId(BigInteger.valueOf(Long.parseLong(id)));
            order.setUserID(BigInteger.valueOf(Long.parseLong(userID)));
            order.setCounteragent(BigInteger.valueOf(Long.parseLong(counteragent)));
            return order;
        }).collect(Collectors.toList());

        response.setOrder(orders.get(0));
        return response;
    }

    @Override
    public Source invoke(Source request) throws Exception {
        return null;
    }
}
