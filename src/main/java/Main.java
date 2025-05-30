
import dao.MatchRepository;
import dao.PlayerRepository;
import model.Match;
import model.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.HibernateUtil;


public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

//        var sessionFactory = HibernateUtil.getSessionFactory();
//
//            var session = sessionFactory.getCurrentSession();
//                session.beginTransaction();
//
//                PlayerRepository playerRepository = new PlayerRepository(session);
//                MatchRepository matchRepository = new MatchRepository(session);
//
//                Player player1 = new Player();
//                player1.setName("Gay1");
//                Player p1 = playerRepository.save(player1);
//
//
//                Player player2 = new Player();
//                player2.setName("Sex1");
//                playerRepository.save(player2);
//
//                Match match = new Match();
//                match.setPlayer1(player1);
//                match.setPlayer2(player2);
//                match.setWinner(player1);
//                matchRepository.save(match);
//
//                session.getTransaction().commit();
//
//
//            var session1 = sessionFactory.getCurrentSession();
//                session.beginTransaction();
//
//                matchRepository = new MatchRepository(session1);
//
//                matchRepository.findAll().forEach(System.out::println);
//
//                session.getTransaction().commit();

        log.info("Match Saved");

    }

}
