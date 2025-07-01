package at.htlklu.fsst.servlet;

import java.io.IOException;
import java.util.List;

import at.htlklu.fsst.entities.Part;
import at.htlklu.fsst.persistence.StorageDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "searchStorage", value = "/searchStorage")
public class searchStorage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        String searchC = request.getParameter("searchcontent");
        List<Part> parts = StorageDAO.searchParts(searchC);

        request.setAttribute("partlist", parts);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {
    }
}
