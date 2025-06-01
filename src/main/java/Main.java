
import dao.MatchRepository;
import dao.PlayerRepository;
import model.Match;
import model.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.PlayerService;
import util.HibernateUtil;

import java.util.Optional;


public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        var sessionFactory = HibernateUtil.getSessionFactory();

            try(var session = sessionFactory.openSession()) {

                session.beginTransaction();

                PlayerRepository playerRepository = new PlayerRepository(session);
                MatchRepository matchRepository = new MatchRepository(session);

                Player player1 = new Player();
                player1.setName("Gay1");
                Player p1 = playerRepository.save(player1);


                Player player2 = new Player();
                player2.setName("Sex1");
                playerRepository.save(player2);

                Match match = new Match();
                match.setPlayer1(player1);
                match.setPlayer2(player2);
                match.setWinner(player1);
                matchRepository.save(match);

                session.getTransaction().commit();
            }

        try (var session = sessionFactory.openSession()) {
            PlayerRepository playerRepository = new PlayerRepository(session);

            Optional<Player> player = playerRepository.findByName("Gay1");

            if (player.isPresent()) {
                System.out.println(player.get());
            }  else {
                System.out.println("1233");
            }

        }

        PlayerService playerService = new PlayerService();

        Player player = playerService.getOrCreatePlayer("Gay1");

        log.info("Player 1: --------------------------->" + player);


        log.info("Match Saved");

    }

}
