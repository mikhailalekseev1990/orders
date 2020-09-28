package org.example.orders.repository;

import org.example.orders.model.Order;

import java.util.List;

public interface OrderRepository {

    Order save(Order order, int userId);

    Order getByUser(int id, int userId);

    Order getByOperator(int id);

    List<Order> getAllByUser(int userId);

    List<Order> getAllByOperator();

    boolean changeStatus(int id, String status);
}
