package model;


import game.GameScore;

public class OngoingMatch {

    private final Player player1;

    private final Player player2;

    private final GameScore gameScore;

    private boolean isMatchOver;

    public OngoingMatch(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.gameScore = new GameScore(this);
        isMatchOver = false;
    }

    public GameScore getGameScore() {
        return gameScore;
    }

    public boolean isMatchOver() {
        return isMatchOver;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setMatchOver(boolean matchOver) {
        isMatchOver = matchOver;
    }
}
