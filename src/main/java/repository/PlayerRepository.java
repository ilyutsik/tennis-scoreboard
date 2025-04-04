package repository;

import model.Player;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class PlayerRepository {

    public Player save(Player player) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.save(player);

        tx.commit();
        session.close();
        return player;
    }

    // not work
    public Player delete(Player player) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.delete(player);

        tx.commit();
        session.close();
        return player;
    }

}
