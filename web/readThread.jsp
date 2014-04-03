<%--
    Document   : readThread
    Created on : Mar 27, 2014, 7:08:40 PM
    Author     : daradermody
--%>

<%@page import="mainPackage.*"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title><%= Security.sanitise(request.getParameter("thread-title")) %></title>
        <meta name="description" content="Website for forum application for Distributed Systems Project II">
        <meta name="keywords" content="distributed systems project forum application java servlet security">

        <link rel="stylesheet" type="text/css" href="style.css" />
    </head>

    <body>
        <%
            // Check session ID, or username and password; if it fails, forward to login
            String[] userInfo = Security.authoriseRequest(request);
            String id = userInfo[1];
            String username = userInfo[0];
            boolean cookiesDisabled = request.getCookies() == null;

            if (id == null) {
                request.setAttribute("invalid-login", "true");
                request.setAttribute("address", "index.jsp");
                getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
            }

            if(!cookiesDisabled)
                response.addCookie(new Cookie("id", id));  
        %>
        <script type="text/javascript" src="http://code.jquery.com/jquery-2.1.0.js"></script>
        <script type="text/javascript">
            // If cookies are disabled, append ID field on submit
            $(function() {
                $('form').submit(function() {
                    if(<%= cookiesDisabled %>)
                        $(this).append('<input type="hidden" name="id" value="<%= id %>">');
                    return true;
                });
            });
        </script>


        <div class="main-body">
            <header>
                <span id="logo">Distributed Systems Project II</span>
                <form name="logOut" action="login.jsp" method="POST">
                    <input type="submit" class="header-button" value="Log Out">
                </form>
                <form name="home" action="index.jsp" method="POST">
                    <input type="submit" class="header-button" value="Threads">
                </form>
                <form name="newThread" action="newThread.jsp" method="POST">
                    <input type="submit" class="header-button" value="New Thread">
                </form>
            </header>

            <ul>
                <%
                    // Find index of thread being requested
                    String threadTitle;
                    int index;
                    for (index = 0; index < Database.getNumberOfThreads(); index++) {
                        threadTitle = Database.getThread(index).getTitle();
                        String requestedThread = Security.sanitise(request.getParameter("thread-title"));
                        if (threadTitle.equals(requestedThread)) {
                            break;
                        }
                    }

                    ForumThread thread = Database.getThread(index); // Retrieve requested thread
                    String postedContent = Security.sanitise(request.getParameter("messageBody"));

                    // If user posted content, add message to thread
                    if (postedContent != null) {
                        thread.addMessage(postedContent, Security.sanitise(request.getParameter("poster")));
                    }

                    // For each message, display according to set of tags and style
                    int messageCount = 0; // Used to find last message posted
                    for (Message message : thread.getAllMessages()) {
                        messageCount++;
                %>
                <li>
                    <div class="big-wrapper message-container">
                        <div class="poster-info">
                            <img src="images/male-default.png" title="<%= message.getPoster()%>" alt="<%= message.getPoster()%>">
                            <span><%= message.getPoster()%></span>
                            <br><%= message.getDate()%>
                        </div>
                        <div class="message" 
                            <%  // identify message as "latest" if it is the last message
                                if (messageCount == thread.getAllMessages().size())
                                     out.print("id='latest'");
                             %>>
                            <%= message.getContent()%>
                        </div>
                    </div>
                </li>
                <% }%>
                <li>
                    <div class="big-wrapper message-container">
                        <div class="poster-info">
                            <p><img src="images/male-default.png" title="<%= username%>" alt="<%= username%>"></p>
                            <p><%= username%><br></p>
                        </div>
                        <div class="message">
                            <form name="newPost" action="readThread.jsp#latest" method="POST">
                                <textarea class="message thread-message" name="messageBody"></textarea>
                                <input id="submit-button" type="submit" value="Post">
                                <input type="hidden" name="poster" value="<%= username%>">
                                <input type="hidden" name="thread-title" value="<%= thread.getTitle()%>">
                            </form>
                            <form name="refresh" action="readThread.jsp#latest" method="POST">
                                <input type="hidden" name="thread-title" value="<%= thread.getTitle()%>">
                                <button id="refresh-button" type="submit" name="refresh" value="true">Refresh</button>
                            </form>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </body>
</html>



