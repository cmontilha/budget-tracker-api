package com.cmontilha.budget.db;

import com.cmontilha.budget.core.User;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

public class UserDAO extends AbstractDAO<User> {
    public UserDAO(SessionFactory factory) {
        super(factory);
    }

    public User create(User user) {
        return persist(user);
    }

    public User findById(Long id) {
        return get(id);
    }

    public User findByEmailAndPassword(String email, String password) {
        return uniqueResult(currentSession().createQuery("FROM User u WHERE u.email = :email AND u.password = :password", User.class)
                .setParameter("email", email)
                .setParameter("password", password));
    }
}