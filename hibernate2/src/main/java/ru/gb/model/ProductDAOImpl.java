package ru.gb.model;

import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import ru.gb.utils.SessionFactoryUtils;

import java.util.List;

@Log4j2
public class ProductDAOImpl implements ProductDAO {
    private SessionFactoryUtils sessionFactoryUtils;

    public ProductDAOImpl(SessionFactoryUtils sessionFactoryUtils) {
        this.sessionFactoryUtils = sessionFactoryUtils;
    }

    @Override
    public Product findById(long id) {
        log.debug("SELECT PRODUCT BY ID");
        try {
            Session session = sessionFactoryUtils.getSession();
            Transaction tr = session.beginTransaction();
            Product product = null;
            if (tr.getStatus() == TransactionStatus.ACTIVE) {
                product = session.get(Product.class, id);
            } else {
                log.error("TRANSACTION ERROR " + tr.getStatus());
            }
            tr.commit();
            return product;
        } catch (Exception e) {
            log.error("FIND PRODUCT BY ID ERROR: " + e.getMessage());
//            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product findByTitle(String title) {
        log.debug("SELECT PRODUCT BY TITLE");
        try {
            Session session = sessionFactoryUtils.getSession();
            session.beginTransaction();
            List<Product> products = (List<Product>) session.createSelectionQuery("select p from Product p where p.title = :title")
                    .setParameter("title", title)
                    .getResultList();
            session.getTransaction().commit();
            return products.get(0);
        } catch (Exception e) {
            log.error("FIND PRODUCT BY TITLE ERROR: " + e.getMessage());
//            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> getAll() {
        try {
            Session session = sessionFactoryUtils.getSession();
            session.beginTransaction();
//            List<Product> products = session.createQuery("select p from Product p").getResultList();
            List<Product> products = (List<Product>) session.createSelectionQuery("select p from Product p").getResultList();

            session.getTransaction().commit();
            return products;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void save(Product product) {
        log.debug("SAVE NEW PRODUCT ");
        try {
            Session session = sessionFactoryUtils.getSession();
            session.beginTransaction();
            session.persist(product);
            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("SAVE PRODUCT BY ID ERROR: " + e.getMessage());
//            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    @Override
    public void saveOrUpdate(Product product) {
        log.debug("SAVE NEW PRODUCT OR UPDATE OLD PRODUCT");
        try {
            Session session = sessionFactoryUtils.getSession();
            session.beginTransaction();
            session.merge(product);
            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("SAVE OR UPDATE PRODUCT ERROR: " + e.getMessage());
//            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Product product) {
        log.trace("DELETE PRODUCT");
        try {
            Session session = sessionFactoryUtils.getSession();
            session.beginTransaction();
            session.remove(product);
            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("DELETE PRODUCT  ERROR: " + e.getMessage());
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteById(long id) {
        log.trace("DELETE PRODUCT BY ID");
        delete(findById(id));
    }

    @Override
    public void deleteByTitle(String title) {
        log.trace("DELETE PRODUCT BY TITLT");
        delete(findByTitle(title));
    }

    @Override
    public List<User> getAllBuyers(Product product) {
        log.trace("GET ALL BUYERS");
        List<User> buyers;
        try {
            Session session = sessionFactoryUtils.getSession();
            session.beginTransaction();
            session.persist(product);
            buyers = product.getUsers();
            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("DELETE PRODUCT  ERROR: " + e.getMessage());
//            throw new RuntimeException(e);
            buyers = null;
        }
        return buyers;
    }
}
