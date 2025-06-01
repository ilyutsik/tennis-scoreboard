package servlet;

import game.MatchPage;
import service.FinishedMatchesPersistenceService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/matches")
public class MatchesController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageStr = req.getParameter("page");
        String filterName = req.getParameter("filter_by_player_name");
        int page;
        if (pageStr == null || pageStr.trim().isEmpty()) {
            page = 0;
        } else {
            page = Integer.parseInt(pageStr);
        }
        FinishedMatchesPersistenceService service = new FinishedMatchesPersistenceService();
        MatchPage matchPage = service.getMatchPage(filterName, page);

        req.setAttribute("matchPage", matchPage);

        req.getRequestDispatcher("matches.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
