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

@WebServlet(name = "remove_friend_servlet", urlPatterns = {"/remove_friend_servlet"})
public class remove_friend_servlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            out.println("<%@page import=\"java.sql.ResultSet\"%>\n"
                    + "<%@page import=\"java.sql.PreparedStatement\"%>\n"
                    + "<%@page import=\"main.login_servlet\"%>\n"
                    + "<%@page import=\"java.sql.DriverManager\"%>\n"
                    + "<%@page import=\"java.sql.Connection\"%>\n"
                    + "<%@page contentType=\"text/html\" pageEncoding=\"UTF-8\"%>\n"
                    + "<!DOCTYPE html>\n"
                    + "<html>\n"
                    + "    <head>\n"
                    + "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n"
                    + "        <title>Transaction Page</title>\n"
                    + "        <link rel=\"stylesheet\" href=\"design.css\">\n"
                    + "        <link href=\"style.css\" rel=\"stylesheet\" type=\"text/css\">\n"
                    + "    </head>\n"
                    + "    <body>\n"
                    + "        <div id=\"top_links\">\n"
                    + "\n"
                    + "\n"
                    + "            <div id=\"header\">\n"
                    + "                <h1>NIIT Bank:<span class=\"style1\"></span></h1>\n"
                    + "                <h2>The Very Best</h2>	\n"
                    + "\n"
                    + "            </div>\n"
                    + "\n"
                    + "            <div id=\"navigation\">\n"
                    + "                <ul>\n"
                    + "                    <li><a href=\"home_page.jsp\">HOME PAGE</a></li>\n"
                    + "                    <li><a href=\"summary_servlet\">SUMMARY</a></li>\n"
                    + "                    <li><a href=\"profile_servlet\">PROFILE</a></li>\n"
                    + "                    <li><a href=\"logout_servlet\">LOGOUT</a></li>\n"
                    + "\n"
                    + "                </ul>\n"
                    + "            </div>\n"
                    + "            <div id =\"navigation\">\n"
                    + "                <ul>\n"
                    + "                    <li><a href=\"deposit_page.jsp\">DEPOSIT</a></li>\n"
                    + "                    <li><a href=\"transaction_page.jsp\">TRANSACTION</a></li>\n"
                    + "                    <li><a href=\"passbook_servlet\">PASSBOOK</a></li>\n"
                    + "                </ul>\n"
                    + "            </div>\n"
                    + "            <table style=\"width:897px; background:#FFFFFF; margin:0 auto;\">\n"
                    + "                <tr>\n"
                    + "                    <td width=\"600\" valign=\"top\" style=\"border-right:#666666 1px dotted;\">\n"
                    + "                        <div id=\"services\"><h1>Future Services</h1><br>\n"
                    + "                            <ul>\n"
                    + "                                <li><a href=\"#\">Apply For Credit Card</a></li>\n"
                    + "                                <li><a href=\"#\">Apply For Online Cheque Book </a></li>\n"
                    + "                                <li><a href=\"#\">Support For Online Shopping</a></li>\n"
                    + "                            </ul>\n"
                    + "                        </div>\n"
                    + "                    </td>\n"
                    + "                    <td width=\"900\" valign=\"top\">\n"
                    + "                        <div id=\"welcome\" style=\"border-right:#666666 1px dotted;\"><h1>Funds Transfer</h1><br>\n"
                    + "                            <table  align=\"center\" bgcolor=\"white\">\n"
                    + "                                <td>\n"
                    + "                                    <form action=\"transaction_otp_generate_servlet\" method=\"post\">\n"
                    + "                                        <table cellspacing=\"5\" cellpadding=\"3\">\n"
                    + "                                            <tr><td>A/C NUMBER:</td><td><input type=\"text\" name=\"account_number\"/></td></tr>\n"
                    + "                                            <tr><td>A/C NAME:</td><td> <input type=\"text\" name=\"account_name\"/></td></tr>\n"
                    + "                                            <tr><td>BANK:</td><td><select name=\"bank_transfer\">\n"
                    + "                                                        <option>sbi</option>\n"
                    + "                                                        <option>icici</option>\n"
                    + "                                                    </select></td></tr>\n"
                    + "                                            <tr><td>AMOUNT:</td><td><input type=\"text\" name=\"amount\"/></td></tr>\n"
                    + "                                            <tr><td><input type=\"submit\" value=\"Transfer Funds\"/></td>\n"
                    + "                                                <td><input type=\"reset\" value=\"Clear\"/></td></tr>\n"
                    + "                                        </table>\n"
                    + "                                    </form>\n"
                    + "                                </td>\n"
                    + "                            </table>\n"
                    + "                        </div>\n"
                    + "                    </td>\n"
                    + "                    <td>\n"
                    + "                    <td width=\"900\" valign=\"top\">\n"
                    + "                        <div id=\"welcome\" style=\"border-right:#666666 1px dotted;\"><h1>Beneficiary Remove</h1><br>\n"
                    + "                            <table  align=\"center\" bgcolor=\"white\">\n"
                    + "                                <td>\n"
                    + "                                    <form action=\"delete_friend_servlet\">");

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + login_servlet.bank, "root", "root");

            String command = "Select * from friend where By_Name = ?";
            PreparedStatement st = connection.prepareStatement(command);
            st.setString(1, login_servlet.username);
            ResultSet rs = st.executeQuery();
            out.println("Beneficiary: <select name=\"friend\">\n");
            while (rs.next()) {
                out.println("<option>" + rs.getString("Username") + "</option>\n");
            }
            out.println("</select><br/><br/>");

            out.println("\n"
                    + "                                        <input type=\"submit\" value=\"Remove\"/><br/><br/>\n"
                    + "                                    </form>\n"
                    + "                                </td>\n"
                    + "                            </table>");
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
            Logger.getLogger(remove_friend_servlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(remove_friend_servlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(remove_friend_servlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(remove_friend_servlet.class.getName()).log(Level.SEVERE, null, ex);
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
