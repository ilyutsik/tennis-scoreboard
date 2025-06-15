package util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null || sessionFactory.isClosed()) {
            try {
                String user = System.getenv("DB_USER");
                String pass = System.getenv("DB_PASS");
                Configuration configuration = new Configuration().configure();
                configuration.setProperty("hibernate.connection.username", user);
                configuration.setProperty("hibernate.connection.password", pass);
                configuration.addAnnotatedClass(model.Player.class);
                configuration.addAnnotatedClass(model.Match.class);
                sessionFactory = configuration.buildSessionFactory();
            } catch (Throwable ex) {
                System.err.println("Ошибка при создании SessionFactory: " + ex);
                throw new ExceptionInInitializerError(ex);
            }
        }
        return sessionFactory;
    }

    public static void shutdown() {
        sessionFactory.close();
    }
}
