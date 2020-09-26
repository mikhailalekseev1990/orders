package org.example.orders.service;


import org.example.orders.model.Request;
import org.example.orders.model.Status;
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

    public Request get(int id, int userId) {
        return checkNotFoundWithId(orderRepository.get(id, userId), id);
    }

    public void update(Request request, int userId) {
        Assert.notNull(request, "request must not be null");
        checkNotFoundWithId(orderRepository.save(request, userId), request.id());
    }

    public Request create(Request request, int userId) {
        Assert.notNull(request, "request must not be null");
        return orderRepository.save(request, userId);
    }

    public List<Request> getAllByUser(int userId) {
        return orderRepository.getAllByUser(userId);
    }

    public List<Request> getAll() {
        return orderRepository.getAll();
    }

    public void changeStatus(int id, Status status) {
        Assert.notNull(status, "order must not be null");
        checkNotFoundWithId(orderRepository.changeStatus(id, status), id);
    }

}
