package Myproject.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/RatingServlet")
public class RatingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String providerId = request.getParameter("providerId");
        String rating = request.getParameter("rating");

        try (Connection conn = DatabaseConnectionServlet.getConnection()) {
            String query = "INSERT INTO ratings (provider_id, rating) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, providerId);
            stmt.setString(2, rating);
            stmt.executeUpdate();
            out.println("Rating submitted successfully!");
        } catch (Exception e) {
            out.println("Database error: " + e.getMessage());
        }
    }
}
