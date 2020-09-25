package org.example.orders.repository;

import org.example.orders.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class JpaUserRepository implements UserRepository{

    @Autowired
    EntityManager entityManager;

    @Override
    public List<User> getAll() {
        return entityManager.createNamedQuery(User.ALL_SORTED, User.class).getResultList();
    }

    @Override
    public User get(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public User setRole(int id, String role) {
        return null;
    }

    @Override
    public User deleteRole(int id, String role) {
        return null;
    }
}
