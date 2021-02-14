package pl.sda.service;

import pl.sda.model.Order;

import java.util.List;

public interface OrderService {

    Order getById(Integer id);

    List<Order> getAll();

    void save(Order order);

    Object update(Order order);

    Object delete(Integer id);


}
