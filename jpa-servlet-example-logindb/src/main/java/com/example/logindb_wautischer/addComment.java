package com.example.logindb_wautischer;

import java.io.*;

import at.htlklu.fsst.persistence.LoginDAO;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "addComment", value = "/addComment")
public class addComment extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        String name =  request.getParameter("uname");
        String comment = request.getParameter("cname");
        LoginDAO.insertCommentToUserName(name, comment);

        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    public void destroy() {
    }
}