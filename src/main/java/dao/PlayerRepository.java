package dao;

import model.Player;
import org.hibernate.Session;


public class PlayerRepository extends BaseRepository<Integer, Player> {

    public PlayerRepository(Session session) {
        super(session, Player.class);
    }

}
