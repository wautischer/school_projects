package com.example.logindb_wautischer;

import java.io.*;

import at.htlklu.fsst.entities.User;
import at.htlklu.fsst.persistence.LoginDAO;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "viewSecuredComments", value = "/viewSecuredComments")
public class viewSecuredComments extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        String username = request.getParameter("uname");
        String password = request.getParameter("pword");

        if (LoginDAO.checkPassword(username, password)){
            User user = LoginDAO.getUserByUsername(username);
            HttpSession session = request.getSession();
            session.setAttribute("loggedInUser", user);
            response.sendRedirect(request.getContextPath() + "/login/success.jsp");
        } else {
            response.sendRedirect(request.getContextPath() + "/login/failed.jsp");
        }
    }

    public void destroy() {
    }
}