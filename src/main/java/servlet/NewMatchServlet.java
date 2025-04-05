package servlet;

import model.Player;
import repository.PlayerRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.NoSuchElementException;


@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet {

    PlayerRepository playerRepository = new PlayerRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("new-match.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String player1Name = req.getParameter("player1");
        String player2Name = req.getParameter("player2");

        Player player1;
        Player player2;

        try {
            player1 = playerRepository.findByName(player1Name);
        } catch (NoSuchElementException e) {
            player1 = playerRepository.save(new Player(player1Name));
        }
        try {
            player2 = playerRepository.findByName(player2Name);
        } catch (NoSuchElementException e) {
            player2 = playerRepository.save(new Player(player2Name));

        }

    }
}
