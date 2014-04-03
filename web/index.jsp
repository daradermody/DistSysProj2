<%--
    Document   : index
    Created on : Mar 27, 2014, 7:05:25 PM
    Author     : daradermody
--%>

<%@page import="mainPackage.*" %>
<%@page import="java.util.ArrayList" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Template Page</title>
        <meta name="description" content="Website for forum application for Distributed Systems Project II">
        <meta name="keywords" content="distributed systems project forum application java servlet security">

        <link rel="stylesheet" type="text/css" href="style.css" />

        <%
            // Check session ID, or username and password; if it fails, forward to login
            String[] userInfo = Security.authoriseRequest(request);
            String username = userInfo[0];
            String id = userInfo[1];
            if(id == null) {
                request.setAttribute("invalid-login", "true");
                request.setAttribute("address", "main.jsp");
                getServletContext().getRequestDispatcher("/login.jsp").forward(request,response);
            } else {
                response.addCookie(new Cookie("id", id));
            }

            // Add new thread if parameters exist
            String newThreadTitle = Security.sanitise(request.getParameter("threadName"));
            if (!newThreadTitle.equals("")) {
                ForumThread newThread = new ForumThread(newThreadTitle);
                newThread.addMessage(Security.sanitise(request.getParameter("threadBody")), username);
                Database.addThread(newThread);
            }
        %>
    </head>

    <body>
        <div class="main-body">
            <header>
                <span id="logo">Distributed Systems Project II</span>
                <form name="logOut" action="login.jsp" method="POST">
                    <input type="submit" class="header-button" value="Log Out">
                </form>
                <form name="home" action="main.jsp" method="POST">
                    <input type="submit" class="header-button" value="Threads">
                </form>
                <form name="newThread" action="newThread.jsp" method="POST">
                    <input type="submit" class="header-button" value="New Thread">
                </form>
            </header>

            <form name="threadList" method="POST" action="readThread.jsp">
                <ul>
                    <%-- Loops through getting of thread items --%>
                    <% for (int i = 0; i < Database.getNumberOfThreads(); i++) {
                            String title = Database.getThread(i).getTitle();
                            String description = Database.getThread(i).getAllMessages().get(0).getContent();
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
