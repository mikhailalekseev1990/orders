package org.example.orders.repository;

import org.example.orders.model.Request;
import org.example.orders.model.User;

import java.util.List;

public interface UserRepository {
    List<User> getAll();

    User get(int id);

    User save(User user);

    User setRole(int id, String role);

    User deleteRole(int id, String role);
}
