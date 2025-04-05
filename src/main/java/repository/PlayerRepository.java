package repository;

import model.Player;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;
import java.util.NoSuchElementException;


public class PlayerRepository {

    public Player save(Player player) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(player);
            tx.commit();
            return player;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public Player findByName(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Player WHERE name = :name";
            Query<Player> query = session.createQuery(hql, Player.class);
            query.setParameter("name", name);
            Player player = query.uniqueResult();
            if (player == null) {
                throw new NoSuchElementException("Player not found");
            }
            return player;
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Player not found");
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error occurred", e);
        }
    }


    // not work
    public Player delete(Player player) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        tx.commit();
        session.close();
        return player;
    }

}
