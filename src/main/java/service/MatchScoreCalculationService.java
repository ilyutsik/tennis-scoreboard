package service;

import exception.InternalServerError;
import model.Match;
import model.OngoingMatch;
import model.Player;

public class MatchScoreCalculationService {

    public void addPoint(Integer playerId, OngoingMatch ongoingMatch) {
        ongoingMatch.getGameScore().pointWonBy(playerId);
    }

    public boolean isGameOver(OngoingMatch ongoingMatch) throws InternalServerError {
        if (ongoingMatch.isMatchOver()) {
            return true;
        }
        if (ongoingMatch.getGameScore().winnerId() != null) {

            ongoingMatch.setMatchOver(true);
            return true;
        }

        return false;
    }

    public Match createFinishedMatch(OngoingMatch ongoingMatch) {
        Player winner;
        Integer winnerId = ongoingMatch.getGameScore().winnerId();
        if (winnerId.equals(ongoingMatch.getPlayer1().getId())) {
            winner = ongoingMatch.getPlayer1();
        } else {
            winner = ongoingMatch.getPlayer2();
        }
        return new Match(ongoingMatch.getPlayer1(), ongoingMatch.getPlayer2(), winner);
    }
}
