package ru.gb;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.gb.model.Product;
import ru.gb.model.ProductDAO;
import ru.gb.model.ProductDAOImpl;
import ru.gb.utils.SessionFactoryUtils;

import static org.junit.jupiter.api.Assertions.*;

public class HibernateTests {

    private static SessionFactoryUtils util;
    private static ProductDAO dao;

    @BeforeAll
    static void beforeALl() {
        util = new SessionFactoryUtils();
        util.init();
        dao = new ProductDAOImpl(util);
    }

    @Test
    void createDeleteProduct() {
        Product testProduct = new Product("TESTING_PRODUCT", 9999);
        System.out.println(testProduct);
        dao.save(testProduct);
        assertEquals(testProduct.getTitle(), dao.findByTitle(testProduct.getTitle()).getTitle());
        dao.deleteByTitle(testProduct.getTitle());
        Throwable thrown = assertThrows(Exception.class, () -> {
            dao.findByTitle(testProduct.getTitle());
        });
        assertNotNull(thrown.getMessage());
    }



    @Test
    void updateProduct() {
//        dao.save(testProduct);
//        Product product = dao.findByTitle(testProduct.getTitle());
//        product.setTitle("FOR_EXAMPLE");
//        dao.save(product);
//        assertEquals(product.getTitle(),dao.findById(product.getId()).getTitle());
//        dao.deleteByTitle(product.getTitle());
//        Throwable thrown = assertThrows(Exception.class, () -> {
//            dao.findByTitle(product.getTitle());
//        });
//        assertNotNull(thrown.getMessage());
        Product testProduct = new Product("TESTING_PRODUCT", 9999);
        dao.save(testProduct);
        System.out.println(dao.getAll());
        testProduct.setTitle("EXAMPLE");
        dao.saveOrUpdate(testProduct);
        System.out.println(dao.getAll());
        assertEquals("EXAMPLE",dao.findById(testProduct.getId()).getTitle());
        dao.deleteByTitle(testProduct.getTitle());
        Throwable thrown = assertThrows(Exception.class, () -> dao.findByTitle(testProduct.getTitle()));
        assertNotNull(thrown.getMessage());
    }


}
