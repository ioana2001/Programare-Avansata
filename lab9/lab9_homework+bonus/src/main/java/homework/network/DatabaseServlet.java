package homework.network;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DatabaseServlet", urlPatterns = {"/lab9/result.jsp"}, initParams = {
@WebInitParam(name = "name", value = "Not provided")
})
public class DatabaseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String dbType = request.getParameter("name");

        request.setAttribute("type", dbType);
        request.getRequestDispatcher("/lab9/result.jsp").forward(request, response);
    }

}
