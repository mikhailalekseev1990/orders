package org.example.orders.repository;

import org.example.orders.model.Order;

import java.util.List;

public interface OrderRepository {
    Order save(Order order, int userId);

    boolean delete(int id, int userId);

    Order get(int id, int userId);

    // ORDERED dateTime desc
    List<Order> getAllByUser(int userId);

    List<Order> getAll();
}
