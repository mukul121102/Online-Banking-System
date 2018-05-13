<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Banking System</title>
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
                        <div id="welcome" style="border-right:#666666 1px dotted;"><h1>Login Form</h1><br>
                            <table  align="center" bgcolor="white">
                                <tr>

                                </tr>
                                <td>
                                    <form name = "login form " action="login_servlet" method="post">
                                        <table cellspacing="5" cellpadding="3">
                                            <tr><td>USERNAME:</td><td><input type="text" name="username" placeholder="Username" required="required"/></td></tr>
                                            <tr><td>PASSWORD:</td><td><input type="password" name="password" placeholder="Password" required="required"/></td></tr>
                                            <tr><td>    BANK: </td><td><select name="bank">
                                                        <option>sbi</option>
                                                        <option>icici</option>
                                                    </select></td></tr>
                                            <tr> <td>  <input class="button" type="submit" value="Sign In"/>&nbsp;</td>
                                                <td>   <input class="button" type="reset" value="Clear"/></td></tr>
                                        </table>
                                    </form>
                                </td>
                            </table>

                            <table>
                                <tbody>
                                    <tr>
                                        <td>
                                            <form action="forgetpass_page.jsp">
                                                <input class="button1" type="submit" value="Forget Password?"/>
                                            </form>
                                        </td>
                                        <td>
                                            <form action="signup_page.jsp">
                                                <input class="button1" type="submit" value="Sign Up"/>
                                            </form>
                                        </td>
                                    </tr>
                                </tbody>
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