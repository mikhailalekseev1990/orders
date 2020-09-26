package org.example.orders.service;

import org.example.orders.model.User;
import org.example.orders.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.example.orders.util.ValidationUtil.checkNotFoundWithId;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public List<User> getAll() {
        return userRepository.getAll();
    }

    public User get(int id) {
        return checkNotFoundWithId(userRepository.get(id), id);
    }

    public User save(User user) {
        return null;
    }

    public User setRole(int id, String role) {
        return null;
    }

    public User deleteRole(int id, String role) {
        return null;
    }
}
