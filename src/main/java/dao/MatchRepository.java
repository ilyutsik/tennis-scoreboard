package dao;

import model.Match;
import org.hibernate.Session;

import java.util.List;

public class MatchRepository extends BaseRepository<Integer, Match> {

    public MatchRepository(Session session) {
        super(session, Match.class);
    }

    public List<Match> getMatchesPage(int page, int size) {
            return session.createQuery("FROM Match ORDER BY id", Match.class)
                    .setFirstResult((page - 1) * size)
                    .setMaxResults(size)
                    .list();
    }

    public Long getMatchesCount() {
        return session.createQuery("SELECT COUNT(m) FROM Match m", Long.class)
                .uniqueResult();
    }

    public List<Match> getMatchesPageByName(String name, int page, int size) {
            return session.createQuery("FROM Match m WHERE m.player1.name LIKE :name OR m.player2.name LIKE :name", Match.class)
                    .setParameter("name", name)
                    .setFirstResult((page - 1) * size)
                    .setMaxResults(size)
                    .list();
    }

    public Long getMatchesByNameCount(String name) {
        return session.createQuery("SELECT COUNT(m) FROM Match m WHERE m.player1.name LIKE :name OR m.player2.name LIKE :name", Long.class)
                .setParameter("name", name)
                .uniqueResult();
    }

}
