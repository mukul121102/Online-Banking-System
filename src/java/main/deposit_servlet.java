package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "deposit_servlet", urlPatterns = {"/deposit_servlet"})
public class deposit_servlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String amount = request.getParameter("amount");

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + login_servlet.bank, "root", "root");

            if (login_servlet.username != null) {
                String command = "update balance set balance = Balance + ? where Username = ? ";

                PreparedStatement st = connection.prepareStatement(command);
                st.setInt(1, Integer.parseInt(amount));
                st.setString(2, login_servlet.username);
                st.executeUpdate();

                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                String command2 = "insert into transaction values (?,?,?,?,?,?,?)";
                PreparedStatement st2 = connection.prepareStatement(command2);
                st2.setString(1, login_servlet.username);
                st2.setString(2, login_servlet.username);
                st2.setString(3, "Deposit");
                st2.setString(4, dateFormat.format(date));
                st2.setInt(5, Integer.parseInt(amount));
                st2.setString(6, "Self");
                st2.setString(7, login_servlet.bank);
                st2.executeUpdate();
                
                out.println("<script type=\"text/javascript\">");
                out.println("alert('AMOUNT ADDED');");
                out.println("location='deposit_page.jsp';");
                out.println("</script>");                
            } else {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('LOGIN FIRST');");
                out.println("location='LOGIN_page.jsp';");
                out.println("</script>");                
            }

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
        } catch (SQLException ex) {
            Logger.getLogger(deposit_servlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(deposit_servlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (SQLException ex) {
            Logger.getLogger(deposit_servlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(deposit_servlet.class.getName()).log(Level.SEVERE, null, ex);
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
