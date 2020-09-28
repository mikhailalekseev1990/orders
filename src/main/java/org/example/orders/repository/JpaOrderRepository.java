package org.example.orders.repository;


import org.example.orders.model.Order;
import org.example.orders.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


@Repository
@Transactional(readOnly = true)
public class JpaOrderRepository implements OrderRepository {

    @Autowired
    private EntityManager em;

    @Override
    @Transactional
    public Order save(Order order, int userId) {
        order.setUser(em.getReference(User.class, userId));
        if (order.isNew()) {
            em.persist(order);
            return order;
        } else if (getByUser(order.id(), userId) == null) {
            return null;
        }
        return em.merge(order);
    }

    @Override
    public boolean delete(int id, int userId) {
        throw new RuntimeException("Delete not implement");
    }

    @Override
    public Order getByOperator(int id) {
        return em.find(Order.class, id);
    }

    @Override
    public Order getByUser(int id, int userId) {
        Order order = em.find(Order.class, id);
        return order != null && order.getUser().getId() == userId ? order : null;
    }


    @Override
    public List<Order> getAllByUser(int userId) {
        return em.createNamedQuery(Order.ALL_SORTED_BY_USER, Order.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public List<Order> getAllByOperator() {
        return em.createNamedQuery(Order.ALL_SORTED_BY_OPERATOR, Order.class)
                .getResultList();
    }

    @Override
    @Transactional
    public boolean changeStatus(int id, String status) {
        return em.createNamedQuery(Order.CHANGE_STATUS)
                .setParameter("status", status)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }


}
