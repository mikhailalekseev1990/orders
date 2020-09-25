package org.example.orders.repository;

import org.example.orders.model.Order;
import org.example.orders.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
@Transactional(readOnly = true)
public class JpaOrderRepository implements OrderRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Order save(Order order, int userId) {
        order.setUser(entityManager.getReference(User.class, userId));
        if (order.isNew()) {
            entityManager.persist(order);
            return order;
        } else if (get(order.id(), userId) == null) {
            return null;
        }
        return entityManager.merge(order);
    }

    @Override
    public boolean delete(int id, int userId) {
        return entityManager.createNamedQuery(Order.DELETE)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .executeUpdate() != 0;
    }

    @Override
    public Order get(int id, int userId) {
        Order order = entityManager.find(Order.class, id);
        return order != null && order.getUser().getId() == userId ? order : null;
    }

    @Override
    public List<Order> getAll() {
        return entityManager.createNamedQuery(Order.ALL_SORTED, Order.class)
                .getResultList();
    }

    @Override
    public List<Order> getAllByUser(int userId) {
        return entityManager.createNamedQuery(Order.ALL_SORTED_BY_USER, Order.class)
                .setParameter("userId", userId)
                .getResultList();
    }
}
