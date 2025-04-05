import model.Match;
import model.Player;
import repository.MatchRepository;
import repository.PlayerRepository;

public class Main {
    public static void main(String[] args) {
        PlayerRepository playerRepository = new PlayerRepository();
        MatchRepository matchRepository = new MatchRepository();

        Player player = new Player("12230");
        Player player2 = new Player("130");
        playerRepository.save(player);
        Match match = new Match(player,player,player);
        matchRepository.save(match);

    }
}
