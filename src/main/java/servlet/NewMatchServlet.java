package servlet;

import model.Player;
import dao.PlayerRepository;
import org.hibernate.Session;
import util.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.NoSuchElementException;


@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet {

    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    PlayerRepository playerRepository = new PlayerRepository(session);

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


    }
}
