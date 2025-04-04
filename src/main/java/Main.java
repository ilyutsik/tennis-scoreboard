import entity.Player;
import repository.PlayerRepository;

public class Main {
    public static void main(String[] args) {
        Player player = new Player("1234");
        PlayerRepository playerRepository = new PlayerRepository();
        System.out.println(playerRepository.save(player));
    }
}
