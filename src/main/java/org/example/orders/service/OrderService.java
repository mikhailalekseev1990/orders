package org.example.orders.service;


import org.example.orders.model.Order;
import org.example.orders.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static org.example.orders.util.ValidationUtil.*;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public Order getByUser(int id, int userId) {
        return checkNotFoundWithId(orderRepository.getByUser(id, userId), id);
    }

    public Order getByOperator(int id) {
        return checkNotFoundWithId(orderRepository.getByOperator(id), id);
    }

    public void update(Order order, int userId) {
        Assert.notNull(order, "order must not be null");
        checkNotFoundWithId(orderRepository.save(order, userId), order.id());
    }

    public Order create(Order order, int userId) {
        Assert.notNull(order, "order must not be null");
        return orderRepository.save(order, userId);
    }

    public List<Order> getAllByUser(int userId) {
        return orderRepository.getAllByUser(userId);
    }

    public List<Order> getAllByOperator() {
        return orderRepository.getAllByOperator();
    }

    public void changeStatus(int id, String status) {
        Assert.notNull(status, "order must not be null");
        checkNotFoundWithId(orderRepository.changeStatus(id, status), id);
    }

}
