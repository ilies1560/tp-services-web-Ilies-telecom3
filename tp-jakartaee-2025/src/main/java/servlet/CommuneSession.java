package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Commune;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

@WebServlet("/commune-session")
public class CommuneSession extends HttpServlet {

    private Path communeFilePath() {
        String instanceRoot = System.getProperty("com.sun.aas.instanceRoot");
        if (instanceRoot != null && !instanceRoot.isBlank()) {
            return Path.of(instanceRoot, "commune.txt");
        }
        return Path.of(System.getProperty("user.home"), "commune.txt");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (session.getAttribute("nom") == null || session.getAttribute("prenom") == null) {
            request.getRequestDispatcher("/user-form.jsp").forward(request, response);
            return;
        }
        request.getRequestDispatcher("/commune-session.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);

        Integer count = (Integer) session.getAttribute("count");
        session.setAttribute("count", count == null ? 1 : count + 1);

        String action = request.getParameter("action");
        if ("list".equals(action)) {
            Path p = communeFilePath();
            if (Files.exists(p)) request.setAttribute("communesFile", Files.readAllLines(p, StandardCharsets.UTF_8));
            else request.setAttribute("communesFile", List.of());
            request.getRequestDispatcher("/commune-session.jsp").forward(request, response);
            return;
        }

        String prenom = request.getParameter("prenom");
        String nom = request.getParameter("nom");
        if (prenom != null && nom != null && !prenom.isBlank() && !nom.isBlank() && session.getAttribute("prenom") == null) {
            session.setAttribute("prenom", prenom.trim());
            session.setAttribute("nom", nom.trim());
            session.setAttribute("badinput", null);
            request.getRequestDispatcher("/commune-session.jsp").forward(request, response);
            return;
        }

        String cpStr = request.getParameter("codePostal");
        String name = request.getParameter("name");

        if (cpStr == null || name == null || cpStr.isBlank() || name.isBlank()) {
            session.setAttribute("badinput", "Mauvaise saisie (champs vides)");
            request.getRequestDispatcher("/commune-session.jsp").forward(request, response);
            return;
        }

        int cp;
        try {
            cp = Integer.parseInt(cpStr.trim());
        } catch (NumberFormatException e) {
            session.setAttribute("badinput", "Mauvaise saisie (code postal invalide)");
            request.getRequestDispatcher("/commune-session.jsp").forward(request, response);
            return;
        }

        Commune c = new Commune(cp, name.trim());
        session.setAttribute("commune", c);
        session.setAttribute("badinput", null);

        Path p = communeFilePath();
        if (p.getParent() != null) Files.createDirectories(p.getParent());
        Files.writeString(p, cp + ";" + name.trim() + System.lineSeparator(), StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND);

        request.getRequestDispatcher("/commune-session.jsp").forward(request, response);
    }
}
