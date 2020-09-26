package org.example.orders.repository;


import org.example.orders.model.Request;
import org.example.orders.model.Status;
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
    public Request save(Request request, int userId) {
        request.setUser(em.getReference(User.class, userId));
        if (request.isNew()) {
            em.persist(request);
            return request;
        } else if (get(request.id(), userId) == null) {
            return null;
        }
        return em.merge(request);
    }

    @Override
    public boolean delete(int id, int userId) {
        throw new RuntimeException("Delete not implement");
    }

    @Override
    public Request get(int id, int userId) {
        Request request = em.find(Request.class, id);
        return request != null && request.getUser().getId() == userId ? request : null;
    }

    @Override
    public List<Request> getAll() {
        return em.createNamedQuery(Request.ALL_SORTED, Request.class)
                .getResultList();
    }

    @Override
    public boolean changeStatus(int id, Status status) {
//        return crudOrderRepository.changeStatus(id, status);
        return false;
    }

    @Override
    public List<Request> getAllByUser(int userId) {
       return em.createNamedQuery(Request.ALL_SORTED, Request.class)
//                .setParameter("userId", userId)
                .getResultList();
    }
}
