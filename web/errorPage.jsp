<%-- 
    Document   : errorPage
    Created on : 03-Apr-2014, 12:56:24
    Author     : Puser
--%>
<%--Creates an error page that displays the error type, URI and Status code
    Add this to the top of every other JSP page
        <%@ page errorPage="/errorPage.jsp" %>
--%>
<%@page import="mainPackage.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isErrorPage="true" %>
<html>
    <head>
        <title>Show Error Page</title>
    </head>
    <body>
        <h1>The computer says no!</h1>
        <table width="100%" border="1">
            <tr valign="top">
                <td width="40%"><b>Error:</b></td>
                <td>${pageContext.exception}</td>
            </tr>
            <tr valign="top">
                <td><b>URI:</b></td>
                <td>${pageContext.errorData.requestURI}</td>
            </tr>
            <tr valign="top">
                <td><b>Status code:</b></td>
                <td>${pageContext.errorData.statusCode}</td>
            </tr>      
        </table>
    </body>
</html>