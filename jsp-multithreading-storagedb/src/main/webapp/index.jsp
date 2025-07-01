<%@ page import="java.util.List" %>
<%@ page import="at.htlklu.fsst.entities.Part" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Storage - Wautischer</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
<h1 class="text-center">Storage HTL Moessingerstraße</h1>
<br>
<div class="container">
    <form action="searchStorage" method="get" class="mt-3">
        <div class="mb-3">
            <label for="search-input" class="form-label"><h3>Enter serial number or name of the electronic part:</h3></label>
            <input type="text" id="search-input" name="searchcontent" class="form-control" value="">
        </div>
        <div class="mb-3">
            <button type="submit" class="btn btn-primary">Search</button>
        </div>
    </form>
</div>

<%
    List<Part> temp = (List<Part>) request.getAttribute("partlist");

    if (temp != null && !temp.isEmpty()) {
        out.print("<div class='container-fluid'>");
        out.println("<table class='table table-striped'>");

        out.println("<tr>");
        out.println("<th class='text-white' style='background-color: blue;'> ID </th>");
        out.println("<th class='text-white' style='background-color: blue;'> Serial Nr </th>");
        out.println("<th class='text-white' style='background-color: blue;'> Name </th>");
        out.println("<th class='text-white' style='background-color: blue;'> Box </th>");
        out.println("<th class='text-white' style='background-color: blue;'> Count </th>");
        out.println("</tr>");

        for (Part p : temp) {
            out.println("<tr>");
            out.println("<td>" + p.getId() + "</td>");
            out.println("<td>" + p.getSerialnr() + "</td>");
            out.println("<td>" + p.getPartname() + "</td>");
            out.println("<td>" + p.getBox() + "</td>");
            int count = p.getCount();
            if (count == 0) {
                out.println("<td style='background-color: red;'>" + count + "</td>");
            } else {
                out.println("<td>" + count + "</td>");
            }

            out.println("</tr>");
        }

        out.println("</table>");
        out.println("</div>");
    } else {
        out.println("<div class='container'><p>No parts found.</p></div>");
    }
%>
</body>
</html>
