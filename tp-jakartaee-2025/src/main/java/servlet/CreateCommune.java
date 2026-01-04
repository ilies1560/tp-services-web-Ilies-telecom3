package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Commune;
import java.io.IOException;

@WebServlet("/create-commune")
public class CreateCommune extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/create-commune.html").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cpStr = request.getParameter("codePostal");
        String name = request.getParameter("name");

        if (cpStr == null || name == null || cpStr.isBlank() || name.isBlank()) {
            request.setAttribute("badinput", "Champs vides");
            request.getRequestDispatcher("/commune.jsp").forward(request, response);
            return;
        }

        int cp;
        try {
            cp = Integer.parseInt(cpStr.trim());
        } catch (NumberFormatException e) {
            request.setAttribute("badinput", "Code postal invalide");
            request.getRequestDispatcher("/commune.jsp").forward(request, response);
            return;
        }

        Commune c = new Commune(cp, name.trim());
        request.setAttribute("commune", c);
        request.setAttribute("badinput", null);
        request.getRequestDispatcher("/commune.jsp").forward(request, response);
    }
}
