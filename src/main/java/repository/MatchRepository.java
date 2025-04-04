package repository;

import model.Match;
import model.Player;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;


public class MatchRepository {

    public Match save(Match match) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.save(match);

        tx.commit();
        session.close();
        return match;
    }

}
