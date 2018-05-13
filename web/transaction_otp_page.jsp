<%@page import="main.transaction_otp_generate_servlet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Transaction Page</title>
        <link rel="stylesheet" href="design.css">
        <link href="style.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <div id="top_links">


            <div id="header">
                <h1>NIIT BANK<span class="style1"></span></h1>
                <h2>The Very Best</h2>	

            </div>

            <div id="navigation">
                <ul>
                    <li><a href="home_page.jsp">HOME PAGE</a></li>
                    <li><a href="summary_servlet">SUMMARY</a></li>
                    <li><a href="profile_servlet">PROFILE</a></li>
                    <li><a href="logout_servlet">LOGOUT</a></li>

                </ul>
            </div>
            <div id ="navigation">
                <ul>
                    <li><a href="deposit_page.jsp">DEPOSIT</a></li>
                    <li><a href="transaction_page.jsp">TRANSACTION</a></li>
                    <li><a href="passbook_servlet">PASSBOOK</a></li>
                </ul>
            </div>
            <table style="width:897px; background:#FFFFFF; margin:0 auto;">
                <tr >
                    <td width="600" valign="top" style="border-right:#666666 1px dotted;">
                        <div id="services"><h1>Future Services</h1><br>
                            <ul>
                                <li><a href="#">Apply For Credit Card</a></li>
                                <li><a href="#">Apply For Online Cheque Book </a></li>
                                <li><a href="#">Support For Online Shopping</a></li>
                            </ul>

                        </div>
                    </td>
                    <td width="900" valign="top">
                        <div id="welcome" style="border-right:#666666 1px dotted;"><h1>Funds Transfer</h1><br>
                            <table  align="center" bgcolor="white">
                                <tr>

                                </tr>
                                <td>
                                    <form action="transaction_otp_servlet" method="post">
                                        Enter the OTP sent to your mobile number:<br/><br/>
                                        <input type="text" name="otp" placeholder="OTP" required="required" title="Type Valid OTP"pattern="[0-9]{4}"/>
                                        <input type="submit" value="Submit" />
                                    </form>
                                    </center>
                                    <% out.println(transaction_otp_generate_servlet.otp);%>
                                </td>
                            </table>
                        </div>
                    </td></tr></table>
            <div id="footer_top">
                <div id="footer_navigation">


                </div>

                <div id="footer_copyright" >

                    <center><img  alt="Capstone Project"  width="196" height="106"></center><br>
                    <p>This is the Online Banking System made for Capstone project by Aditya Sharma, Ajinkya Dalvi, Mukul Gupta, Sourav Swain and Varnit Kumar as the students of NIIT University</p>

                    Copyright © Online Banking System</div>
            </div>
        </div>
    </body>
</html>
