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
            String id = userInfo[1];
            if(id == null) {
                request.setAttribute("invalid-login", "true");
                request.setAttribute("address", "index.jsp");
                getServletContext().getRequestDispatcher("/login.jsp").forward(request,response);
            } else {
                response.addCookie(new Cookie("id", id));
            }
        %>
        
        <div class="main-body">
            <header>
              <span id="logo">Distributed Systems Project II</span>
                <form name="logOut" action="login.jsp" method="POST">
                    <input type="submit" class="header-button" value="Log Out">
                </form>
                <form name="home" action="index.jsp" method="POST">
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
