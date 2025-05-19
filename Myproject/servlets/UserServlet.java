package Myproject.servlets;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.html");
            return;
        }

        String userEmail = (String) session.getAttribute("user");

        try {
            Connection conn = DatabaseConnectionServlet.getConnection();
            String query = "SELECT name, email, role FROM users WHERE email=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, userEmail);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                out.println("<h2>User Details</h2>");
                out.println("<p>Name: " + rs.getString("name") + "</p>");
                out.println("<p>Email: " + rs.getString("email") + "</p>");
                out.println("<p>Role: " + rs.getString("role") + "</p>");
            } else {
                out.println("<p>User not found.</p>");
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<p>Error fetching user details.</p>");
        }
    }
}
