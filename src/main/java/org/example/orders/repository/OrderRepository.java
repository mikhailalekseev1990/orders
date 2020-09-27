package org.example.orders.repository;

import org.example.orders.model.Request;
import org.example.orders.model.Status;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository {

    Request save(Request request, int userId);

    boolean delete(int id, int userId);

    Request get(int id, int userId);

    List<Request> getAllByUser(int userId);

    List<Request> getAllByOperator();

    boolean changeStatus(int id, String status);
}
