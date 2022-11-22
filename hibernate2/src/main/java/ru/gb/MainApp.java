package ru.gb;

import lombok.extern.log4j.Log4j2;
import ru.gb.model.Product;
import ru.gb.model.ProductDAOImpl;
import ru.gb.utils.SessionFactoryUtils;

import java.util.List;

@Log4j2
public class MainApp {


    public static void main(String[] args) {
        log.trace("MAIN START");
        SessionFactoryUtils sessionFactoryUtils = new SessionFactoryUtils();
        sessionFactoryUtils.init();
        try {
            ProductDAOImpl dao = new ProductDAOImpl(sessionFactoryUtils);
            String prefix = "WORK_TEST-----:   ";
            System.out.println(prefix + dao.getAll());
            dao.save(new Product("name", 213));
            List<Product> products = dao.getAll();
            System.out.println(prefix + products);
            dao.deleteById(products.get(0).getId());
            products = dao.getAll();
            System.out.println(prefix + products);
            Product product = products.get(0);
            System.out.println(prefix + dao.findById(products.get(0).getId()));
            product.setPrice(1000);
            product.setTitle("UPDATED");
            dao.save(product);
            System.out.println(prefix + dao.findById(products.get(0).getId()));




        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessionFactoryUtils.shutdown();
        }
    }
}
