<%@ page import="at.htlklu.fsst.persistence.LoginDAO" %>
<%@ page import="at.htlklu.fsst.entities.Comment" %>
<%@ page import="java.util.List" %>
<%@ page import="at.htlklu.fsst.entities.User" %>
<%@ page import="java.sql.SQLOutput" %><%--
  Created by IntelliJ IDEA.
  User: laurin
  Date: 21.05.24
  Time: 23:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Secured Comments</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
<h1 class="text-bg-success text-center">Login Successfully</h1>

<%
    session = request.getSession();
    User loggedInUser = (User) session.getAttribute("loggedInUser");

    List<Comment> commentsByUserID = LoginDAO.getCommentToUserID(loggedInUser.getId());

    out.println("<br>");
    out.println("<h2>All Comments for User with UserID: "+loggedInUser.getId() +"</h2>");
    out.print("<div class='container-fluid'>");
    out.println("<table class='table table-striped'>");

    out.println("<tr>");
    out.println("<th> ID </th>");
    out.println("<th> Comment </th>");
    out.println("<th> User ID </th>");
    out.println("</tr>");

    for (Comment c : commentsByUserID) {
        out.println("<tr>");
        out.println("<td>" + c.getId() + "</td>");
        out.println("<td>" + c.getComment() + "</td>");
        out.println("<td>" + c.getUser().getId() + "</td>");
        out.println("</tr>");
    }

    out.println("</table>");
    out.println("</div>");
%>
</body>
</html>
