package ru.gb.model;

import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import ru.gb.utils.SessionFactoryUtils;

import java.util.List;

@Log4j2
public class UserDAOImpl implements UserDAO {
    private SessionFactoryUtils sessionFactoryUtils;

    public UserDAOImpl(SessionFactoryUtils sessionFactoryUtils) {
        this.sessionFactoryUtils = sessionFactoryUtils;
    }

    @Override
    public boolean addUser(User user) {
        log.debug("ADD USER");
        try {
            Session session = sessionFactoryUtils.getSession();
            session.beginTransaction();
            session.persist(user);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            log.error("ADD USER ERROR: " + e.getMessage());
//            throw new RuntimeException(e);
            return false;
        }
    }

    @Override
    public boolean deleteUser(User user) {
        log.trace("DELETE USER");
        try {
            Session session = sessionFactoryUtils.getSession();
            session.beginTransaction();
            session.remove(user);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            log.error("DELETE USER ERROR: " + e.getMessage());
//            throw new RuntimeException(e);
            return false;
        }
    }

    @Override
    public List<Product> getProducts(User user) {
        log.trace("GET ALL PRODUCTS");
        List<Product> userProducts;
        try {
            Session session = sessionFactoryUtils.getSession();
            session.beginTransaction();
            session.persist(user);
            userProducts = user.getProducts();
            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("DELETE PRODUCT  ERROR: " + e.getMessage());
//            throw new RuntimeException(e);
            userProducts = null;
        }
        return userProducts;
    }

}
