package org.example.orders.repository;

import org.example.orders.model.Role;
import org.example.orders.model.User;
import org.hibernate.jpa.QueryHints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class JpaUserRepository implements UserRepository {

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
    @Transactional
    public boolean setRole(int id) {
      return   entityManager.createNativeQuery("INSERT INTO user_roles (user_id, role) VALUES (?,?)")
                .setParameter(1, id)
                .setParameter(2, Role.OPERATOR.toString())
                .executeUpdate() != 0;

    }

    @Override
    public User getByName(String name) {
        List<User> users = entityManager.createNamedQuery(User.BY_NAME, User.class)
                .setParameter(1, name)
                .setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false)
                .getResultList();
        return DataAccessUtils.singleResult(users);
    }

}
