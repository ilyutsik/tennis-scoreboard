package dao;

import model.Player;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;


public class PlayerRepository extends BaseRepository<Integer, Player> {

    public PlayerRepository(Session session) {
        super(session, Player.class);
    }

    public Optional<Player> findByName(String name) {
        List<Player> player = getSession().createQuery("from Player where name = :name", Player.class).setParameter("name", name)
                .getResultList();
        return player.isEmpty() ? Optional.empty() : Optional.of(player.getFirst());
    }

}
