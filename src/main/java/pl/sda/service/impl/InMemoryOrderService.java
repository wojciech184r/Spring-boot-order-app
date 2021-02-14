package pl.sda.service.impl;

import org.springframework.stereotype.Service;
import pl.sda.model.Order;
import pl.sda.service.OrderService;

import java.util.ArrayList;
import java.util.List;

@Service
public class InMemoryOrderService implements OrderService {

    private List<Order> orders = new ArrayList<>();

    private int nextId = 1;

    @Override
    public Order getById(Integer id) {

        for (Order order : orders) {
            if (order.getId().equals(id)) {
                return order;
            }
        }

        throw new IllegalArgumentException("Brak zamówienia dla id: " + id);
    }

    @Override
    public List<Order> getAll() {
        return orders;
    }

    @Override
    public void save(Order order) {
        order.setId(nextId++);
        orders.add(order);
    }

    @Override
    public Object update(Order order) {

        for (int i = 0; i<orders.size(); i++) {
            if (orders.get(i).getId().equals(order.getId())) {
                orders.get(i).setProductName(order.getProductName());
                orders.get(i).setAmount(order.getAmount());
                break;
            }
        }

        return order;
    }

    @Override
    public Object delete(Integer id) {
        for (int i = 0; i<orders.size(); i++) {
            if (orders.get(i).getId().equals(id)) {
                orders.remove(id);
            }
        }
        return orders;
    }


}
