package service;

import game.OngoingMatch;
import game.enums.Point;
import model.Player;
import org.junit.jupiter.api.*;

class MatchScoreCalculationServiceTest {

    private static final Integer setsToWin = 2;
    private static final Integer gamesToWinSet = 6;
    private static final Integer minDifferenceBetweenGamesToWin = 2;
    private static final Integer extraPointsToWin = 7;
    private static final Integer minDifferenceBetweenExtraPointsToWin = 2;

    MatchScoreCalculationService matchScoreCalculationService;
    Player p1;
    Player p2;
    OngoingMatch ongoingMatch;
    Integer sets1pBefore;
    Integer sets2pBefore;
    Integer p1GamesBefore;
    Integer p2GamesBefore;


    @BeforeEach
    void init() {
        p1 = new Player("P1");
        p2 = new Player("P2");
        p1.setId(1);
        p2.setId(2);
        ongoingMatch = new OngoingMatch(p1, p2);
        matchScoreCalculationService = new MatchScoreCalculationService(ongoingMatch);

        sets1pBefore = ongoingMatch.getPlayer1Score().getSets();
        sets2pBefore = ongoingMatch.getPlayer2Score().getSets();
        p1GamesBefore = ongoingMatch.getPlayer1Score().getGames();
        p2GamesBefore = ongoingMatch.getPlayer2Score().getGames();


    }

    @Nested
    class TieBreakTests {

        @Test
        void tieBreakStart() {
            tieBreak();

            Integer sets1pAfter = ongoingMatch.getPlayer1Score().getSets();
            Integer sets2pAfter = ongoingMatch.getPlayer2Score().getSets();

            Assertions.assertEquals(sets1pBefore, sets1pAfter);
            Assertions.assertEquals(sets2pBefore, sets2pAfter);

        }

        @Test
        void winInTieBreak() {
            tieBreak();
            winPoint(p1, extraPointsToWin);

            Integer sets1pAfter = ongoingMatch.getPlayer1Score().getSets();
            Integer sets2pAfter = ongoingMatch.getPlayer2Score().getSets();

            Assertions.assertEquals(sets1pBefore + 1, sets1pAfter);
            Assertions.assertEquals(sets2pBefore, sets2pAfter);

        }

        @Test
        void winTieBreakWithMinDifferenceBetweenExtraPoints() {
            tieBreak();

            winPoint(p1, extraPointsToWin-1);
            winPoint(p1, extraPointsToWin-1);
            winPoint(p1, minDifferenceBetweenExtraPointsToWin);

            Integer sets1pAfter = ongoingMatch.getPlayer1Score().getSets();
            Integer sets2pAfter = ongoingMatch.getPlayer2Score().getSets();

            Assertions.assertEquals(sets1pBefore + 1, sets1pAfter);
            Assertions.assertEquals(sets2pBefore, sets2pAfter);
        }

        @Test
        void notWinTieBreakWithSmallDifferenceBetweenExtraPoints() {
            tieBreak();

            winPoint(p1, extraPointsToWin-1);
            winPoint(p1, extraPointsToWin-1);
            winPoint(p1, minDifferenceBetweenExtraPointsToWin-1);

            Integer sets1pAfter = ongoingMatch.getPlayer1Score().getSets();
            Integer sets2pAfter = ongoingMatch.getPlayer2Score().getSets();

            Assertions.assertEquals(sets1pBefore + 1, sets1pAfter);
            Assertions.assertEquals(sets2pBefore, sets2pAfter);
        }
    }

    @Nested
    class GameExtraPointsTest {

        @Test
        void winSetWithMinDifferenceBetweenGames() {
            winGame(p1, gamesToWinSet-1);
            winGame(p2, gamesToWinSet-1);

            winGame(p1, minDifferenceBetweenGamesToWin);

            Integer sets1pAfter = ongoingMatch.getPlayer1Score().getSets();
            Integer sets2pAfter = ongoingMatch.getPlayer2Score().getSets();

            Assertions.assertEquals(sets1pBefore + 1, sets1pAfter);
            Assertions.assertEquals(sets2pBefore, sets2pAfter);
        }

        @Test
        void notWinSetWithSmallDifferenceBetweenGames() {
            winGame(p1, gamesToWinSet-1);
            winGame(p2, gamesToWinSet-1);

            winGame(p1, minDifferenceBetweenGamesToWin-1);

            Integer sets1pAfter = ongoingMatch.getPlayer1Score().getSets();
            Integer sets2pAfter = ongoingMatch.getPlayer2Score().getSets();

            Assertions.assertEquals(sets1pBefore, sets1pAfter);
            Assertions.assertEquals(sets2pBefore, sets2pAfter);

        }

        @Test
        void winWithSetsToWin() {
            winSet(p1, gamesToWinSet);

            Integer sets1pAfter = ongoingMatch.getPlayer1Score().getSets();
            Integer sets2pAfter = ongoingMatch.getPlayer2Score().getSets();

            Assertions.assertEquals(setsToWin, sets1pAfter);
            Assertions.assertEquals(sets2pBefore, sets2pAfter);
            Assertions.assertTrue(matchScoreCalculationService.isGameOver());
        }
    }

    @Nested
    class PointsTest {

        @Test
        void winPointWithScoreFortyZero() {
            winPoint(p1, 4);

            Integer player1GamesAfter = ongoingMatch.getPlayer1Score().getGames();
            Integer player2GamesAfter = ongoingMatch.getPlayer2Score().getGames();

            Assertions.assertEquals(p1GamesBefore + 1, player1GamesAfter);
            Assertions.assertEquals(p2GamesBefore, player2GamesAfter);
        }

        @Test
        void winPointWithEqual() {
            winPoint(p1, 3);
            winPoint(p2, 3);

            winPoint(p1, 1);
            winPoint(p2, 1);

            winPoint(p1, 1);

            String player1PointsAfter = ongoingMatch.getPlayer1Score().getPoints().getValue();
            String player2PointsAfter = ongoingMatch.getPlayer2Score().getPoints().getValue();

            Assertions.assertEquals(Point.more.getValue(), player1PointsAfter);
            Assertions.assertEquals(Point.less.getValue(), player2PointsAfter);

        }

        @Test
        void winPointWithMore() {
            winPoint(p1, 3);
            winPoint(p2, 3);

            winPoint(p1, 1);
            winPoint(p2, 1);

            winPoint(p1, 1);
            winPoint(p1, 1);

            String player1PointsAfter = ongoingMatch.getPlayer1Score().getPoints().getValue();
            String player2PointsAfter = ongoingMatch.getPlayer2Score().getPoints().getValue();

            Integer player1GamesAfter = ongoingMatch.getPlayer1Score().getGames();
            Integer player2GamesAfter = ongoingMatch.getPlayer2Score().getGames();

            Assertions.assertEquals(Point.zero.getValue(), player1PointsAfter);
            Assertions.assertEquals(Point.zero.getValue(), player2PointsAfter);
            Assertions.assertEquals(p1GamesBefore + 1, player1GamesAfter);
            Assertions.assertEquals(p1GamesBefore, player2GamesAfter);

        }

        @Test
        void losePointWithMore() {
            winPoint(p1, 3);
            winPoint(p2, 3);

            winPoint(p1, 1);
            winPoint(p2, 1);

            winPoint(p1, 1);
            winPoint(p2, 1);

            String player1PointsAfter = ongoingMatch.getPlayer1Score().getPoints().getValue();
            String player2PointsAfter = ongoingMatch.getPlayer2Score().getPoints().getValue();

            Integer player1GamesAfter = ongoingMatch.getPlayer1Score().getGames();
            Integer player2GamesAfter = ongoingMatch.getPlayer2Score().getGames();

            Assertions.assertEquals(Point.equal.getValue(), player1PointsAfter);
            Assertions.assertEquals(Point.equal.getValue(), player2PointsAfter);
            Assertions.assertEquals(p1GamesBefore, player1GamesAfter);
            Assertions.assertEquals(p1GamesBefore, player2GamesAfter);

        }

    }

    private void tieBreak() {
        winGame(p1, gamesToWinSet-1);
        winGame(p2, gamesToWinSet-1);
        winGame(p1, 1);
        winGame(p2, 1);
    }


    void winPoint(Player player, int points) {
        for (int i = 0; i < points; i++) {
            matchScoreCalculationService.pointWonBy(player.getId());
        }
    }

    void winGame(Player player, int games) {
        for (int i = 0; i < games; i++) {
            winPoint(player, 4);
        }
    }

    void winSet(Player player, int sets) {
        for (int i = 0; i < sets; i++) {
            winGame(player, gamesToWinSet);
        }
    }

}
