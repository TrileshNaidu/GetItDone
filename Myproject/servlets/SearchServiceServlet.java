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
import java.sql.ResultSet;

@WebServlet("/SearchServiceServlet")
public class SearchServiceServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String skill = request.getParameter("skill");

        try (Connection conn = DatabaseConnectionServlet.getConnection()) {
            String query = "SELECT * FROM users u JOIN skills s ON u.id = s.user_id WHERE s.skill_name = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, skill);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                out.println("Provider: " + rs.getString("name") + " | Skill: " + rs.getString("skill_name"));
            }
        } catch (Exception e) {
            out.println("Database error: " + e.getMessage());
        }
    }
}
