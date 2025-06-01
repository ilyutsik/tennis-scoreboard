package game;


import model.Player;

public class OngoingMatch {

    private final Player player1;

    private final Player player2;

    private final Score player1Score;

    private final Score player2Score;

    public OngoingMatch(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.player1Score = new Score();
        this.player2Score = new Score();
    }

    public Score getPlayer1Score() {
        return player1Score;
    }

    public Score getPlayer2Score() {
        return player2Score;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

}
