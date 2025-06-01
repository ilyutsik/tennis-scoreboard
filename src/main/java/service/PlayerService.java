package service;

import dao.PlayerRepository;
import exception.InternalServerError;
import model.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.HibernateUtil;
import java.util.Optional;

public class PlayerService {

    private static final Logger log = LoggerFactory.getLogger(PlayerService.class);

    private final SessionFactory sessionFactory;

    public PlayerService() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public Player getOrCreatePlayer(String playerName) throws InternalServerError {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            PlayerRepository playerRepository = new PlayerRepository(session);
            Optional<Player> playerOptional = playerRepository.findByName(playerName);
            Player player = playerOptional.orElseGet(() -> playerRepository.save(new Player(playerName)));

            tx.commit();

            return player;

        } catch (Exception e) {
            if (tx != null) {
                log.warn("roll back transaction", e);
                tx.rollback();
            }
        }
        log.warn("Failed to get or create player");
        throw new InternalServerError("Failed to get or create player");
    }


}
