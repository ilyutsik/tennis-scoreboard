package servlet;

import model.Match;
import model.OngoingMatch;
import service.OngoingMatchesService;
import org.hibernate.SessionFactory;
import service.FinishedMatchesPersistenceService;
import service.MatchScoreCalculationService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.UUID;


@WebServlet("/match-score")
public class MatchScoreController extends HttpServlet {

    SessionFactory sessionFactory;

//    Map<UUID, OngoingMatch> ongoingMatches;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
//        ongoingMatches = (Map<UUID, OngoingMatch>) servletConfig.getServletContext().getAttribute("ongoingMatches");
        sessionFactory = (SessionFactory) servletConfig.getServletContext().getAttribute("sessionFactory");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuidString = req.getParameter("uuid");

        UUID uuid = UUID.fromString(uuidString);

        OngoingMatchesService ongoingMatchesService = OngoingMatchesService.getInstance();
        OngoingMatch ongoingMatch = ongoingMatchesService.get(uuid);

        req.setAttribute("ongoingMatch", ongoingMatch);
        req.setAttribute("gameScore", ongoingMatch.getGameScore());
        req.getRequestDispatcher("match-score.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pointWinnerIdStr = req.getParameter("pointWinnerID");
        String uuidString = req.getParameter("uuid");
        UUID uuid = UUID.fromString(uuidString);
        Integer pointWinnerId = Integer.parseInt(pointWinnerIdStr);

        OngoingMatchesService matches = OngoingMatchesService.getInstance();
        OngoingMatch ongoingMatch = matches.get(uuid);
        MatchScoreCalculationService ongoingMatchService = new MatchScoreCalculationService();

        ongoingMatchService.addPoint(pointWinnerId, ongoingMatch);
        if (ongoingMatchService.isGameOver(ongoingMatch)) {

            Match finishedMatch = ongoingMatchService.createFinishedMatch(ongoingMatch);
            FinishedMatchesPersistenceService finishedMatchesPersistenceService = new FinishedMatchesPersistenceService();
            finishedMatchesPersistenceService.saveMatch(finishedMatch);
            req.setAttribute("ongoingMatch", ongoingMatch);
            req.setAttribute("winerName", finishedMatch.getWinner().getName());
            req.getRequestDispatcher("finished-match.jsp").forward(req, resp);
            matches.remove(uuid);
            return;
        }

        String redirectUrl = req.getContextPath() + "/match-score?uuid=" + uuid.toString();
        resp.sendRedirect(redirectUrl);

    }
}
