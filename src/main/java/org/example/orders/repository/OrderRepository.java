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

    // ORDERED dateTime desc
    List<Request> getAllByUser(int userId);

    List<Request> getAll();

    boolean changeStatus(int id, Status status);
}
