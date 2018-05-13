<%@page import="main.forgetpass_servlet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up Page</title>
        <link rel="stylesheet" href="design.css">
        <link href="style.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <div id="top_links">
            <div id="header">
                <h1>NIIT BANK<span class="style1"></span></h1>
                <h2>The Very Best</h2>
                <A href="login_page.jsp"><IMG SRC="images/home1.gif"/></A>	
            </div>

            <table style="width:897px; background:#FFFFFF; margin:0 auto;">
                <tr >
                    <td width="600" valign="top" style="border-right:#666666 1px dotted;">

                    </td>
                    <td width="900" valign="top">
                        <div id="welcome" style="border-right:#666666 1px dotted;">
                            <table  align="center" bgcolor="white">
                                <td>
                                    <form action="otp_servlet" method="post">
                                        Enter the OTP sent to your mobile number:<br/><br/>
                                        <input type="text" name="otp" placeholder="OTP"/>
                                        <input type="submit" value="Submit" />
                                    </form>
                                    <% out.println(forgetpass_servlet.otp);%>
                                </td>
                            </table>
                        </div>
                    </td>
                </tr>
            </table>
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
