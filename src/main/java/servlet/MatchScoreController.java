package servlet;

import model.Match;
import game.OngoingMatch;
import service.OngoingMatchesService;
import service.FinishedMatchesPersistenceService;
import service.MatchScoreCalculationService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.List;
import java.util.Objects;
import java.util.UUID;


@WebServlet("/match-score")
public class MatchScoreController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuidString = req.getParameter("uuid");

        UUID uuid = UUID.fromString(uuidString);

        OngoingMatchesService ongoingMatchesService = OngoingMatchesService.getInstance();
        OngoingMatch ongoingMatch = ongoingMatchesService.get(uuid);

        req.setAttribute("ongoingMatch", ongoingMatch);
        req.getRequestDispatcher("match-score.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pointWinnerIdStr = req.getParameter("pointWinnerID");

        String uuidString = req.getParameter("uuid");
        UUID uuid = UUID.fromString(uuidString);
        if (Objects.equals(pointWinnerIdStr, "null")) {
            String redirectUrl = req.getContextPath() + "/match-score?uuid=" + uuid.toString();
            resp.sendRedirect(redirectUrl);
            return;
        }
        Integer pointWinnerId = Integer.parseInt(pointWinnerIdStr);

        OngoingMatchesService matches = OngoingMatchesService.getInstance();
        OngoingMatch ongoingMatch = matches.get(uuid);
        if (ongoingMatch == null) {
            String redirectUrl = req.getContextPath() + "/matches";
            resp.sendRedirect(redirectUrl);
            return;
        }
        MatchScoreCalculationService calculationService = new MatchScoreCalculationService(ongoingMatch);

        calculationService.pointWonBy(pointWinnerId);
        if (calculationService.isGameOver()) {
            req.setAttribute("ongoingMatch", ongoingMatch);
            matches.remove(uuid);

            Match finishedMatch = calculationService.getFinishedMatch();
            req.setAttribute("winerName", finishedMatch.getWinner().getName());

            FinishedMatchesPersistenceService finishedMatchesPersistenceService = new FinishedMatchesPersistenceService();
            finishedMatchesPersistenceService.saveMatch(finishedMatch);


            req.getRequestDispatcher("finished-match.jsp").forward(req, resp);
            return;
        }

        String redirectUrl = req.getContextPath() + "/match-score?uuid=" + uuid.toString();
        resp.sendRedirect(redirectUrl);

    }
}
