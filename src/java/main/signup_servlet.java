package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "signup_servlet", urlPatterns = {"/signup_servlet"})
public class signup_servlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            String name = request.getParameter("name");
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String phone_number = request.getParameter("phone_number");
            String password = request.getParameter("password");
            String bank = request.getParameter("bank");

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + bank, "root", "root");

            String command = "insert into profile (Name,Username,Email,Phone_Number,Password,Bank) values (?,?,?,?,?,?)";
            PreparedStatement st = connection.prepareStatement(command);
            st.setString(1, name);
            st.setString(2, username);
            st.setString(3, email);
            st.setString(4, phone_number);
            st.setString(5, password);
            st.setString(6, bank);
            st.executeUpdate();

            String command1 = "insert into balance (Username,Balance) values (?,?)";
            PreparedStatement st1 = connection.prepareStatement(command1);
            st1.setString(1, username);
            st1.setDouble(2, 0);
            st1.executeUpdate();
            
            out.println("<script type=\"text/javascript\">");
            out.println("alert('NEW USER ADDED');");
            out.println("location='login_page.jsp';");
            out.println("</script>");            
        } catch (Exception e) {

            out.println("<script type=\"text/javascript\">");
            out.println("alert('USER EXISTS');");
            out.println("location='signup_page.jsp';");
            out.println("</script>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(signup_servlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(signup_servlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(signup_servlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(signup_servlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
