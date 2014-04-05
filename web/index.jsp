<%-- test3
    Document   : index
    Created on : Mar 27, 2014, 7:05:25 PM
    Author     : daradermody
--%>

<%@page import="mainPackage.*" %>
<%@page import="java.util.ArrayList" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="/errorPage.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Thread Page</title>
        <meta name="description" content="Website for forum application for Distributed Systems Project II">
        <meta name="keywords" content="distributed systems project forum application java servlet security">

        <link rel="stylesheet" type="text/css" href="style.css" />

        <%
            // Check session ID, or username and password; if it fails, forward to login
            String[] userInfo = Security.authoriseRequest(request);
            String username = userInfo[0]; // Set to more convenient variable
            String id = userInfo[1]; // Set to more convenient variable

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
            
            // Determine if user has cookies disabled
            boolean cookiesDisabled = request.getCookies() == null;
            
            Cookie cookie = new Cookie("id", id); // Create new cookie with session ID
            cookie.setMaxAge(900); // Set max age to 15 minutes
            cookie.setSecure(true); // Forces browser to only send cookie over HTTPS/SSL
            if(!cookiesDisabled) // If cookies enabled, add cookie to response
                response.addCookie(cookie); 

            // Add new thread if parameters exist
            String newThreadTitle = Security.sanitise(request.getParameter("threadName"), false);
            if (!newThreadTitle.equals("")) {
                // Create new forum thread object with user-inputted thread title/name
                ForumThread newThread = new ForumThread(newThreadTitle);
                // Add message to thread obejct
                newThread.addMessage(Security.sanitise(request.getParameter("threadBody"), true), username);
                ForumBoard.addThread(newThread); // Add thread object to database
            }
        %>
        
        <!-- Import jQuery -->
        <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.0.js"></script>
        <%-- JavaScript function that adds ID field to form when submitted if cookies are disabled --%>
        <script type="text/javascript">
            $(function() {
                $('form').submit(function() { // On submission of form
                    if(!navigator.cookieEnabled) // Check if cookies disabled
                        $(this).append('<input type="hidden" name="id" value="<%= id %>">');
                    return true;
                });
            });
        </script>
    </head>

    <body>
        <div class="main-body">
            <header>
                <span id="logo">Distributed Systems Project II</span>
                <form name="logOut" action="login.jsp" method="GET">
                    <input type="hidden" name="log-out" value="true">
                    <input type="submit" class="header-button" value="Log Out">
                </form>
                <form name="home" action="index.jsp" method="GET">
                    <input type="submit" class="header-button" value="Threads">
                </form>
                <form name="newThread" action="newThread.jsp" method="GET">
                    <input type="submit" class="header-button" value="New Thread">
                </form>
            </header>

            <form name="threadList" method="GET" action="readThread.jsp">
                <ul>
                    <%-- Loops through getting of thread items --%>
                    <% for (int i = 0; i < ForumBoard.getNumberOfThreads(); i++) {
                            String title = ForumBoard.getThread(i).getTitle();
                            String description = ForumBoard.getThread(i).getAllMessages().get(0).getContent();
                    %>
                    <li>
                        <div class="big-wrapper">
                            <button class="thread-title-button" type="submit" name="thread-title" value="<%= title %>"><%= title %></button>
                            <span class="thread-description">
                                <%= description%>
                            </span>
                        </div>
                    </li>
                    <% }%>
                </ul>
            </form>
        </div>
    </body>
</html>
