package ru.gb.model;

import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import ru.gb.utils.SessionFactoryUtils;

import java.sql.Timestamp;
import java.util.List;

@Log4j2
public class OrderDAOImpl implements OrderDAO {
    private SessionFactoryUtils sessionFactoryUtils;

    public OrderDAOImpl(SessionFactoryUtils sessionFactoryUtils) {
        this.sessionFactoryUtils = sessionFactoryUtils;
    }

    @Override
    public boolean newOrder(User user, List<Product> productList) {
        log.trace("NEW ORDER " + user.getName() + " " + productList);
        try {
            Session session = sessionFactoryUtils.getSession();
            for (Product p : productList) {
                Order o = new Order();
                session.beginTransaction();
                session.persist(o);
                o.setUser(user);
                o.setProduct(p);
                o.setProductPrice(p.getPrice());
                o.setDatetime(new Timestamp(System.currentTimeMillis()));
                session.detach(o);
            }
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            log.error("DELETE PRODUCT  ERROR: " + e.getMessage());
//            throw new RuntimeException(e);
            return false;
        }
    }

    @Override
    public List<Order> getUserOders(User user) {
        log.trace("GET ALL ORDERS OF USER");
        List<Order> orders;
        try {
            Session session = sessionFactoryUtils.getSession();
            session.beginTransaction();
            session.merge(user);
            orders = (List<Order>) session.createSelectionQuery("select o from Order o where o.user_id = :user").setParameter("user", user.getId()).getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("DELETE PRODUCT  ERROR: " + e.getMessage());
//            throw new RuntimeException(e);
            orders = null;
        }
        return orders;
    }
}
