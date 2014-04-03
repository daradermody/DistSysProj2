<%-- 
    Document   : index
    Created on : Mar 31, 2014, 9:11:40 PM
    Author     : daradermody
--%>

<%@page import="mainPackage.Security"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <meta name="description" content="Website for forum application for Distributed Systems Project II">
        <meta name="keywords" content="distributed systems project forum application java servlet security">

        <link rel="stylesheet" type="text/css" href="style.css" />
        <script type="text/javascript" src="http://code.jquery.com/jquery-2.1.0.js"></script>
        <script src="http://crypto-js.googlecode.com/svn/tags/3.1.2/build/rollups/sha1.js"></script>
        <script type="text/javascript">
            $(function() {
                $('#login-fields').submit(function() {
                    $passwd = $('input[name="password"]');
                    $passwd.val(CryptoJS.SHA1($passwd.val()));
                    return true;
                });
            });
        </script>
    </head>
    <body>
        <%
            // Add session ID cookie to test if cookies enabled (tested in index.jsp)
            response.addCookie(new Cookie("id", ""));

            // Get address of intended location (before forward to login page)
            String address = "";
            address = Security.sanitise((String) request.getAttribute("address"));
            
            if (address.equals(""))
                address = "index.jsp";

            // If user submitted parameters for inteded page, retrieve them and add them (except username/passwords)
            if (!request.getParameterMap().isEmpty()) {
                address += "?";
                for (Entry<String, String[]> entry : request.getParameterMap().entrySet())
                    if (!entry.getKey().equals("username") && !entry.getKey().equals("password"))
                        address += String.format("%s=%s&", Security.sanitise(entry.getKey()), Security.sanitise(entry.getValue()[0]));
                address = address.substring(0, address.length() - 1); // Truncate last & character
            }
        %>

        <div class="main-body">
            <header>
                <div id="logo">Distributed Systems Project II</div>
            </header>
            <div class="big-wrapper" id="login-container">
                <div id="login-image">
                </div>
                <form id="login-fields" name="login" action="<%= address%>" method="GET">
                    <ol>
                        <li><input type="text" class="login-text-field" placeholder="Username" name="username"></li>
                        <li><input type="password" class="login-text-field" placeholder="Password" name="password"></li>
                        <li>
                            <input type="submit" value="Login"> 
                            <%
                                String invalidLogin = null;
                                try {
                                    invalidLogin = (String)request.getAttribute("invalid-login");
                                } catch(Exception e) {
                                    invalidLogin = "false";
                                }
                                if(invalidLogin != null)
                                    if (invalidLogin.equals("true"))
                                        out.println("<span class='error-message'>Invalid login! Bears "
                                                + "are now flocking to your location.</span>");
                            %>
                        </li>
                    </ol>
                </form>
            </div>
        </div>
    </body>
</html>
