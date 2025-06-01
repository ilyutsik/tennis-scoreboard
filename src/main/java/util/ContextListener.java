package util;

import model.OngoingMatch;
import org.hibernate.SessionFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ContextListener implements ServletContextListener {

    SessionFactory sessionFactory;

    Map<UUID, OngoingMatch> ongoingMatches;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();

        sessionFactory = HibernateUtil.getSessionFactory();

        ongoingMatches = new HashMap<>();

        sc.setAttribute("sessionFactory", sessionFactory);
        sc.setAttribute("ongoingMatches", ongoingMatches);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

}
