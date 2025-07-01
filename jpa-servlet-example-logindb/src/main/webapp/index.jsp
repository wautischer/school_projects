<%@ page import="at.htlklu.fsst.entities.User" %>
<%@ page import="at.htlklu.fsst.persistence.LoginDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="at.htlklu.fsst.entities.Comment" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Login_DB - Wautischer</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
<h1 class="text-center">Login DB</h1>
<br>
<div class="container">
    <ul class="nav justify-content-center">
        <li class="nav-item">
            <a class="nav-link" href="insertcomment.jsp">Add Comment to User</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="deletecomments.jsp">Delete Comments to User ID</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="viewSecuredComments.jsp">View Secured Comments</a>
        </li>
    </ul>
</div>
<br>

<%
    List<User> userSortedByUsername = LoginDAO.getAllUsers();
    List<User> userWithHTLMail = LoginDAO.getAllUsersWithHTLMail();
    List<Comment> commentsByUserID = LoginDAO.getCommentToUserID(8);

    out.println("<br>");
    out.println("<h2>All Users sorted By Username Ascending</h2>");
    out.print("<div class='container-fluid'>");
    out.println("<table class='table table-striped'>");

    out.println("<tr>");
    out.println("<th> ID </th>");
    out.println("<th> Firstname </th>");
    out.println("<th> Surname </th>");
    out.println("<th> Username </th>");
    out.println("<th> Password </th>");
    out.println("<th> Email </th>");
    out.println("</tr>");

    for (User u : userSortedByUsername) {
        out.println("<tr>");
        out.println("<td>" + u.getId() + "</td>");
        out.println("<td>" + u.getFirstname() + "</td>");
        out.println("<td>" + u.getSurname() + "</td>");
        out.println("<td>" + u.getUsername() + "</td>");
        out.println("<td>" + u.getPassword() + "</td>");
        out.println("<td>" + u.getEmail() + "</td>");
        out.println("</tr>");
    }

    out.println("</table>");
    out.println("</div>");

    out.println("<br>");
    out.println("<h2>All Users with HTL-Email</h2>");
    out.print("<div class='container-fluid'>");
    out.println("<table class='table table-striped'>");


    out.println("<tr>");
    out.println("<th> ID </th>");
    out.println("<th> Firstname </th>");
    out.println("<th> Surname </th>");
    out.println("<th> Username </th>");
    out.println("<th> Password </th>");
    out.println("<th> Email </th>");
    out.println("</tr>");

    for (User u : userWithHTLMail) {
        out.println("<tr>");
        out.println("<td>" + u.getId() + "</td>");
        out.println("<td>" + u.getFirstname() + "</td>");
        out.println("<td>" + u.getSurname() + "</td>");
        out.println("<td>" + u.getUsername() + "</td>");
        out.println("<td>" + u.getPassword() + "</td>");
        out.println("<td>" + u.getEmail() + "</td>");
        out.println("</tr>");
    }

    out.println("</table>");
    out.println("</div>");

    out.println("<br>");
    out.println("<h2>All Comments for User with UserID: " + commentsByUserID.get(0).getUser().getId() + "</h2>");
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