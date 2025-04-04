import model.Match;
import model.Player;
import repository.MatchRepository;
import repository.PlayerRepository;

public class Main {
    public static void main(String[] args) {
        PlayerRepository playerRepository = new PlayerRepository();
        MatchRepository matchRepository = new MatchRepository();

        Player player = new Player("1230");
        Player player2 = new Player("10");
        playerRepository.save(player);
        Match match = new Match(player,player,player);
        matchRepository.save(match);

    }
}
