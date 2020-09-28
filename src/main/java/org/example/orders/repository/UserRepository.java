package org.example.orders.repository;

import org.example.orders.model.User;

import java.util.List;

public interface UserRepository {
    List<User> getAll();

    User get(int id);

    boolean setRole(int id);

    User getByName(String name);

}
