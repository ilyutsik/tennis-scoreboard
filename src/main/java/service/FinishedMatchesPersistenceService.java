package service;

import dao.MatchRepository;
import exception.InternalServerError;
import model.Match;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class FinishedMatchesPersistenceService {

    private final SessionFactory sessionFactory;

    public FinishedMatchesPersistenceService() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public Match saveMatch(Match match) throws InternalServerError {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            MatchRepository matchRepository = new MatchRepository(session);
            matchRepository.save(match);

            tx.commit();
            return match;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new InternalServerError("Error creating match");
        }
    }

}
