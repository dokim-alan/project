package ca.sait.project.servlets;

import ca.sait.project.models.User;
import ca.sait.project.services.AccountService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
    
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        if ( session.getAttribute("email") != null) {
            String query = request.getQueryString();
            if (query != null && query.contains("logout")) {
                session.invalidate();
                request.setAttribute("message", "Yor are successfully logged out.");
            } else {
                response.sendRedirect("home");
                return;
            }
        }
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }
    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            request.setAttribute("message", "Username or password is missing.");
        } else {
            AccountService account = new AccountService();
            User user = account.login(email, password);
            
            if (user != null) { //redirect
                request.getSession().setAttribute("email", email);
                request.getSession().setAttribute("firstname", user.getFirstName());
                request.getSession().setAttribute("lastname", user.getLastName());
                response.sendRedirect("home");
                return;
            } else { //invalid
                request.setAttribute("email", email);
                request.setAttribute("message", "Username or password is invalid.");
            }
        }
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }
    
/*
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        // automatically log in as "anne"
        session.setAttribute("email", "cprg352+anne@gmail.com");
        response.sendRedirect("notes");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // nothing here
    }
*/
}
