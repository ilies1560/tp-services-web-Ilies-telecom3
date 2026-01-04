package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Commune;
import java.io.IOException;

@WebServlet("/commune")
public class CommuneServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Commune c = new Commune(75001, "Paris 1");
        request.setAttribute("commune", c);
        request.getRequestDispatcher("/commune.jsp").forward(request, response);
    }
}
