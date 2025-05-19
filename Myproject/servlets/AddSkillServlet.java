package Myproject.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/AddSkillServlet")
public class AddSkillServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String userId = request.getParameter("userId");
        String skill = request.getParameter("skill");
        String expertise = request.getParameter("expertise");
        String pricing = request.getParameter("pricing");
        String workMode = request.getParameter("workMode");

        try (Connection conn = DatabaseConnectionServlet.getConnection()) {
            String query = "INSERT INTO skills (user_id, skill_name, expertise_level, pricing, work_mode) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, userId);
            stmt.setString(2, skill);
            stmt.setString(3, expertise);
            stmt.setString(4, pricing);
            stmt.setString(5, workMode);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                out.println("Skill added successfully!");
            } else {
                out.println("Failed to add skill.");
            }
        } catch (SQLException e) {
            out.println("Database error: " + e.getMessage());
        }
    }
}
