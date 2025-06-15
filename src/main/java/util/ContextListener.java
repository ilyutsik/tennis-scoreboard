package util;

import game.OngoingMatch;
import org.hibernate.SessionFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ContextListener implements ServletContextListener {

    SessionFactory sessionFactory;


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();

        sessionFactory = HibernateUtil.getSessionFactory();

        sc.setAttribute("sessionFactory", sessionFactory);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

}
