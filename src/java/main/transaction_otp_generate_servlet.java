package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "transaction_otp_generate_servlet", urlPatterns = {"/transaction_otp_generate_servlet"})
public class transaction_otp_generate_servlet extends HttpServlet {

    public static int otp;
    public static String account_number, account_name, amount, bank_transfer;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            account_number = request.getParameter("account_number");
            account_name = request.getParameter("account_name");
            amount = request.getParameter("amount");
            bank_transfer = request.getParameter("bank_transfer");

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + login_servlet.bank, "root", "root");

            String command = "select * from balance where Username='" + login_servlet.username + "'";
            PreparedStatement st = connection.prepareStatement(command);
            ResultSet rs = st.executeQuery();
            String balance = null;
            while (rs.next()) {
                balance = rs.getString("Balance");
            }

            Double difference = Double.parseDouble(balance) - Double.parseDouble(amount);

            if (difference >= 0) {
                otp = OTP.otpGenerator();
                
                response.sendRedirect("transaction_otp_page.jsp");
            } else {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('INSUFFICIENT FUNDS');");
                out.println("location='transaction_page.jsp';");
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(transaction_otp_generate_servlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(transaction_otp_generate_servlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(transaction_otp_generate_servlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(transaction_otp_generate_servlet.class.getName()).log(Level.SEVERE, null, ex);
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
