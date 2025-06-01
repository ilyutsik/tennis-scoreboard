package game;

import game.enums.Point;
import model.OngoingMatch;

public class GameScore {

    private static final Integer setsToWin = 2;
    private static final Integer gamesToWinSet = 6;
    private static final Integer minDifferenceBetweenGamesToWin = 2;
    private static final Integer extraPointsToWin = 6;
    private static final Integer minDifferenceBetweenExtraPointsToWin = 2;
    Integer player1Id;
    Integer player2Id;
    Score player1;
    Score player2;


    public GameScore(OngoingMatch match) {
        this.player1Id = match.getPlayer1().getId();
        this.player2Id = match.getPlayer2().getId();
        this.player1 = new Score();
        this.player2 = new Score();
    }


    public Integer winnerId() {
        if (player1.getSets().equals(setsToWin)) {
            return player1Id;
        }
        if (player2.getSets().equals(setsToWin)) {
            return player2Id;
        }
        return null;
    }

    public void pointWonBy(Integer playerId) {
        if (winnerId() != null) {
            return;
        }
        boolean isPlayer1 = isFirstPlayer(playerId);
        addPoint(isPlayer1);

    }


    private boolean isTieBreak() {
        return ((player1.getGames() >= gamesToWinSet) && (player1.getGames().equals(player2.getGames())));
    }

    private void addPoint(boolean isPlayer1) {
        if (isTieBreak()) {
            if (isPlayer1) {
                player1.addExtraPoint();
            } else {
                player2.addExtraPoint();
            }

            if (player1.getExtraPoints() > extraPointsToWin && player1.getExtraPoints() - minDifferenceBetweenExtraPointsToWin >= player2.getExtraPoints()) {
                player1.addSet();
                player1.setGames(0);
                player2.setGames(0);
                player1.setExtraPoints(0);
                player2.setExtraPoints(0);
            }
            if (player2.getExtraPoints() > extraPointsToWin && player2.getExtraPoints() - minDifferenceBetweenExtraPointsToWin >= player1.getExtraPoints()) {
                player2.addSet();
                player1.setGames(0);
                player2.setGames(0);
                player1.setExtraPoints(0);
                player2.setExtraPoints(0);
            }
            return;
        }

        if (player1.getPoints() == Point.equal && player2.getPoints() == Point.equal) {
            if (isPlayer1) {
                player1.setPoints(Point.more);
            } else {
                player2.setPoints(Point.more);
            }
        } else if (player1.getPoints() == Point.more || player2.getPoints() == Point.more) {
            if (isPlayer1 && player1.getPoints() == Point.more || !isPlayer1 && player2.getPoints() == Point.more) {
                if (isPlayer1) {
                    player1.setPoints(Point.game);
                    resetPoints();
                    player1.addGame();
                } else {
                    player2.setPoints(Point.game);
                    resetPoints();
                    player2.addGame();
                }
            } else {
                player1.setPoints(Point.equal);
                player2.setPoints(Point.equal);
            }
        } else if (player1.getPoints() == Point.forty && player2.getPoints().ordinal() < Point.forty.ordinal() && isPlayer1) {
            player1.setPoints(Point.game);
            resetPoints();
            player1.addGame();
        } else if (player2.getPoints() == Point.forty && player1.getPoints().ordinal() < Point.forty.ordinal() && !isPlayer1) {
            player2.setPoints(Point.game);
            resetPoints();
            player2.addGame();
        } else {
            if (isPlayer1) {
                player1.addPoint();
            } else {
                player2.addPoint();
            }
            if (player1.getPoints() == Point.forty && player2.getPoints() == Point.forty) {
                player1.setPoints(Point.equal);
                player2.setPoints(Point.equal);
            }
        }

        addGame();

    }

    private void addGame() {
        if (player1.getGames().equals(gamesToWinSet) && (player1.getGames() - minDifferenceBetweenGamesToWin >= player2.getGames())) {
            player1.addSet();
            player1.setGames(0);
            player2.setGames(0);
        }
        if (player2.getGames().equals(gamesToWinSet) && player2.getGames() - minDifferenceBetweenGamesToWin >= player1.getGames()) {
            player2.addSet();
            player1.setGames(0);
            player2.setGames(0);
        }
    }

    private void resetPoints() {
        player1.setPoints(Point.zero);
        player2.setPoints(Point.zero);
    }


    private boolean isFirstPlayer(Integer playerId) {
        return playerId.equals(player1Id);
    }

    public Score getPlayer1() {
        return player1;
    }

    public Score getPlayer2() {
        return player2;
    }
}
