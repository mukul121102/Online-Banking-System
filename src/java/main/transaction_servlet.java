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

@WebServlet(name = "transaction_servlet", urlPatterns = {"/transaction_servlet"})
public class transaction_servlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + login_servlet.bank, "root", "root");
            Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + transaction_otp_generate_servlet.bank_transfer, "root", "root");

            if (login_servlet.username != null) {
                String command = "update balance set Balance = Balance + ? where Account_Number = ? ";
                PreparedStatement st = connection1.prepareStatement(command);
                st.setDouble(1, Double.parseDouble(transaction_otp_generate_servlet.amount));
                st.setInt(2, Integer.parseInt(transaction_otp_generate_servlet.account_number));
                st.executeUpdate();

                String command1 = "update balance set Balance = Balance - ? where Username = ? ";

                PreparedStatement st1 = connection.prepareStatement(command1);
                st1.setDouble(1, Double.parseDouble(transaction_otp_generate_servlet.amount));
                st1.setString(2, login_servlet.username);
                st1.executeUpdate();

                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                String command2 = "insert into transaction values (?,?,?,?,?,?,?)";
                PreparedStatement st2 = connection.prepareStatement(command2);
                st2.setString(1, login_servlet.username);
                st2.setString(2, login_servlet.username);
                st2.setString(3, "Transfer");
                st2.setString(4, dateFormat.format(date));
                st2.setDouble(5, Double.parseDouble(transaction_otp_generate_servlet.amount));
                st2.setString(6, transaction_otp_generate_servlet.account_name);
                st2.setString(7, transaction_otp_generate_servlet.bank_transfer);
                st2.executeUpdate();

                PreparedStatement st3 = connection1.prepareStatement(command2);
                st3.setString(1, transaction_otp_generate_servlet.account_name);
                st3.setString(2, login_servlet.username);
                st3.setString(3, "Recieved");
                st3.setString(4, dateFormat.format(date));
                st3.setDouble(5, Double.parseDouble(transaction_otp_generate_servlet.amount));
                st3.setString(6, transaction_otp_generate_servlet.account_name);
                st3.setString(7, transaction_otp_generate_servlet.bank_transfer);
                st3.executeUpdate();

                out.println("<script type=\"text/javascript\">");
                out.println("alert('TRANSFER SUCCESS');");
                out.println("location='transaction_page.jsp';");
                out.println("</script>");                
            } else {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('LOGIN FIRST');");
                out.println("location='login_page.jsp';");
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
            Logger.getLogger(transaction_servlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(transaction_servlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(transaction_servlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(transaction_servlet.class.getName()).log(Level.SEVERE, null, ex);
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
