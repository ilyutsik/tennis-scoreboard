package service;

import game.Score;
import game.enums.Point;
import model.Match;
import game.OngoingMatch;
import model.Player;

import java.util.Objects;

public class MatchScoreCalculationService {

    private static final Integer setsToWin = 2;
    private static final Integer gamesToWinSet = 6;
    private static final Integer minDifferenceBetweenGamesToWin = 2;
    private static final Integer extraPointsToWin = 7;
    private static final Integer minDifferenceBetweenExtraPointsToWin = 2;

    OngoingMatch ongoingMatch;
    Integer player1Id;
    Integer player2Id;
    Score player1;
    Score player2;


    public MatchScoreCalculationService(OngoingMatch ongoingMatch) {
        this.player1Id = ongoingMatch.getPlayer1().getId();
        this.player2Id = ongoingMatch.getPlayer2().getId();
        this.player1 = ongoingMatch.getPlayer1Score();
        this.player2 = ongoingMatch.getPlayer2Score();
        this.ongoingMatch = ongoingMatch;
    }

    public void pointWonBy(Integer playerId) {
        if (isGameOver()) {
            return;
        }
        boolean isPlayer1 = isFirstPlayer(playerId);
        addPoint(isPlayer1);
    }

    public boolean isGameOver() {
        return getWinnerId() != null;
    }

    public Match getFinishedMatch() {
        Integer winnerId = getWinnerId();
        Player winner = Objects.equals(winnerId, player1Id) ? ongoingMatch.getPlayer1() : ongoingMatch.getPlayer2();
        return new Match(ongoingMatch.getPlayer1(), ongoingMatch.getPlayer2(), winner);
    }

    private Integer getWinnerId() {
        if (player1.getSets().equals(setsToWin)) {
            return player1Id;
        }
        if (player2.getSets().equals(setsToWin)) {
            return player2Id;
        }
        return null;
    }

    private void addPoint(boolean isPlayer1) {
        if (isTieBreak()) {
            addExtraPoint(isPlayer1);
            return;
        }
        if (isPlayersPointsAreEqual()) {
            if (isPlayer1) {
                player1.setPoints(Point.more);
                player2.setPoints(Point.less);
            } else {
                player2.setPoints(Point.more);
                player1.setPoints(Point.less);
            }
        } else if (isOnePlayerHavePointMore()) {
            if (isFirstPlayerHaveMore(isPlayer1)) {
                resetPoints();
                player1.addGame();
            } else if (isSecondPlayerHaveMore(isPlayer1)) {
                resetPoints();
                player2.addGame();
            } else {
                player1.setPoints(Point.equal);
                player2.setPoints(Point.equal);
            }
        } else if (isPlayer1HaveFortyAndWinPointAndPlayer2HasLessThanFortyPoints(isPlayer1)) {
            resetPoints();
            player1.addGame();
        } else if (isPlayer2HaveFortyAndWinPointAndPlayer1HasLessThanFortyPoints(isPlayer1)) {
            resetPoints();
            player2.addGame();
        } else {
            if (isPlayer1) {
                player1.addPoint();
            } else {
                player2.addPoint();
            }
            if (isBothPlayersHasForty()) {
                player1.setPoints(Point.equal);
                player2.setPoints(Point.equal);
            }
        }
        updateGamesScore();
    }

    private boolean isBothPlayersHasForty() {
        return player1.getPoints() == Point.forty && player2.getPoints() == Point.forty;
    }

    private boolean isPlayer2HaveFortyAndWinPointAndPlayer1HasLessThanFortyPoints(boolean isPlayer1) {
        return player2.getPoints() == Point.forty && player1.getPoints().ordinal() < Point.forty.ordinal() && !isPlayer1;
    }

    private boolean isPlayer1HaveFortyAndWinPointAndPlayer2HasLessThanFortyPoints(boolean isPlayer1) {
        return player1.getPoints() == Point.forty && player2.getPoints().ordinal() < Point.forty.ordinal() && isPlayer1;
    }

    private boolean isSecondPlayerHaveMore(boolean isPlayer1) {
        return !isPlayer1 && player2.getPoints() == Point.more;
    }

    private boolean isFirstPlayerHaveMore(boolean isPlayer1) {
        return isPlayer1 && player1.getPoints() == Point.more;
    }

    private boolean isOnePlayerHavePointMore() {
        return player1.getPoints() == Point.more || player2.getPoints() == Point.more;
    }

    private boolean isPlayersPointsAreEqual() {
        return player1.getPoints() == Point.equal && player2.getPoints() == Point.equal;
    }

    private void addExtraPoint(boolean isPlayer1) {
        if (isPlayer1) {
            player1.addExtraPoint();
        } else {
            player2.addExtraPoint();
        }

        if (player1.getExtraPoints() >= extraPointsToWin && player1.getExtraPoints() - minDifferenceBetweenExtraPointsToWin >= player2.getExtraPoints()) {
            player1.addSet();
            resetGameAndExtraPoints();
        }
        if (player2.getExtraPoints() >= extraPointsToWin && player2.getExtraPoints() - minDifferenceBetweenExtraPointsToWin >= player1.getExtraPoints()) {
            player2.addSet();
            resetGameAndExtraPoints();
        }
    }

    private void resetGameAndExtraPoints() {
        player1.setGames(0);
        player2.setGames(0);
        player1.setExtraPoints(0);
        player2.setExtraPoints(0);
    }

    private boolean isTieBreak() {
        return ((player1.getGames() >= gamesToWinSet) && (player1.getGames().equals(player2.getGames())));
    }

    private void updateGamesScore() {
        if (player1.getGames() >= gamesToWinSet && (player1.getGames() - minDifferenceBetweenGamesToWin >= player2.getGames())) {
            player1.addSet();
            player1.setGames(0);
            player2.setGames(0);
        }
        if (player2.getGames() >= gamesToWinSet && player2.getGames() - minDifferenceBetweenGamesToWin >= player1.getGames()) {
            player2.addSet();
            player1.setGames(0);
            player2.setGames(0);
        }
    }

    private void resetPoints() {
        ongoingMatch.getPlayer1Score().setPoints(Point.zero);
        ongoingMatch.getPlayer2Score().setPoints(Point.zero);
    }

    private boolean isFirstPlayer(Integer playerId) {
        return ongoingMatch.getPlayer1().getId().equals(playerId);
    }

}
