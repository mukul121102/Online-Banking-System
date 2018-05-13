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

@WebServlet(name = "summary_servlet", urlPatterns = {"/summary_servlet"})
public class summary_servlet extends HttpServlet {
    public static Double balance;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Account Summary Page</title>");
            out.print("<link rel=\"stylesheet\" href=\"design.css\">");
            out.print("<link href=\"style.css\" rel=\"stylesheet\" type=\"text/css\">");
            out.println("</head>");
            out.println("<body>");
            out.print("<div id=\"top_links\">\n"
                    + "  \n"
                    + "\n"
                    + "<div id=\"header\">\n"
                    + "	<h1>NIIT BANK<span class=\"style1\"></span></h1>\n"
                    + "    <h2>The Very Best</h2>	\n"
                    + "\n"
                    + "</div>\n"
                    + "\n"
                    + "<div id=\"navigation\">\n"
                    + "    <ul>\n"
                    + "    <li><a href=\"home_page.jsp\">HOME PAGE</a></li>\n"
                    + "    <li><a href=\"summary_servlet\">SUMMARY</a></li>\n"
                    + "    <li><a href=\"profile_servlet\">PROFILE</a></li>\n"
                    + "    <li><a href=\"logout_servlet\">LOGOUT</a></li>\n"
                    + "    \n"
                    + "    </ul>\n"
                    + "</div>\n"
                    + "    <div id =\"navigation\">\n"
                    + "        <ul>\n"
                    + "            <li><a href=\"deposit_page.jsp\">DEPOSIT</a></li>\n"
                    + "    <li><a href=\"transaction_page.jsp\">TRANSACTION</a></li>\n"
                    + "    <li><a href=\"passbook_servlet\">PASSBOOK</a></li>\n"
                    + "        </ul>\n"
                    + "    </div>");
            out.print("<table style=\"width:897px; background:#FFFFFF; margin:0 auto;\">\n"
                    + "<tr >\n"
                    + "	<td width=\"300\" valign=\"top\" style=\"border-right:#666666 1px dotted;\">\n"
                    + "    	<div id=\"services\"><h1>Future Services</h1><br>\n"
                    + "		    <ul>\n"
                    + "        	<li><a href=\"#\">Apply For Credit Card</a></li>\n"
                    + "            <li><a href=\"#\">Apply For Online Cheque Book </a></li>\n"
                    + "            <li><a href=\"#\">Support For Online Shopping</a></li>\n"
                    + "            </ul>\n"
                    + "			\n"
                    + "       </div>\n"
                    + "	</td>");

            
            out.print("<td width=\"500\" valign=\"top\">\n" +
"                        <div id=\"welcome\" style=\"border-right:#666666 1px ;\"><h1>SUMMARY</h1><br>");
            out.print("<table>");

            String account_name = null, account_number = null;

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + login_servlet.bank, "root", "root");

            String command = "select * from balance where Username='" + login_servlet.username + "'";
            PreparedStatement st = connection.prepareStatement(command);
            ResultSet rs = st.executeQuery();

            out.print("<table bordercolor=black width=25% border=1>");
            out.println("<tr><th>Account Number</th><th>Username</th><th>Balance</th><tr>");
            while (rs.next()) {
                balance = Double.parseDouble(rs.getString("Balance"));
                out.println("<tr><td>" + rs.getString("Account_Number") + "</td><td>" + rs.getString("Username") + "</td><td>" + rs.getString("Balance") + "</td></tr>");
            }
            out.println("</table>");
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
            Logger.getLogger(summary_servlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(summary_servlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(summary_servlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(summary_servlet.class.getName()).log(Level.SEVERE, null, ex);
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
