package service;

import dao.MatchRepository;
import exception.InternalServerError;
import game.MatchPage;
import model.Match;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class FinishedMatchesPersistenceService {

    private final SessionFactory sessionFactory;

    private final Integer pageSize = 5;

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

    public MatchPage getMatchPage(String name , Integer page) throws InternalServerError {
        if (page == null || page < 1) {
            page = 1;
        }
        if (name != null && !name.trim().isEmpty()) {
            return getMatchesPageByName(name, page);
        } else {
            return getMatchesPage(page);
        }
    }

    public MatchPage getMatchesPage(int page) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            MatchRepository matchRepository = new MatchRepository(session);
            List<Match> matchList = matchRepository.getMatchesPage(page, pageSize);
            Long matchesCount = matchRepository.getMatchesCount();

            tx.commit();

            Integer pageCount = (int) Math.ceil((double) matchesCount / pageSize);
            return new MatchPage(matchList, page, pageCount);
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new InternalServerError("Error find matches");
        }
    }

    public MatchPage getMatchesPageByName(String name, int page) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            MatchRepository matchRepository = new MatchRepository(session);

            List<Match> matchList = matchRepository.getMatchesPageByName(name, page, pageSize);
            Long matchesCount = matchRepository.getMatchesByNameCount(name);

            tx.commit();

            Integer pageCount = (int) Math.ceil((double) matchesCount / pageSize);
            return new MatchPage(matchList, page, pageCount);
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new InternalServerError("Error find matches");
        }
    }

}
