package org.example.orders.service;

import org.example.orders.model.User;
import org.example.orders.repository.UserRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.example.orders.util.ValidationUtil.checkNotFoundWithId;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Cacheable("users")
    public List<User> getAll() {
        return userRepository.getAll();
    }

    public User get(int id) {
        return checkNotFoundWithId(userRepository.get(id), id);
    }

    public boolean setRole(int id) {
        return userRepository.setRole(id);
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        return userRepository.getByName(name);
    }

    public User getByName(String name) {
        return userRepository.getByName(name);
    }

}
