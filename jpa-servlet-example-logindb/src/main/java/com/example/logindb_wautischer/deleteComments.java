package com.example.logindb_wautischer;

import java.io.*;

import at.htlklu.fsst.persistence.LoginDAO;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "deleteComments", value = "/deleteComments")
public class deleteComments extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        Integer id = Integer.parseInt(request.getParameter("uid"));
        LoginDAO.deleteCommentsToUserID(id);

        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    public void destroy() {
    }
}