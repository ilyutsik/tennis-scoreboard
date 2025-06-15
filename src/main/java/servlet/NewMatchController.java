package servlet;

import exception.InternalServerError;
import game.OngoingMatch;
import service.OngoingMatchesService;
import model.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import response.ResponseSender;
import service.PlayerService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.UUID;


@WebServlet("/new-match")
public class NewMatchController extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(NewMatchController.class);

    PlayerService playerService = new PlayerService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("new-match.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String player1Name = req.getParameter("player1");
        String player2Name = req.getParameter("player2");

        if (player1Name.length() > 20) {
            req.setAttribute("error", "Player 1 name is too long");
            req.getRequestDispatcher("new-match.jsp").forward(req, resp);
        }
        if (player2Name.length() > 20) {
            req.setAttribute("error", "Player 2 name is too long");
            req.getRequestDispatcher("new-match.jsp").forward(req, resp);
        }

        if (player2Name.equals(player1Name)) {
            req.setAttribute("error", "Players name can`t be the same");
            req.getRequestDispatcher("new-match.jsp").forward(req, resp);
        }

        try {
            Player player1 = playerService.getOrCreatePlayer(player1Name);
            Player player2 = playerService.getOrCreatePlayer(player2Name);

            OngoingMatch ongoingMatch = new OngoingMatch(player1, player2);
            UUID uuid = UUID.randomUUID();

            OngoingMatchesService ongoingMatchesService = OngoingMatchesService.getInstance();
            ongoingMatchesService.put(uuid, ongoingMatch);

            String redirectUrl = req.getContextPath() + "/match-score?uuid=" + uuid.toString();
            resp.sendRedirect(redirectUrl);
        } catch (InternalServerError e) {
            ResponseSender.sendErrorResponse(resp, e.getMessage(), HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }
}
