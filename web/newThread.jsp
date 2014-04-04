<%-- 
    Document   : newThread
    Created on : Mar 27, 2014, 10:19:17 PM
    Author     : daradermody
--%>

<%@page import="mainPackage.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>New Thread</title>
        <meta name="description" content="Website for forum application for Distributed Systems Project II">
        <meta name="keywords" content="distributed systems project forum application java servlet security">

        <link rel="stylesheet" type="text/css" href="style.css" />
    </head>

    <body>
        <%
            // Check session ID, or username and password; if it fails, forward to login
            String[] userInfo = Security.authoriseRequest(request);
            String username = userInfo[0]; // Set to more convenient variable
            String id = userInfo[1]; // Set to more convenient variable
            // Determine if user has cookies disabled
            boolean cookiesDisabled = request.getCookies() == null;
            
            // If session ID invalid/non-existant, forward to login page (also 
            // determine if login was attempted)
            if(id.equals("")) {
                // If login failed, set attribute so login.jsp can set error message
                if(!username.equals("<none>"))
                    request.setAttribute("invalid-login", "true");
                else
                    request.setAttribute("invalid-login", "false");
                request.setAttribute("address", "index.jsp"); // Set requested page as this page
                // Forward request (with parameters) to login page for authentication
                getServletContext().getRequestDispatcher("/login.jsp").forward(request,response);
            }
            
            Cookie cookie = new Cookie("id", id); // Create new cookie with session ID
            cookie.setMaxAge(900); // Set max age to 15 minutes
            cookie.setSecure(true); // Forces browser to only send cookie over HTTPS/SSL
            if(!cookiesDisabled) // If cookies enabled, add cookie to response
                response.addCookie(cookie);     
        %>

        <!-- Import jQuery -->
        <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.0.js"></script>
        <%-- JavaScript function that adds ID field to form when submitted if cookies are disabled --%>
        <script type="text/javascript">
            $(function() {
                $('form').submit(function() { // On submission of form
                    if(<%= cookiesDisabled %>) // Check if cookies disabled
                        $(this).append('<input type="hidden" name="id" value="<%= id %>">');
                    return true;
                });
            });
        </script>

        
        <div class="main-body">
            <header>
              <span id="logo">Distributed Systems Project II</span>
                <form name="logOut" action="login.jsp" method="GET">
                    <input type="submit" class="header-button" value="Log Out">
                </form>
                <form name="home" action="index.jsp" method="GET">
                    <input type="submit" class="header-button" value="Threads">
                </form>
            </header>

            <form name="newThread" method="GET" action="index.jsp">
                <div class="big-wrapper message-container">
                    <div id="thread-title-container">
                        Title of thread: <input type="text" id="thread-title" name="threadName">
                    </div>
                    <textarea class="message new-thread" name="threadBody"></textarea>
                    <input id="submit-button" type="submit" value="Create">
                </div>
            </form>
        </div>
    </body>
</html>
