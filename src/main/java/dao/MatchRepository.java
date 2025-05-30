package dao;

import model.Match;
import org.hibernate.Session;

public class MatchRepository extends BaseRepository<Integer, Match> {

    public MatchRepository(Session session) {
        super(session, Match.class);
    }
}
