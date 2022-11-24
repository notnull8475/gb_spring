package ru.gb.utils;

import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import ru.gb.model.Product;

@Log4j2
public class SessionFactoryUtils {
    private SessionFactory factory;

    public void init() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            log.trace("Hibernate configuration loaded");

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            log.trace("Hibernate serviceRegistry created");


            factory = configuration.addAnnotatedClass(Product.class).buildSessionFactory(serviceRegistry);
//        factory = new Configuration()
//                .configure("hibernate.cfg.xml")
////                .addAnnotatedClass(Product.class)
//                .buildSessionFactory();
            log.debug(factory.isOpen() ? "SESSION_FACTORY IS OPEN" : "SESSION_FACTORY NOT OPEN");
        } catch (Throwable e) {
            log.error(e.getMessage());
            throw new ExceptionInInitializerError(e);
        }
    }

    public Session getSession() {
        log.trace("SESSION REQUEST FROM SESSION_FACTORY ");
        return factory.getCurrentSession();
    }

    public void shutdown() {
        log.trace("SESSION_FACTORY CLOSE");
        if (factory != null) {
            factory.close();
        }
    }
}
