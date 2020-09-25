package org.example.orders.service;

import org.example.orders.model.Order;
import org.example.orders.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static org.example.orders.util.ValidationUtil.*;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order get(int id, int userId) {
        return checkNotFoundWithId(orderRepository.get(id, userId), id);
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

    public List<Order> getAll() {
        return orderRepository.getAll();
    }

}
